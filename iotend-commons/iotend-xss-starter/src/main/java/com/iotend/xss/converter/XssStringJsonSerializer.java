package com.iotend.xss.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.iotend.xss.utils.XssUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * 基于xss的 json 序列化器
 * 在本项目中，没有使用该类
 *
 * @author huang
 */
@Slf4j
public class XssStringJsonSerializer extends JsonSerializer<String> {
    @Override
    public Class<String> handledType() {
        return String.class;
    }

    @Override
    public void serialize(String value, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        if (value != null) {
            try {
                String encodedValue = XssUtils.xssClean(value, null);
                jsonGenerator.writeString(encodedValue);
            } catch (Exception e) {
                log.error("序列化失败:[{}]", value, e);
            }
        }
    }

}
