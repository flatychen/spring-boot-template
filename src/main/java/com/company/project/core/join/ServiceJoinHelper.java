package com.company.project.core.join;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 用于服务端join的接口
 */
@Slf4j
public class ServiceJoinHelper {


    private static final int DEFALUT_LIST_SIZE = 16;


    private static ConcurrentMap<Field, JoinField> targetJoinFieldConcurrentMap = new ConcurrentHashMap<>();


    public static <S> void join(Class<S> clazz, List<S> beans, ServiceJoinable... serviceJoinables) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Validate.notNull(beans, "beans不能为空");
        Validate.notNull(clazz, "clazz不能为空");
        Validate.notEmpty(beans, "beans不能为空");
        Validate.notNull(serviceJoinables, "serviceJoinables不能为空");

        // 1. 查询目标bean中的field，并且排序  fixme 可以缓存
        List<Field> targetFields = prepareTargetField(clazz);


        //  检查targetFields注解是否为空
        if (CollectionUtils.isEmpty(targetFields)) {
            log.warn("clazz：{} 无属性含有 @JoinField", clazz);
            return;
        }

        // 2. 查询源field ,与上述目标field保持顺序一致 fixme 可以缓存

        List<Field> sourceFields = reflectSourceFields(clazz, targetFields);

        // 3. 建立 target field 与 source field  映射
        Map<Field, Field> targetFieldToSourceFieldMap = buildTargetAndSourceMap(sourceFields, targetFields);

        // 4. 通过反射得到源field 的值列表，注意 sourceFieldValueMap 用于后期快速查找
        Map<BeanField<S>, Object> sourceFieldValueMap = Maps.newHashMapWithExpectedSize(DEFALUT_LIST_SIZE);
        Map<Field, List<Integer>> sourceValues = reflectAndBuildSourceFieldValues(beans, sourceFields, sourceFieldValueMap);


        // 5. 调用 serviceJoinables 查询方法
        Map<Field, List<Object>> targetValues = fetchTargetFieldValues(targetFields, targetFieldToSourceFieldMap, sourceValues, serviceJoinables);


        // 6. 构建targetFieldValueMap 用于快速查找 ，注意与sourceFieldValueMap 对应关系
        Map<BeanField, Object> targetFieldValueMap = buildTargetFieldValuesMap(targetFields, targetValues);

        // 7 写入source  bean
        for (S bean : beans) {
            for (Field targetField : targetFields) {
                Field sourceFiled = targetFieldToSourceFieldMap.get(targetField);
                Object sourceValue = sourceFieldValueMap.get(new BeanField(sourceFiled, bean));
                Object targetValue = targetFieldValueMap.get(new BeanField(targetField, sourceValue));
                try {
                    FieldUtils.writeField(targetField, bean, targetValue, true);
                } catch (IllegalAccessException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        }

        log.info("ServiceJoinHelper join use time:{}", stopwatch.toString());
    }

    private static Map<BeanField, Object> buildTargetFieldValuesMap(List<Field> targetFields, Map<Field, List<Object>> targetValues) {
        Map<BeanField, Object> targetFieldValueMap = Maps.newHashMapWithExpectedSize(DEFALUT_LIST_SIZE);
        targetValues.forEach((targetField, oneTargetFieldValues) -> {
            Field targetIdField = getOuterTargetField(targetField,oneTargetFieldValues);
            for (Object oneTargetFieldValue : oneTargetFieldValues) {
                Object o = null;
                try {
                    o = FieldUtils.readField(targetIdField, oneTargetFieldValue, true);
                } catch (IllegalAccessException e) {
                    throw new IllegalArgumentException(e);
                }
                targetFieldValueMap.put(new BeanField(targetField, o), oneTargetFieldValue);
            }
        });
        return targetFieldValueMap;
    }

    private static Field getOuterTargetField(Field targetField, List<Object> oneTargetFieldValues) {
        JoinField joinField = targetJoinFieldConcurrentMap.get(targetField);
        Class targetObjectClass = oneTargetFieldValues.get(0).getClass();
        Field targetIdField = FieldUtils.getField(targetObjectClass,joinField.customOuterField(), true);
        return targetIdField;
    }

    private static <S> Map<Field, List<Integer>> reflectAndBuildSourceFieldValues(List<S> beans, List<Field> sourceFields, Map<BeanField<S>, Object> sourceFieldValueMap) {
        Map<Field, List<Integer>> sourceValues = Maps.newHashMapWithExpectedSize(DEFALUT_LIST_SIZE);
        // 4. 获取源field字段值的列表
        for (Field sourceField : sourceFields) {
            List<Integer> values = Lists.newArrayListWithCapacity(DEFALUT_LIST_SIZE);
            for (S bean : beans) {
                Object sourceVal = null;
                try {
                    sourceVal = FieldUtils.readField(sourceField, bean);
                } catch (IllegalAccessException e) {
                    throw new IllegalArgumentException(e);
                }
                values.add(new Integer(sourceVal.toString()));
                sourceFieldValueMap.put(new BeanField<>(sourceField, bean), sourceVal);
            }
            sourceValues.put(sourceField, values);
        }
        return sourceValues;
    }

    /**
     * 调用ServiceJoinable 查询相关方法
     *
     * @param targetFields
     * @param targetFieldToSourceFieldMap
     * @param sourceValues
     * @param serviceJoinables
     * @return
     */
    private static Map<Field, List<Object>> fetchTargetFieldValues(List<Field> targetFields, Map<Field, Field> targetFieldToSourceFieldMap, Map<Field, List<Integer>> sourceValues, ServiceJoinable[] serviceJoinables) {
        Map<Field, List<Object>> targetValues = Maps.newHashMapWithExpectedSize(DEFALUT_LIST_SIZE);
        for (int i = 0; i < serviceJoinables.length; i++) {
            Field targetField = targetFields.get(i);
            Field sourceField = targetFieldToSourceFieldMap.get(targetField);
            ServiceJoinable serviceJoinable = serviceJoinables[i];
            List<Object> list = serviceJoinable.findByIds(sourceValues.get(sourceField));
            if (!CollectionUtils.isEmpty(list)) {
                targetValues.put(targetField, list);
            }
        }
        return targetValues;
    }

    /**
     *
     * 建立target与source的关系
     *
     * @param sourceFields
     * @param targetFields
     * @return
     */
    private static Map<Field, Field> buildTargetAndSourceMap(List<Field> sourceFields, List<Field> targetFields) {
        Map<Field, Field> fieldFieldMap = Maps.newHashMapWithExpectedSize(DEFALUT_LIST_SIZE);
        for (int i = 0; i < targetFields.size(); i++) {
            fieldFieldMap.put(targetFields.get(i), sourceFields.get(i));
        }
        return fieldFieldMap;
    }


    /**
     * 反射得到源bean字段列表
     *
     * @param clazz
     * @param targetFields
     * @param <S>
     * @return
     */
    private static <S> List<Field> reflectSourceFields(Class<S> clazz, List<Field> targetFields) {
        // 通过目标filed获取来源field
        List<Field> sourceFields = Lists.newArrayListWithCapacity(DEFALUT_LIST_SIZE);
        for (Field targetField : targetFields) {
            JoinField joinField =  targetJoinFieldConcurrentMap.get(targetField);
            Field sourceField = FieldUtils.getDeclaredField(clazz, joinField.sourceField(), true);
            if (sourceField == null) {
                log.warn("@JoinField sourceField:{} 不存在", joinField.sourceField());
                throw new IllegalArgumentException();
            } else {
                sourceFields.add(sourceField);
            }
        }
        return sourceFields;
    }

    /**
     * 排序bean之中的目标字段,根据注解JoinField 之中的order排序。
     *
     * @param clazz
     * @param <S>
     * @return
     */
    private static <S> List<Field> prepareTargetField(Class<S> clazz) {
        List<Field> fields = FieldUtils.getFieldsListWithAnnotation(clazz, JoinField.class);
        fields.stream().forEach(field -> {
            JoinField joinField = field.getAnnotation(JoinField.class);
            targetJoinFieldConcurrentMap.putIfAbsent(field, joinField);
        });

        fields.sort((o1, o2) -> {
            JoinField j1 = targetJoinFieldConcurrentMap.get(o1);
            JoinField j2 = targetJoinFieldConcurrentMap.get(o2);
            return new Integer(j1.order()).compareTo(new Integer(j2.order()));
        });
        return fields;
    }






    /**
     * 用于map映射之用
     *
     * @param <T>
     */
    private static class BeanField<T> {
        private Field field;
        private T t;


        public BeanField(Field field, T t) {
            this.field = field;
            this.t = t;
        }

        public Field getField() {
            return field;
        }

        public T getT() {
            return t;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BeanField<?> beanField = (BeanField<?>) o;
            return Objects.equals(field, beanField.field) &&
                    Objects.equals(t, beanField.t);
        }

        @Override
        public int hashCode() {
            return Objects.hash(field, t);
        }


        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("BeanField{");
            sb.append("field=").append(field);
            sb.append(", t=").append(t);
            sb.append('}');
            return sb.toString();
        }
    }


}
