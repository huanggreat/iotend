package com.iotend.authority.service.auth.impl;

import cn.hutool.core.convert.Convert;
import com.iotend.authority.dao.auth.ApplicationMapper;
import com.iotend.authority.entity.auth.Application;
import com.iotend.authority.service.auth.ApplicationService;
import com.iotend.base.service.SuperCacheServiceImpl;
import com.iotend.database.mybatis.conditions.Wraps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Function;

import static com.iotend.common.constant.CacheKey.*;

/**
 * <p>
 * 业务实现类
 * 应用
 * </p>
 *
 * @author huang
 * @date 2019-12-15
 */
@Slf4j
@Service

public class ApplicationServiceImpl extends SuperCacheServiceImpl<ApplicationMapper, Application> implements ApplicationService {

    @Override
    protected String getRegion() {
        return APPLICATION;
    }

    @Override
    public Application getByClient(String clientId, String clientSecret) {
        String key = buildTenantKey(clientId, clientSecret);
        Function<String, Object> loader = (k) -> super.getObj(Wraps.<Application>lbQ()
                .select(Application::getId).eq(Application::getClientId, clientId).eq(Application::getClientSecret, clientSecret), Convert::toLong);
        return getByKey(APPLICATION_CLIENT, key, loader);
    }

}
