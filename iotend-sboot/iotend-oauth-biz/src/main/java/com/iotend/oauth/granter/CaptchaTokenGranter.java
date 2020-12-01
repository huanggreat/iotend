package com.iotend.oauth.granter;

import com.iotend.authority.dto.auth.LoginParamDTO;
import com.iotend.base.R;
import com.iotend.context.BaseContextHandler;
import com.iotend.exception.BizException;
import com.iotend.jwt.model.AuthInfo;
import com.iotend.oauth.event.LoginEvent;
import com.iotend.oauth.event.model.LoginStatusDTO;
import com.iotend.oauth.service.ValidateCodeService;
import com.iotend.utils.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.iotend.oauth.granter.CaptchaTokenGranter.GRANT_TYPE;

/**
 * 验证码TokenGranter
 *
 * @author Chill
 */
@Component(CaptchaTokenGranter.GRANT_TYPE)
@Slf4j
public class CaptchaTokenGranter extends AbstractTokenGranter implements TokenGranter {

    public static final String GRANT_TYPE = "captcha";
    @Autowired
    private ValidateCodeService validateCodeService;

    @Override
    public R<AuthInfo> grant(LoginParamDTO loginParam) {
        R<Boolean> check = validateCodeService.check(loginParam.getKey(), loginParam.getCode());
        if (check.getIsError()) {
            String msg = check.getMsg();
            BaseContextHandler.setTenant(loginParam.getTenant());
            SpringUtils.publishEvent(new LoginEvent(LoginStatusDTO.fail(loginParam.getAccount(), msg)));
            throw BizException.validFail(check.getMsg());
        }

        return login(loginParam);
    }

}
