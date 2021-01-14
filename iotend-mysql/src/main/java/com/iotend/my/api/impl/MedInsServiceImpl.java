package com.iotend.my.api.impl;

import com.iotend.my.api.MedInsService;
import com.iotend.my.mapper.MedInsMapper;
import com.iotend.my.model.MedIns;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedInsServiceImpl implements MedInsService {
    @Autowired
    MedInsMapper medInsMapper;
    @Override
    public int add(MedIns medIns) {
        return medInsMapper.add(medIns);
    }

    @Override
    public List<MedIns> findAll() {
        return medInsMapper.findAll();
    }

    @Override
    public List<MedIns> findById(int id) {
        return medInsMapper.findById(id);
    }
}
