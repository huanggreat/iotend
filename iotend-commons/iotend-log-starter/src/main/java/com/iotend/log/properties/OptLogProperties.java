package com.iotend.log.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import static com.iotend.log.properties.OptLogProperties.PREFIX;

/**
 * 操作日志配置类
 *
 * @author huang
 */
@ConfigurationProperties(prefix = PREFIX)
@Data
@NoArgsConstructor
public class OptLogProperties {
    public static final String PREFIX = "iotend.log";

    /**
     * 是否启用
     */
    private Boolean enabled = true;

    /**
     * 日志存储类型
     */
    private OptLogType type = OptLogType.DB;
}