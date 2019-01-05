package com.company.project.core.join;

import java.util.List;

/**
 * 用于服务端join的接口
 */
public interface ServiceJoinable<T> {

    List<T> findByIds(List<Object> ids);

}
