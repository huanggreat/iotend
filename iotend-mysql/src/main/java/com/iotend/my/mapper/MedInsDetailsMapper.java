package com.iotend.my.mapper;

import com.iotend.my.model.MedInsDetails;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MedInsDetailsMapper {
    int add(MedInsDetails medInsDetails);
}
