package com.iotend.security.config;

import com.iotend.security.interceptor.ContextHandlerInterceptor;
import com.iotend.security.properties.ContextProperties;
import lombok.AllArgsConstructor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 公共配置类, 一些公共工具配置
 *
 * @author huang
 */
@AllArgsConstructor
public class GlobalMvcConfigurer implements WebMvcConfigurer {

    private ContextProperties contextProperties;

    /**
     * 注册 拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ContextHandlerInterceptor())
                .addPathPatterns(contextProperties.getPathPatterns())
                .order(contextProperties.getOrder())
                .excludePathPatterns(contextProperties.getExcludePatterns());
    }
}
