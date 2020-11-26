package com.iotend.function;

/**
 * @description: 处理异常的函数
 * @author: huang
 * @create: 2020-11-26 16:30
 */
@FunctionalInterface
public interface CheckedFunction<T, R> {
    /**
     * 执行
     *
     * @param t 入参
     * @return R 出参
     * @throws Exception
     */
    R apply(T t) throws Exception;


}