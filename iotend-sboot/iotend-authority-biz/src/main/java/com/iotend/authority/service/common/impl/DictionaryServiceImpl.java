package com.iotend.authority.service.common.impl;


import com.iotend.authority.dao.common.DictionaryMapper;
import com.iotend.authority.entity.common.Dictionary;
import com.iotend.authority.service.common.DictionaryService;
import com.iotend.base.service.SuperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务实现类
 * 字典类型
 * </p>
 *
 * @author huang
 * @date 2019-07-02
 */
@Slf4j
@Service

public class DictionaryServiceImpl extends SuperServiceImpl<DictionaryMapper, Dictionary> implements DictionaryService {

}
