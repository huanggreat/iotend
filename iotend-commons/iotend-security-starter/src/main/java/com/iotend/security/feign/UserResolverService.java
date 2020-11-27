package com.iotend.security.feign;

import com.iotend.base.R;
import com.iotend.context.BaseContextHandler;
import com.iotend.security.model.SysUser;

/**
 * @author huang
 */
public interface UserResolverService {
    /**
     * 根据id查询用户
     *
     * @param id
     * @param userQuery
     * @return
     */
    R<SysUser> getById(Long id, UserQuery userQuery);

    /**
     * 查询当前用户的信息
     *
     * @param userQuery
     * @return
     */
    default R<SysUser> getById(UserQuery userQuery) {
        return this.getById(BaseContextHandler.getUserId(), userQuery);
    }
}
