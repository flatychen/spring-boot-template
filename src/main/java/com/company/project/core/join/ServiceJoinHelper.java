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

/**
 * 用于服务端join的接口
 */
@Slf4j
public class ServiceJoinHelper {


//    private ConcurrentMap<Class,>


    public static <S, T> void join(Class<S> clazz, List<S> beans, ServiceJoinable... serviceJoinables) throws IllegalAccessException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Validate.notNull(beans, "beans不能为空");
        Validate.notNull(clazz, "clazz不能为空");
        Validate.notEmpty(beans, "beans不能为空");
        Validate.notNull(serviceJoinables, "serviceJoinable不能为空");

        // 查询目标field  fixme 可以缓存
        List<Field> targetFields = sortTargetField(clazz);


        //  检查注解是否为空
        if (CollectionUtils.isEmpty(targetFields)) {
            log.warn("@JoinField 无属性");
            return;
        }

        // 查询源field ,与上述目标保持一致 fixme 可以缓存
        List<Field> sourceFields = reflectSourceFields(clazz, targetFields);

        Map<Field, Field> targetMapSourceField = targetMapSourceField(sourceFields, targetFields);

        Map<Field, List<Object>> sourceValues = Maps.newHashMap();

        Map<BeanField<S>, Object> beanFieldListMap = Maps.newHashMap();

        // 获取源字段值的列表
        for (Field sourceField : sourceFields) {
            List<Object> values = Lists.newArrayList();
            for (S bean : beans) {
                Object sourceVal = FieldUtils.readField(sourceField, bean);
                values.add(sourceVal);
                beanFieldListMap.put(new BeanField<>(sourceField, bean), sourceVal);
            }
            sourceValues.put(sourceField, values);
        }


//        return sourceValues;
//        Map<Field, List<Object>> sourceValues = getSourceFieldValues(beans, sourceFields);

        log.info("{}", sourceValues);

        Map<Field, List<Object>> targetValues = Maps.newHashMap();
        for (int i = 0; i < serviceJoinables.length; i++) {
            Field targetField = targetFields.get(i);
            ServiceJoinable serviceJoinable = serviceJoinables[i];
            List<Object> list = serviceJoinable.findByIds(sourceValues.get(i));
            targetValues.put(targetField, list);
        }


        Map<BeanField<S>, Object> targetFieldListMap = Maps.newHashMap();


        log.info("{}", targetValues);

        for (S bean : beans) {
            for (Field targetField : targetFields) {
                Field sourceFiled = targetMapSourceField.get(targetField);
                List<Object> onetargetValues = targetValues.get(targetField);
                // how
                FieldUtils.writeField(targetField, bean, onetargetValues.get(0));
            }
        }

        log.info("ServiceJoinHelper join use time:{}", stopwatch.toString());
    }

    private static Map<Field, Field> targetMapSourceField(List<Field> sourceFields, List<Field> targetFields) {
        Map<Field, Field> fieldFieldMap = Maps.newHashMap();
        for (int i = 0; i < targetFields.size(); i++) {
            fieldFieldMap.put(targetFields.get(i), sourceFields.get(i));
        }
        return fieldFieldMap;
    }

    private static <S> Map<Field, List<Object>> getSourceFieldValues(List<S> beans, List<Field> sourceFields) throws IllegalAccessException {
        Map<Field, List<Object>> sourceValues = Maps.newHashMap();

        // 获取源字段值的列表
        for (int i = 0; i < sourceFields.size(); i++) {
            Field sourceField = sourceFields.get(i);
            List<Object> values = Lists.newArrayList();

            for (S bean : beans) {
                Object sourceVal = FieldUtils.readField(sourceField, bean);
                values.add(sourceVal);
            }
            sourceValues.put(sourceField, values);
        }
        return sourceValues;
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
        List<Field> sourceFields = Lists.newArrayList();
        for (Field targetField : targetFields) {
            JoinField joinField = targetField.getAnnotation(JoinField.class);
            Field sourceField = FieldUtils.getDeclaredField(clazz, joinField.sourcefield(), true);
            if (sourceField == null) {
                log.warn("@JoinField sourcefield:{} 不存在", joinField.sourcefield());
                throw new IllegalArgumentException();
            } else {
                sourceFields.add(sourceField);
            }
        }
        return sourceFields;
    }

    /**
     * 排序Model之中的目标字段
     *
     * @param clazz
     * @param <S>
     * @return
     */
    private static <S> List<Field> sortTargetField(Class<S> clazz) {
        List<Field> fields = FieldUtils.getFieldsListWithAnnotation(clazz, JoinField.class);
        fields.sort((o1, o2) -> {
            JoinField j1 = o1.getAnnotation(JoinField.class);
            JoinField j2 = o2.getAnnotation(JoinField.class);
            return new Integer(j1.order()).compareTo(new Integer(j2.order()));
        });
        return fields;
    }


    public static class BeanField<T> {
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
    }


}
