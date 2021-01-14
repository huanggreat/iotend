package com.iotend.my.mapper;

import com.iotend.my.model.MedIns;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MedInsMapper {
    int add(MedIns medIns);
    List<MedIns> findAll();
    List<MedIns> findById(int id);
}
