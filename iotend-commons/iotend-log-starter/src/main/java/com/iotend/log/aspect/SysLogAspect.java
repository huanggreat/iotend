package com.iotend.log.aspect;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.iotend.base.R;
import com.iotend.context.BaseContextConstants;
import com.iotend.context.BaseContextHandler;
import com.iotend.jackson.JsonUtil;
import com.iotend.log.annotation.SysLog;
import com.iotend.log.entity.OptLogDTO;
import com.iotend.log.event.SysLogEvent;
import com.iotend.log.util.LogUtil;
import com.iotend.utils.SpringUtils;
import com.iotend.utils.StrPool;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * 操作日志使用spring event异步入库
 *
 * @author huang
 */
@Slf4j
@Aspect
public class SysLogAspect {

    public static final int MAX_LENGTH = 65535;
    private static final ThreadLocal<OptLogDTO> THREAD_LOCAL = new ThreadLocal<>();
    /**
     * 用于SpEL表达式解析.
     */
    private SpelExpressionParser spelExpressionParser = new SpelExpressionParser();
    /**
     * 用于获取方法参数定义名字.
     */
    private DefaultParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();

    /***
     * 定义controller切入点拦截规则：拦截标记SysLog注解和指定包下的方法
     * 2个表达式加起来才能拦截所有Controller 或者继承了BaseController的方法
     *
     * execution(public * com.iotend.base.controller.*.*(..)) 解释：
     * 第一个* 任意返回类型
     * 第二个* com.iotend.base.controller包下的所有类
     * 第三个* 类下的所有方法
     * ()中间的.. 任意参数
     *
     * \@annotation(com.iotend.log.annotation.SysLog) 解释：
     * 标记了@SysLog 注解的方法
     */
    @Pointcut("execution(public * com.iotend.base.controller.*.*(..)) || @annotation(com.iotend.log.annotation.SysLog)")
    public void sysLogAspect() {

    }

    /**
     * 返回通知
     *
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "sysLogAspect()")
    public void doAfterReturning(JoinPoint joinPoint, Object ret) {
        tryCatch((aaa) -> {
            SysLog sysLogAnno = LogUtil.getTargetAnno(joinPoint);
            if (check(joinPoint, sysLogAnno)) {
                return;
            }

            R r = Convert.convert(R.class, ret);
            OptLogDTO sysLog = get();
            if (r == null) {
                sysLog.setType("OPT");
                if (sysLogAnno.response()) {
                    sysLog.setResult(getText(String.valueOf(ret == null ? StrPool.EMPTY : ret)));
                }
            } else {
                if (r.getIsSuccess()) {
                    sysLog.setType("OPT");
                } else {
                    sysLog.setType("EX");
                    sysLog.setExDetail(r.getMsg());
                }
                if (sysLogAnno.response()) {
                    sysLog.setResult(getText(r.toString()));
                }
            }

            publishEvent(sysLog);
        });

    }


    /**
     * 异常通知
     *
     * @param e
     */
    @AfterThrowing(pointcut = "sysLogAspect()", throwing = "e")
    public void doAfterThrowable(JoinPoint joinPoint, Throwable e) {
        tryCatch((aaa) -> {
            SysLog sysLogAnno = LogUtil.getTargetAnno(joinPoint);
            if (check(joinPoint, sysLogAnno)) {
                return;
            }

            OptLogDTO sysLog = get();
            sysLog.setType("EX");

            // 遇到错误时，请求参数若为空，则记录
            if (!sysLogAnno.request() && sysLogAnno.requestByError() && StrUtil.isEmpty(sysLog.getParams())) {
                Object[] args = joinPoint.getArgs();
                HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
                String strArgs = getArgs(sysLogAnno, args, request);
                sysLog.setParams(getText(strArgs));
            }

            // 异常对象
            sysLog.setExDetail(ExceptionUtil.stacktraceToString(e, MAX_LENGTH));
            // 异常信息
            sysLog.setExDesc(ExceptionUtil.stacktraceToString(e, MAX_LENGTH));

            publishEvent(sysLog);
        });
    }

    @Before(value = "sysLogAspect()")
    public void recordLog(JoinPoint joinPoint) throws Throwable {
        tryCatch((val) -> {
            SysLog sysLogAnno = LogUtil.getTargetAnno(joinPoint);
            if (check(joinPoint, sysLogAnno)) {
                return;
            }

            // 开始时间
            OptLogDTO sysLog = get();
            sysLog.setCreateUser(BaseContextHandler.getUserId());
            sysLog.setUserName(BaseContextHandler.getName());
            String controllerDescription = "";
            Api api = joinPoint.getTarget().getClass().getAnnotation(Api.class);
            if (api != null) {
                String[] tags = api.tags();
                if (tags != null && tags.length > 0) {
                    controllerDescription = tags[0];
                }
            }

            String controllerMethodDescription = LogUtil.getDescribe(sysLogAnno);

            if (StrUtil.isNotEmpty(controllerMethodDescription) && StrUtil.contains(controllerMethodDescription, StrPool.HASH)) {
                //获取方法参数值
                Object[] args = joinPoint.getArgs();

                MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
                controllerMethodDescription = getValBySpEL(controllerMethodDescription, methodSignature, args);
            }

            if (StrUtil.isEmpty(controllerDescription)) {
                sysLog.setDescription(controllerMethodDescription);
            } else {
                if (sysLogAnno.controllerApiValue()) {
                    sysLog.setDescription(controllerDescription + "-" + controllerMethodDescription);
                } else {
                    sysLog.setDescription(controllerMethodDescription);
                }
            }

            // 类名
            sysLog.setClassPath(joinPoint.getTarget().getClass().getName());
            //获取执行的方法名
            sysLog.setActionMethod(joinPoint.getSignature().getName());

            // 参数
            Object[] args = joinPoint.getArgs();

            HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
            if (sysLogAnno.request()) {
                String strArgs = getArgs(sysLogAnno, args, request);
                sysLog.setParams(getText(strArgs));
            }

            sysLog.setTrace(MDC.get(BaseContextConstants.LOG_TRACE_ID));

            if (request != null) {
                sysLog.setRequestIp(ServletUtil.getClientIP(request));
                sysLog.setRequestUri(URLUtil.getPath(request.getRequestURI()));
                sysLog.setHttpMethod(request.getMethod());
                sysLog.setUa(StrUtil.sub(request.getHeader("user-agent"), 0, 500));
                if (BaseContextHandler.getBoot()) {
                    sysLog.setTenantCode(BaseContextHandler.getTenant());
                } else {
                    sysLog.setTenantCode(request.getHeader(BaseContextConstants.JWT_KEY_TENANT));
                }
                if (StrUtil.isEmpty(sysLog.getTrace())) {
                    sysLog.setTrace(request.getHeader(BaseContextConstants.TRACE_ID_HEADER));
                }
            }
            sysLog.setStartTime(LocalDateTime.now());

            THREAD_LOCAL.set(sysLog);
        });
    }

    /**
     * 监测是否需要记录日志
     *
     * @param joinPoint
     * @param sysLogAnno
     * @return
     */
    private boolean check(JoinPoint joinPoint, SysLog sysLogAnno) {
        if (sysLogAnno == null || !sysLogAnno.enabled()) {
            return true;
        }
        // 读取目标类上的注解
        SysLog targetClass = joinPoint.getTarget().getClass().getAnnotation(SysLog.class);
        // 加上 sysLogAnno == null 会导致父类上的方法永远需要记录日志
//        if (sysLogAnno == null && targetClass != null && !targetClass.enabled()) {
        if (targetClass != null && !targetClass.enabled()) {
            return true;
        }
        return false;
    }


    private OptLogDTO get() {
        OptLogDTO sysLog = THREAD_LOCAL.get();
        if (sysLog == null) {
            return new OptLogDTO();
        }
        return sysLog;
    }

    private void tryCatch(Consumer<String> consumer) {
        try {
            consumer.accept("");
        } catch (Exception e) {
            log.warn("记录操作日志异常", e);
            THREAD_LOCAL.remove();
        }
    }

    private void publishEvent(OptLogDTO sysLog) {
        sysLog.setFinishTime(LocalDateTime.now());
        sysLog.setConsumingTime(sysLog.getStartTime().until(sysLog.getFinishTime(), ChronoUnit.MILLIS));
        SpringUtils.publishEvent(new SysLogEvent(sysLog));
        THREAD_LOCAL.remove();
    }

    /**
     * 截取指定长度的字符串
     *
     * @param val
     * @return
     */
    private String getText(String val) {
        return StrUtil.sub(val, 0, 65535);
    }

    private String getArgs(SysLog sysLogAnno, Object[] args, HttpServletRequest request) {
        String strArgs = StrPool.EMPTY;

        try {
            if (!request.getContentType().contains("multipart/form-data")) {
                strArgs = JsonUtil.toJson(args);
            }
        } catch (Exception e) {
            try {
                strArgs = Arrays.toString(args);
            } catch (Exception ex) {
                log.warn("解析参数异常", ex);
            }
        }
        return strArgs;
    }

    /**
     * 解析spEL表达式
     */
    private String getValBySpEL(String spEL, MethodSignature methodSignature, Object[] args) {
        try {
            //获取方法形参名数组
            String[] paramNames = nameDiscoverer.getParameterNames(methodSignature.getMethod());
            if (paramNames != null && paramNames.length > 0) {
                Expression expression = spelExpressionParser.parseExpression(spEL);
                // spring的表达式上下文对象
                EvaluationContext context = new StandardEvaluationContext();
                // 给上下文赋值
                for (int i = 0; i < args.length; i++) {
                    context.setVariable(paramNames[i], args[i]);
                    context.setVariable("p" + i, args[i]);
                }
                return expression.getValue(context).toString();
            }
        } catch (Exception e) {
            log.warn("解析操作日志的el表达式出错", e);
        }
        return spEL;
    }


}

