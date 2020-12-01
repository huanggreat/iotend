package com.iotend.authority.service.common;

import com.iotend.authority.entity.common.OptLog;
import com.iotend.base.service.SuperService;
import com.iotend.log.entity.OptLogDTO;

import java.time.LocalDateTime;

/**
 * <p>
 * 业务接口
 * 系统日志
 * </p>
 *
 * @author huang
 * @date 2019-07-02
 */
public interface OptLogService extends SuperService<OptLog> {

    /**
     * 保存日志
     *
     * @param entity
     * @return
     */
    boolean save(OptLogDTO entity);

    /**
     * 清理日志
     *
     * @param clearBeforeTime 多久之前的
     * @param clearBeforeNum  多少条
     * @return
     */
    boolean clearLog(LocalDateTime clearBeforeTime, Integer clearBeforeNum);
}
