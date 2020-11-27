package com.iotend.security.resolver;

import com.iotend.base.R;
import com.iotend.context.BaseContextHandler;
import com.iotend.security.annotation.LoginUser;
import com.iotend.security.feign.UserQuery;
import com.iotend.security.feign.UserResolverService;
import com.iotend.security.model.SysUser;
import com.iotend.utils.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Token转化SysUser
 *
 * @author huang
 */
@Slf4j
public class ContextArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     * 入参筛选
     *
     * @param mp 参数集合
     * @return 格式化后的参数
     */
    @Override
    public boolean supportsParameter(MethodParameter mp) {
        return mp.hasParameterAnnotation(LoginUser.class) && mp.getParameterType().equals(SysUser.class);
    }

    /**
     * @param methodParameter       入参集合
     * @param modelAndViewContainer model 和 view
     * @param nativeWebRequest      web相关
     * @param webDataBinderFactory  入参解析
     * @return 包装对象
     */
    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) {
        Long userId = BaseContextHandler.getUserId();
        String account = BaseContextHandler.getAccount();
        String name = BaseContextHandler.getName();

        //以下代码为 根据 @LoginUser 注解来注入 SysUser 对象
        SysUser user = SysUser.builder()
                .id(userId)
                .account(account)
                .name(name)
                .build();
        if (userId == null) {
            return user;
        }
        try {
            LoginUser loginUser = methodParameter.getParameterAnnotation(LoginUser.class);
            boolean isQuery = loginUser.isFull() || loginUser.isStation() || loginUser.isOrg() || loginUser.isRoles() || loginUser.isResource();
            if (isQuery) {
                UserResolverService userResolverService = SpringUtils.getBean(UserResolverService.class);
                R<SysUser> result = userResolverService.getById(userId,
                        UserQuery.builder()
                                .full(loginUser.isFull())
                                .org(loginUser.isOrg())
                                .station(loginUser.isStation())
                                .roles(loginUser.isRoles())
                                .resource(loginUser.isResource())
                                .build());
                if (result.getIsSuccess() && result.getData() != null) {
                    return result.getData();
                }
            }
        } catch (Exception e) {
            log.warn("注入登录人信息时，发生异常. --> {}", user, e);
        }

        return user;
    }
}
