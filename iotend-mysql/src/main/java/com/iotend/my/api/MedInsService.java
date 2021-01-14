package com.iotend.my.api;

import com.iotend.my.model.MedIns;

import java.util.List;

public interface MedInsService {
    int add(MedIns medIns);
    List<MedIns> findAll();
    List<MedIns> findById(int id);
}
