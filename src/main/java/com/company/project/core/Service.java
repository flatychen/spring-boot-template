package com.company.project.core;

import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
 * Service 层 基础接口，其他Service 接口 请继承该接口
 */
public interface Service<T> {

    void save(T model);//持久化

    void save(List<T> models);//批量持久化

    void deleteById(Integer id);//通过主鍵刪除

    void deleteByIds(List<Integer> ids);//批量刪除

    void update(T model);//更新

    T getById(Integer id);//通过ID查找


    List<T> findByIds(List<Integer> ids);//通过多个ID查找


    List<T> selectList(T model);//通过多个ID查找

    List<T> findByCondition(Condition condition);//根据条件查找

    List<T> findAll();//获取所有
}
