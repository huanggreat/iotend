package com.iotend.log.event;

import com.iotend.log.entity.OptLogDTO;
import org.springframework.context.ApplicationEvent;

/**
 * 系统日志事件
 *
 * @author huang
 */
public class SysLogEvent extends ApplicationEvent {

    public SysLogEvent(OptLogDTO source) {
        super(source);
    }
}

