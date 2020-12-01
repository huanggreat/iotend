package com.iotend.authority.controller.common;


import com.iotend.authority.dto.common.ParameterPageDTO;
import com.iotend.authority.dto.common.ParameterSaveDTO;
import com.iotend.authority.dto.common.ParameterUpdateDTO;
import com.iotend.authority.entity.common.Parameter;
import com.iotend.authority.service.common.ParameterService;
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
 * 参数配置
 * </p>
 *
 * @author huang
 * @date 2020-02-05
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/parameter")
@Api(value = "Parameter", tags = "参数配置")
@PreAuth(replace = "parameter:")
public class ParameterController extends SuperController<ParameterService, Long, Parameter, ParameterPageDTO, ParameterSaveDTO, ParameterUpdateDTO> {

}
