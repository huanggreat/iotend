package com.iotend.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.iotend.base.BaseEnum;

import java.io.IOException;

public class EnumSerializer extends StdSerializer<BaseEnum> {
    public final static EnumSerializer INSTANCE = new EnumSerializer();
    public final static String ALL_ENUM_KEY_FIELD = "code";
    public final static String ALL_ENUM_DESC_FIELD = "desc";

    public EnumSerializer() {
        super(BaseEnum.class);
    }

    @Override
    public void serialize(BaseEnum distance, JsonGenerator generator, SerializerProvider provider)
            throws IOException {
        generator.writeStartObject();
        generator.writeFieldName(ALL_ENUM_KEY_FIELD);
        generator.writeString(distance.getCode());
        generator.writeFieldName(ALL_ENUM_DESC_FIELD);
        generator.writeString(distance.getDesc());
        generator.writeEndObject();
    }
}
