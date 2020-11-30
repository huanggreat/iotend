package com.iotend.authority.controller.auth;

import com.iotend.authority.dto.auth.UserTokenPageDTO;
import com.iotend.authority.dto.auth.UserTokenSaveDTO;
import com.iotend.authority.dto.auth.UserTokenUpdateDTO;
import com.iotend.authority.entity.auth.UserToken;
import com.iotend.authority.service.auth.UserTokenService;
import com.iotend.base.controller.SuperController;
import com.iotend.security.annotation.PreAuth;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 前端控制器
 * token
 * </p>
 *
 * @author zuihou
 * @date 2020-04-02
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/userToken")
@Api(value = "UserToken", tags = "token")
@PreAuth(replace = "userToken:")
public class UserTokenController extends SuperController<UserTokenService, Long, UserToken, UserTokenPageDTO, UserTokenSaveDTO, UserTokenUpdateDTO> {


}
