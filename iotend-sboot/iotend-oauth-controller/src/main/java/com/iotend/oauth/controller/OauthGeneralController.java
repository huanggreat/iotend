package com.iotend.oauth.controller;

import cn.hutool.core.util.ArrayUtil;
import com.iotend.authority.enumeration.auth.ApplicationAppTypeEnum;
import com.iotend.authority.enumeration.auth.AuthorizeType;
import com.iotend.authority.enumeration.auth.Sex;
import com.iotend.authority.enumeration.common.LogType;
import com.iotend.authority.service.common.DictionaryItemService;
import com.iotend.authority.service.common.ParameterService;
import com.iotend.base.BaseEnum;
import com.iotend.base.R;
import com.iotend.common.enums.HttpMethod;
import com.iotend.database.mybatis.auth.DataScopeType;

import com.iotend.tenant.enumeration.TenantStatusEnum;
import com.iotend.tenant.enumeration.TenantTypeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用 控制器
 *
 * @author zuihou
 * @date 2019/07/25
 */
@Slf4j
@RestController
@Api(value = "Common", tags = "通用Controller")
@AllArgsConstructor
public class OauthGeneralController {
    private final DictionaryItemService dictionaryItemService;
    private final ParameterService parameterService;

    private final static Map<String, Map<String, String>> ENUM_MAP = new HashMap<>(8);

    static {
        // 权限服务
        ENUM_MAP.put(HttpMethod.class.getSimpleName(), BaseEnum.getMap(HttpMethod.values()));
        ENUM_MAP.put(DataScopeType.class.getSimpleName(), BaseEnum.getMap(DataScopeType.values()));
        ENUM_MAP.put(LogType.class.getSimpleName(), BaseEnum.getMap(LogType.values()));
        ENUM_MAP.put(AuthorizeType.class.getSimpleName(), BaseEnum.getMap(AuthorizeType.values()));
        ENUM_MAP.put(Sex.class.getSimpleName(), BaseEnum.getMap(Sex.values()));
        ENUM_MAP.put(TenantTypeEnum.class.getSimpleName(), BaseEnum.getMap(TenantTypeEnum.values()));
        ENUM_MAP.put(TenantStatusEnum.class.getSimpleName(), BaseEnum.getMap(TenantStatusEnum.values()));
        ENUM_MAP.put(ApplicationAppTypeEnum.class.getSimpleName(), BaseEnum.getMap(ApplicationAppTypeEnum.values()));
        // 文件服务
        //ENUM_MAP.put(DataType.class.getSimpleName(), BaseEnum.getMap(HttpMethod.values()));
        //消息服务
        //ENUM_MAP.put(MsgsCenterType.class.getSimpleName(), BaseEnum.getMap(MsgsCenterType.values()));
       // ENUM_MAP.put(MsgsBizType.class.getSimpleName(), BaseEnum.getMap(MsgsBizType.values()));
        //ENUM_MAP.put(ProviderType.class.getSimpleName(), BaseEnum.getMap(ProviderType.values()));
        //ENUM_MAP.put(SourceType.class.getSimpleName(), BaseEnum.getMap(SourceType.values()));
        //ENUM_MAP.put(SendStatus.class.getSimpleName(), BaseEnum.getMap(SendStatus.values()));
        //ENUM_MAP.put(TaskStatus.class.getSimpleName(), BaseEnum.getMap(TaskStatus.values()));
    }

    @ApiOperation(value = "获取当前系统指定枚举", notes = "获取当前系统指定枚举")
    @GetMapping("/enums")
    public R<Map<String, Map<String, String>>> enums(@RequestParam(value = "codes[]", required = false) String[] codes) {
        if (ArrayUtil.isEmpty(codes)) {
            return R.success(ENUM_MAP);
        }

        Map<String, Map<String, String>> map = new HashMap<>(codes.length);

        for (String code : codes) {
            if (ENUM_MAP.containsKey(code)) {
                map.put(code, ENUM_MAP.get(code));
            }
        }
        return R.success(map);
    }


    @ApiOperation(value = "根据类型编码查询字典项", notes = "根据类型编码查询字典项")
    @GetMapping("/dictionaryItem/codes")
    public R<Map<String, Map<String, String>>> list(@RequestParam("codes[]") String[] types) {
        return R.success(this.dictionaryItemService.map(types));
    }

    @GetMapping("/parameter/value")
    public R<String> getValue(@RequestParam(value = "key") String key, @RequestParam(value = "defVal") String defVal) {
        return R.success(parameterService.getValue(key, defVal));
    }
}

