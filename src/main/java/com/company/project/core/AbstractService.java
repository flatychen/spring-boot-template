package com.company.project.core;


import com.company.project.core.join.ServiceJoinable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Condition;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * 基于通用MyBatis Mapper插件的Service接口的实现
 */
public abstract class AbstractService<T> implements Service<T>, ServiceJoinable<T> {

    @Autowired
    protected MyMapper<T> mapper;

    private Class<T> modelClass;    // 当前泛型真实类型的Class

    public AbstractService() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<T>) pt.getActualTypeArguments()[0];
    }

    public void save(T model) {
        mapper.insertSelective(model);
    }

    public void save(List<T> models) {
        mapper.insertList(models);
    }

    public void deleteById(Integer id) {
        mapper.deleteByPrimaryKey(id);
    }

    public void deleteByIds(List<Integer> ids) {
        mapper.deleteByIds(StringUtils.join(ids,","));
    }

    public void update(T model) {
        mapper.updateByPrimaryKeySelective(model);
    }

    public T getById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }


    @Override
    public List<T> findByIds(List<Integer> ids) {
        return mapper.selectByIds(StringUtils.join(ids.toArray(),","));
    }

    @Override
    public List<T> selectList(T model) {
        return mapper.select(model);
    }

    public List<T> findByCondition(Condition condition) {
        return mapper.selectByCondition(condition);
    }

    public List<T> findAll() {
        return mapper.selectAll();
    }


}
