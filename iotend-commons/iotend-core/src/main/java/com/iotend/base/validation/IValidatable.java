package com.iotend.base.validation;

/**
 * @description: 实现了此接口，表示此类将会支持验证框架。
 * @author: huang
 * @create: 2020-11-26 16:06
 */
public interface IValidatable {
    /**
     * 此类需要检验什么值
     * 支持length长度检验。也可以看情况实现支持类似于email，正则等等校验
     *
     * @return
     */
    Object value();
}
