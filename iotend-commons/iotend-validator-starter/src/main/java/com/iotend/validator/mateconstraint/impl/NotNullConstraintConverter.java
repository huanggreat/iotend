package com.iotend.validator.mateconstraint.impl;

import com.iotend.validator.mateconstraint.IConstraintConverter;

import javax.validation.constraints.*;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

/**
 * 非空 转换器
 *
 */
public class NotNullConstraintConverter extends BaseConstraintConverter implements IConstraintConverter {


    @Override
    protected String getType(Class<? extends Annotation> type) {
        return "notNull";
    }

    @Override
    protected List<Class<? extends Annotation>> getSupport() {
        return Arrays.asList(NotNull.class, NotEmpty.class, NotBlank.class);
    }

    @Override
    protected List<String> getMethods() {
        return Arrays.asList("message");
    }
}