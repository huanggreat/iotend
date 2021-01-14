package com.iotend.my.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 医疗器械
 * */
@Data
public class MedIns implements Serializable {
    public int id;
    /*
     * 医用耗材代码
     */
    public String specificationCode;
    /*
     * 一级分类（学科、品类）
     */
    public String catalogname1;
    /*
     * 二级分类（用途、品目）
     */
    public String catalogname2;
    /*
     * 三级分类（部位、功能、品种）
     */
    public String catalogname3;
    /**
     * 医保通用名
     * */
    public String commonname;
    /**
     * 耗材材质
     * */
    public String matrial;
    /**
     * 规格（特征、参数）
     * */
    public String characteristic;

    public String regcardnm;
    /**
     * 耗材生产企业
     * */
    public String productName;
    public String companyName;
    public int releaseVersion;
    public String ggxhCount;
}
