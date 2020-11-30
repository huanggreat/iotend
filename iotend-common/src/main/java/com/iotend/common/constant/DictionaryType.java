package com.iotend.common.constant;

/**
 * @description:存放系统中常用的类型
 * @author: huang
 * @create: 2020-11-30 13:27
 */
public class DictionaryType {
    /**
     * 职位状态
     */
    public static final String POSITION_STATUS = "POSITION_STATUS";
    /**
     * 民族
     */
    public static final String NATION = "NATION";
    /**
     * 学历
     */
    public static final String EDUCATION = "EDUCATION";
    /**
     * 行政区级
     */
    public static final String AREA_LEVEL = "AREA_LEVEL";
    /**
     * 机构类型
     */
    public static final String ORG_TYPE = "ORG_TYPE";
    public static final String[] ALL = new String[]{
            EDUCATION, NATION, POSITION_STATUS, AREA_LEVEL, ORG_TYPE
    };

    private DictionaryType() {
    }
}
