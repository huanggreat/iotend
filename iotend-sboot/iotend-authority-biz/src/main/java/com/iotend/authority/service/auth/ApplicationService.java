package com.iotend.authority.service.auth;

import com.iotend.authority.entity.auth.Application;
import com.iotend.base.service.SuperCacheService;

/**
 * <p>
 * 业务接口
 * 应用
 * </p>
 *
 * @author zuihou
 * @date 2019-12-15
 */
public interface ApplicationService extends SuperCacheService<Application> {
    /**
     * 根据 clientid 和 clientSecret 查询
     *
     * @param clientId
     * @param clientSecret
     * @return
     */
    Application getByClient(String clientId, String clientSecret);
}
