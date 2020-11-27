package com.iotend.security.properties;

/**
 * 调用用户信息的类型
 *
 * @author huang
 */
public enum UserType {
    /**
     * feign 远程调用
     */
    FEIGN,
    /**
     * Service 本地调用
     */
    SERVICE,
    ;
}
