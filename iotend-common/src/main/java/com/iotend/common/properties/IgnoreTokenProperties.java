package com.iotend.common.properties;

import cn.hutool.core.collection.CollUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.AntPathMatcher;

import java.util.List;

/**
 * @description: 忽略Token校验类
 * @author: huang
 * @create: 2020-11-30 13:32
 */
@Data
@ConfigurationProperties(prefix = IgnoreTokenProperties.PREFIX)
public class IgnoreTokenProperties {
    public static final String PREFIX = "ignore.token";
    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();
    private List<String> url = CollUtil.newArrayList(
            "/error",
            "/actuator/**",
            "/gate/**",
            "/static/**",
            "/anno/**",
            "/**/anno/**",
            "/**/swagger-ui.html",
            "/**/doc.html",
            "/**/webjars/**",
            "/**/v2/api-docs/**",
            "/**/v2/api-docs-ext/**",
            "/**/swagger-resources/**"
    );

    public boolean isIgnoreToken(String path) {
        return getUrl().stream().anyMatch((url) -> path.startsWith(url) || ANT_PATH_MATCHER.match(url, path));
    }
}
