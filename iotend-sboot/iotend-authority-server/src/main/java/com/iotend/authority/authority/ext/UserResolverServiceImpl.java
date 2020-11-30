package com.iotend.authority.authority.ext;

import com.iotend.authority.service.auth.UserService;
import com.iotend.base.R;
import com.iotend.security.feign.UserQuery;
import com.iotend.security.feign.UserResolverService;
import com.iotend.security.model.SysUser;

/**
 * 本地 实现
 *
 * @author huang
 */
public class UserResolverServiceImpl implements UserResolverService {
    private final UserService userService;

    public UserResolverServiceImpl(UserService userService) {
        this.userService = userService;
    }


    @Override
    public R<SysUser> getById(Long id, UserQuery userQuery) {
        return R.success(userService.getSysUserById(id, userQuery));
    }
}
