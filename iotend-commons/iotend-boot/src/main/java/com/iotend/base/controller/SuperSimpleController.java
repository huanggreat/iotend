package com.iotend.base.controller;

import com.iotend.base.service.SuperService;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;

/**
 * 简单的实现了BaseController，为了获取注入 Service 和 实体类型
 * <p>
 * 基类该类后，没有任何方法。
 * 可以让业务Controller继承 SuperSimpleController 后，按需实现 *Controller 接口
 *
 * @param <S>      Service
 * @param <Entity> 实体
 * @author huang
 */
public abstract class SuperSimpleController<S extends SuperService<Entity>, Entity> implements BaseController<Entity> {

    protected Class<Entity> entityClass = null;
    @Autowired
    protected S baseService;

    @Override
    public Class<Entity> getEntityClass() {
        if (entityClass == null) {
            this.entityClass = (Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        }
        return this.entityClass;
    }

    @Override
    public SuperService<Entity> getBaseService() {
        return baseService;
    }
}
