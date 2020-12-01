package com.iotend.authority.service.common;

import com.iotend.authority.entity.common.Area;
import com.iotend.base.service.SuperCacheService;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 地区表
 * </p>
 *
 * @author huang
 * @date 2019-07-02
 */
public interface AreaService extends SuperCacheService<Area> {

    /**
     * 递归删除
     *
     * @param ids
     * @return
     */
    boolean recursively(List<Long> ids);
}
