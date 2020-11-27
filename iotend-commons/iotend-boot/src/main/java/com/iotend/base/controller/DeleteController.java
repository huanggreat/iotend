package com.iotend.base.controller;

import com.iotend.base.R;
import com.iotend.log.annotation.SysLog;
import com.iotend.security.annotation.PreAuth;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.List;

/**
 * 删除Controller
 *
 * @param <Entity> 实体
 * @param <Id>     主键
 * @author huang
 */
public interface DeleteController<Entity, Id extends Serializable> extends BaseController<Entity> {

    /**
     * 删除方法
     *
     * @param ids
     * @return
     */
    @ApiOperation(value = "删除")
    @DeleteMapping
    @ApiImplicitParams({
     @ApiImplicitParam(name = "ids[]", value = "主键id", dataType = "array", paramType = "query"),
    })
    @SysLog("'删除:' + #ids")
    @PreAuth("hasPermit('{}delete')")
    default R<Boolean> delete(@RequestParam("ids[]") List<Id> ids) {
        R<Boolean> result = handlerDelete(ids);
        if (result.getDefExec()) {
            getBaseService().removeByIds(ids);
        }
        return result;
    }

    /**
     * 自定义删除
     *
     * @param ids
     * @return 返回SUCCESS_RESPONSE, 调用默认更新, 返回其他不调用默认更新
     */
    default R<Boolean> handlerDelete(List<Id> ids) {
        return R.successDef(true);
    }

}
