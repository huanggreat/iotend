package com.iotend.injection.core;

import com.iotend.injection.annonation.InjectionField;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author huang
 */
@Data
@AllArgsConstructor
public class FieldParam {
    private InjectionField anno;
    private Serializable queryKey;
    private Object curField;
}
