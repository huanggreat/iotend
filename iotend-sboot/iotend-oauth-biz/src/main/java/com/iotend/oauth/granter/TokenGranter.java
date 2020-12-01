package com.iotend.oauth.granter;


import com.iotend.authority.dto.auth.LoginParamDTO;
import com.iotend.base.R;
import com.iotend.jwt.model.AuthInfo;

/**
 * 授权认证统一接口.
 *
 * @author huang
 * @date 2020年03月31日10:21:21
 */
public interface TokenGranter {

    /**
     * 获取用户信息
     *
     * @param loginParam 授权参数
     * @return LoginDTO
     */
    R<AuthInfo> grant(LoginParamDTO loginParam);

}
