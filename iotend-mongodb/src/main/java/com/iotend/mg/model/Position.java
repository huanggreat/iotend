package com.iotend.mg.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Position implements Serializable {
    public String id;
    public double lng;
    public double lat;
}
