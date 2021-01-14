package com.iotend.my.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Nhsa implements Serializable {
    public String records;
    public String total;
    public int page;
    public int count;
    public int firstResult;
    public int maxResults;
    public boolean success;
    public String result;
    public String msg;
    public int code;
    public int operCount;
    public List<MedIns> rows;
}
