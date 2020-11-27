package com.iotend.security.config;

import com.iotend.security.resolver.ContextArgumentResolver;
import lombok.AllArgsConstructor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 公共配置类, 一些公共工具配置
 *
 * @author huang
 */
@AllArgsConstructor
public class LoginArgResolverConfig implements WebMvcConfigurer {

    /**
     * Token参数解析
     *
     * @param argumentResolvers 解析类
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new ContextArgumentResolver());
    }

}
