package com.iotend.oauth.granter;

import com.iotend.authority.dto.auth.LoginParamDTO;
import com.iotend.base.R;
import com.iotend.jwt.model.AuthInfo;
import org.springframework.stereotype.Component;

import static com.iotend.oauth.granter.PasswordTokenGranter.GRANT_TYPE;

/**
 * 账号密码登录获取token
 *
 * @author huang
 * @date 2020年03月31日10:22:55
 */
@Component(PasswordTokenGranter.GRANT_TYPE)
public class PasswordTokenGranter extends AbstractTokenGranter implements TokenGranter {

    public static final String GRANT_TYPE = "password";

    @Override
    public R<AuthInfo> grant(LoginParamDTO tokenParameter) {
        return login(tokenParameter);
    }
}
