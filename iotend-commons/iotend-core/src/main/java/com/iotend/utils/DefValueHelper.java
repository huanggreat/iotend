package com.iotend.utils;

import cn.hutool.core.util.StrUtil;

import java.io.Serializable;

/**
 * @description: 默认值
 * @author: huang
 * @create: 2020-11-26 16:49
 */
public class DefValueHelper {
    public static String getOrDef(String val, String def) {
        return StrUtil.isEmpty(val) ? def : val;
    }

    public static <T extends Serializable> T getOrDef(T val, T def) {
        return val == null ? def : val;
    }

}
