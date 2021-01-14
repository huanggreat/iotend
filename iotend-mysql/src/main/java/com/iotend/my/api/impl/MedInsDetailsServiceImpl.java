package com.iotend.my.api.impl;

import com.iotend.my.api.MedInsDetailsService;
import com.iotend.my.api.MedInsService;
import com.iotend.my.mapper.MedInsDetailsMapper;
import com.iotend.my.model.MedIns;
import com.iotend.my.model.MedInsDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedInsDetailsServiceImpl implements MedInsDetailsService {
    @Autowired
    MedInsDetailsMapper medInsDetailsMapper;
    @Override
    public int add(MedInsDetails medInsDetails) {
        return medInsDetailsMapper.add(medInsDetails);
    }
}
