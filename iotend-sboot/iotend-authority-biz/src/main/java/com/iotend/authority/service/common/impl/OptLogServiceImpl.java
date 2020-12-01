package com.iotend.authority.service.common.impl;


import com.iotend.authority.dao.common.OptLogMapper;
import com.iotend.authority.entity.common.OptLog;
import com.iotend.authority.service.common.OptLogService;
import com.iotend.base.service.SuperServiceImpl;
import com.iotend.log.entity.OptLogDTO;
import com.iotend.utils.BeanPlusUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 业务实现类
 * 系统日志
 * </p>
 *
 * @author huang
 * @date 2019-07-02
 */
@Slf4j
@Service

public class OptLogServiceImpl extends SuperServiceImpl<OptLogMapper, OptLog> implements OptLogService {

    @Override
    public boolean save(OptLogDTO entity) {
        return super.save(BeanPlusUtil.toBean(entity, OptLog.class));
    }

    @Override
    public boolean clearLog(LocalDateTime clearBeforeTime, Integer clearBeforeNum) {
        return baseMapper.clearLog(clearBeforeTime, clearBeforeNum);
    }
}
