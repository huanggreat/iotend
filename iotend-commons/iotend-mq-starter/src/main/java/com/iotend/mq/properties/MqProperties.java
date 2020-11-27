package com.iotend.mq.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 操作日志配置类
 *
 * @author huang
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = MqProperties.PREFIX)
public class MqProperties {
    public static final String PREFIX = "iotend.rabbitmq";

    /**
     * 是否启用
     */
    private Boolean enabled = true;

}
