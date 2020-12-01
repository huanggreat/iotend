package com.iotend.authority.controller.auth;


import cn.hutool.core.util.RandomUtil;
import com.iotend.authority.dto.auth.ApplicationPageDTO;
import com.iotend.authority.dto.auth.ApplicationSaveDTO;
import com.iotend.authority.dto.auth.ApplicationUpdateDTO;
import com.iotend.authority.entity.auth.Application;
import com.iotend.authority.service.auth.ApplicationService;
import com.iotend.base.R;
import com.iotend.base.controller.SuperCacheController;
import com.iotend.security.annotation.PreAuth;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 应用
 * </p>
 *
 * @author huang
 * @date 2019-12-15
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/application")
@Api(value = "Application", tags = "应用")
@PreAuth(replace = "application:")
public class ApplicationController extends SuperCacheController<ApplicationService, Long, Application, ApplicationPageDTO, ApplicationSaveDTO, ApplicationUpdateDTO> {

    @Override
    public R<Application> handlerSave(ApplicationSaveDTO applicationSaveDTO) {
        applicationSaveDTO.setClientId(RandomUtil.randomString(24));
        applicationSaveDTO.setClientSecret(RandomUtil.randomString(32));
        return super.handlerSave(applicationSaveDTO);
    }

}
