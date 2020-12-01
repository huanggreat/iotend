package com.iotend.authority.service.auth;

import com.iotend.authority.entity.auth.UserToken;
import com.iotend.base.service.SuperService;

/**
 * <p>
 * 业务接口
 * token
 * </p>
 *
 * @author huang
 * @date 2020-04-02
 */
public interface UserTokenService extends SuperService<UserToken> {
    /**
     * 重置用户登录
     *
     * @return
     */
    boolean reset();
}
