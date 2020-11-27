package com.iotend.security.config;

import com.iotend.security.aspect.UriSecurityPreAuthAspect;
import com.iotend.security.aspect.VerifyAuthFunction;
import com.iotend.security.feign.UserResolverService;
import com.iotend.security.properties.ContextProperties;
import com.iotend.security.properties.SecurityProperties;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

/**
 * 权限认证配置类
 *
 * @author huang
 */
@Order
@AllArgsConstructor
@EnableConfigurationProperties({SecurityProperties.class, ContextProperties.class})
public class SecurityConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = SecurityProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
    public UriSecurityPreAuthAspect uriSecurityPreAuthAspect(VerifyAuthFunction verifyAuthFunction) {
        return new UriSecurityPreAuthAspect(verifyAuthFunction);
    }

    @Bean("fun")
    @ConditionalOnMissingBean(VerifyAuthFunction.class)
    public VerifyAuthFunction getVerifyAuthFunction(UserResolverService userResolverService) {
        return new VerifyAuthFunction(userResolverService);
    }

    @Bean
    public GlobalMvcConfigurer getGlobalMvcConfigurer(ContextProperties contextProperties) {
        return new GlobalMvcConfigurer(contextProperties);
    }

}

