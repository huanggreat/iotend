package com.iotend.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 属性
 *
 * @author huang
 */
@Data
@ConfigurationProperties(prefix = SecurityProperties.PREFIX)
public class SecurityProperties {
    public final static String PREFIX = "iotend.security";
    /**
     * 是否启用uri权限
     */
    private Boolean enabled = true;
    /**
     * 查询用户信息的调用方式
     */
    private UserType type = UserType.FEIGN;
}
