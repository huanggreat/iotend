package com.iotend.injection.core;

import cn.hutool.core.util.StrUtil;
import com.google.common.base.Objects;
import com.iotend.injection.annonation.InjectionField;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author huang
 */
@Data
@NoArgsConstructor
@ToString
public class InjectionFieldPo {

    /**
     * 固定的查询值
     *
     * @return
     */
    protected String key;

    /**
     * 执行查询任务的类
     * <p/>
     * api()  和 feign() 任选其一,  使用 api时，请填写实现类， 使用feign时，填写接口即可
     * 如： @InjectionField(api="userServiceImpl") 等价于 @InjectionField(feign=UserService.class)
     * 如： @InjectionField(api="userController") 等价于 @InjectionField(feign=UserApi.class)
     *
     * @return
     */
    protected String api;

    /**
     * 执行查询任务的类
     * <p/>
     * api()  和 feign() 任选其一,  使用 api时，请填写实现类， 使用feign时，填写接口即可
     * 如： @InjectionField(api="userServiceImpl") 等价于 @InjectionField(feign=UserService.class)
     * 如： @InjectionField(api="userController") 等价于 @InjectionField(feign=UserApi.class)
     */
    protected Class<? extends Object> apiClass;

    protected Class<? extends Object> beanClass;

    /**
     * 调用方法
     *
     * @return
     */
    protected String method;
    /**
     * 字典类型
     */
    protected String type;

    public InjectionFieldPo(InjectionField rf) {
        this.api = rf.api();
        this.apiClass = rf.apiClass();
        this.key = rf.key();
        this.method = rf.method();
        this.beanClass = rf.beanClass();
        this.type = rf.dictType();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InjectionFieldPo that = (InjectionFieldPo) o;

        boolean isEquals = Objects.equal(method, that.method);

        if (StrUtil.isNotEmpty(api)) {
            isEquals = isEquals && Objects.equal(api, that.api);
        } else {
            isEquals = isEquals && Objects.equal(apiClass, that.apiClass);
        }

        return isEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(api, apiClass, method);
    }
}

