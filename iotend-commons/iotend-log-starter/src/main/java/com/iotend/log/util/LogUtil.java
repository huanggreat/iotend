package com.iotend.log.util;

import com.iotend.log.annotation.SysLog;
import com.iotend.utils.StrPool;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * 日志工具类
 *
 * @author huang
 */
@Slf4j
public class LogUtil {

    /***
     * 获取操作信息
     * @param point
     * @return
     */
    public static String getDescribe(JoinPoint point) {
        SysLog annotation = getTargetAnno(point);
        if (annotation == null) {
            return StrPool.EMPTY;
        }
        return annotation.value();
    }

    public static String getDescribe(SysLog annotation) {
        if (annotation == null) {
            return StrPool.EMPTY;
        }
        return annotation.value();
    }

    /**
     * 优先从子类获取 @SysLog：
     * 1，若子类重写了该方法，有标记就记录日志，没标记就忽略日志
     * 2，若子类没有重写该方法，就从父类获取，父类有标记就记录日志，没标记就忽略日志
     *
     * @param point
     * @return
     */
    public static SysLog getTargetAnno(JoinPoint point) {
        try {
            SysLog annotation = null;
            if (point.getSignature() instanceof MethodSignature) {
                Method method = ((MethodSignature) point.getSignature()).getMethod();
                if (method != null) {
                    annotation = method.getAnnotation(SysLog.class);
                }
            }
            return annotation;
        } catch (Exception e) {
            log.warn("获取 {}.{} 的 @SysLog 注解失败", point.getSignature().getDeclaringTypeName(), point.getSignature().getName(), e);
            return null;
        }
    }

}