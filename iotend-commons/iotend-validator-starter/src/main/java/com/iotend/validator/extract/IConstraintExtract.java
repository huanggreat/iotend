package com.iotend.validator.extract;

import com.iotend.validator.model.FieldValidatorDesc;
import com.iotend.validator.model.ValidConstraint;

import java.util.Collection;
import java.util.List;

/**
 * 提取指定表单验证规则
 *
 */
public interface IConstraintExtract {

    /**
     * 提取指定表单验证规则
     *
     * @param constraints
     * @return
     * @throws Exception
     */
    Collection<FieldValidatorDesc> extract(List<ValidConstraint> constraints) throws Exception;
}