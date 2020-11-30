package com.iotend.authority.authority.config;

import com.iotend.authority.authority.ext.UserResolverServiceImpl;
import com.iotend.authority.interceptor.TokenHandlerInterceptor;
import com.iotend.authority.service.auth.UserService;
import com.iotend.authority.service.common.OptLogService;
import com.iotend.boot.config.BaseConfig;
import com.iotend.common.properties.IgnoreTokenProperties;
import com.iotend.database.properties.DatabaseProperties;
import com.iotend.log.event.SysLogListener;
import com.iotend.security.feign.UserResolverService;
import com.iotend.security.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author huang
 */
@Configuration
@EnableConfigurationProperties({IgnoreTokenProperties.class})
public class AuthorityWebConfiguration extends BaseConfig implements WebMvcConfigurer {

    @Autowired
    private IgnoreTokenProperties ignoreTokenProperties;
    @Autowired
    private DatabaseProperties databaseProperties;

    @Bean
    public HandlerInterceptor getTokenHandlerInterceptor() {
        return new TokenHandlerInterceptor(ignoreTokenProperties, databaseProperties);
    }

    /**
     * 注册 拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] commonPathPatterns = getExcludeCommonPathPatterns();
        registry.addInterceptor(getTokenHandlerInterceptor())
                .addPathPatterns("/**")
                .order(5)
                .excludePathPatterns(commonPathPatterns);
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    /**
     * auth-client 中的拦截器需要排除拦截的地址
     *
     * @return
     */
    protected String[] getExcludeCommonPathPatterns() {
        String[] urls = {
                "/*.css",
                "/*.js",
                "/*.html",
                "/error",
                "/login",
                "/v2/api-docs",
                "/v2/api-docs-ext",
                "/swagger-resources/**",
                "/webjars/**",

                "/",
                "/csrf",

                "/META-INF/resources/**",
                "/resources/**",
                "/static/**",
                "/public/**",
                "classpath:/META-INF/resources/**",
                "classpath:/resources/**",
                "classpath:/static/**",
                "classpath:/public/**",

                "/cache/**",
                "/swagger-ui.html**",
                "/doc.html**"
        };
        return urls;
    }

    @Bean
    @ConditionalOnExpression("${iotend.log.enabled:true} && 'DB'.equals('${iotend.log.type:LOGGER}')")
    public SysLogListener sysLogListener(OptLogService optLogService) {
        return new SysLogListener((log) -> optLogService.save(log));
    }

    @Bean
    @ConditionalOnProperty(prefix = SecurityProperties.PREFIX, name = "type", havingValue = "SERVICE", matchIfMissing = true)
    public UserResolverService getUserResolverServiceImpl(UserService userService) {
        return new UserResolverServiceImpl(userService);
    }

}

