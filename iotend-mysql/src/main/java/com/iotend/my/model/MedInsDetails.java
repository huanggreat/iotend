package com.iotend.my.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

@Data
public class MedInsDetails implements Serializable {
    @JSONField(serialize = false)
    public String id;
    public String registrant;
    public String specificationCode;
    public String catalogname1;
    public String catalogname2;
    public String catalogname3;
    public String commonname;
    public String matrial;
    public String characteristic;
    public String regcardnm;
    public String productName;
    public String companyName;
    public int releaseVersion;
    public String ggxhCount;
}
