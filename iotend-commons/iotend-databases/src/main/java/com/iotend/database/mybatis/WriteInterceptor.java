package com.iotend.database.mybatis;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.handlers.AbstractSqlParserHandler;
import com.iotend.context.BaseContextHandler;
import com.iotend.exception.BizException;
import com.iotend.utils.SpringUtils;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.util.Arrays;
import java.util.Properties;

import static org.apache.ibatis.mapping.SqlCommandType.*;

/**
 * 演示环境写权限控制 拦截器
 * 该拦截器只用于演示环境， 开发和生产都不需要
 * <p>
 *
 * @author huang
 */
@Slf4j
@NoArgsConstructor
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class WriteInterceptor extends AbstractSqlParserHandler implements Interceptor {


    @Override
    @SneakyThrows
    public Object intercept(Invocation invocation) {
        // 为什么在拦截器里使用 @RefreshScope 无效？
        if (SpringUtils.getApplicationContext() == null) {
            return invocation.proceed();
        }
        if (!SpringUtils.getApplicationContext().getEnvironment().getProperty("iotend.database.isNotWrite", Boolean.class, false)) {
            return invocation.proceed();
        }

        StatementHandler statementHandler = (StatementHandler) PluginUtils.realTarget(invocation.getTarget());
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        sqlParser(metaObject);
        // 读操作 放行
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        if (SqlCommandType.SELECT.equals(mappedStatement.getSqlCommandType())) {
            return invocation.proceed();
        }
        // 记录日志相关的 放行
        if (StrUtil.containsAnyIgnoreCase(mappedStatement.getId(), "uid", "UserToken", "resetPassErrorNum", "updateLastLoginTime")) {
//        if (StrUtil.containsAnyIgnoreCase(mappedStatement.getId(), "UserToken", "sms", "MsgsCenterInfo", "resetPassErrorNum", "updateLastLoginTime", "OptLog", "LoginLog", "File", "xxl")) {
            return invocation.proceed();
        }
        // userId=1 的超级管理员 放行
        Long userId = BaseContextHandler.getUserId();
        String tenant = BaseContextHandler.getTenant();
        log.info("mapperid={}, userId={}", mappedStatement.getId(), userId);


        //演示用的超级管理员 能查 和 增
        if (userId == 2 && (DELETE.equals(mappedStatement.getSqlCommandType()))) {
            throw new BizException(-1, "演示环境，无删除权限，请本地部署后测试");
        }

        //内置的租户 不能 修改、删除 权限数据
        boolean isAuthority = StrUtil.containsAnyIgnoreCase(mappedStatement.getId(), "Tenant", "GlobalUser", "User", "Menu", "Resource", "Role", "Dictionary", "Parameter", "Application");
        boolean isWrite = CollectionUtil.contains(Arrays.asList(DELETE, UPDATE, INSERT), mappedStatement.getSqlCommandType());
        if ("0000".equals(tenant) && isWrite && isAuthority) {
            throw new BizException(-1, "演示环境禁止修改、删除重要数据！请在 iotend-admin-ui 使用其他租户登录后测试 全部功能");
        }

        // 你还可以自定义其他限制规则， 比如：IP 等

        //放行
        return invocation.proceed();
    }

    /**
     * 生成拦截对象的代理
     *
     * @param target 目标对象
     * @return 代理对象
     */
    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    /**
     * mybatis配置的属性
     *
     * @param properties mybatis配置的属性
     */
    @Override
    public void setProperties(Properties properties) {

    }

}

