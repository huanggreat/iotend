package com.iotend.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.iotend.base.mapper.SuperMapper;
import com.iotend.exception.BizException;
import com.iotend.exception.code.ExceptionCode;

import java.util.List;

/**
 * 基于MP的 IService 新增了2个方法： saveBatchSomeColumn、updateAllById
 * 其中：
 * 1，updateAllById 执行后，会清除缓存
 * 2，saveBatchSomeColumn 批量插入
 *
 * @param <T> 实体
 * @author huang
 */
public interface SuperService<T> extends IService<T> {
    /**
     * 获取实体的类型
     *
     * @return
     */
    Class<T> getEntityClass();

    /**
     * 批量保存数据
     * <p>
     * 注意：该方法仅仅测试过mysql
     *
     * @param entityList
     * @return
     */
    default boolean saveBatchSomeColumn(List<T> entityList) {
        if (entityList.isEmpty()) {
            return true;
        }
        if (entityList.size() > 5000) {
            throw BizException.wrap(ExceptionCode.TOO_MUCH_DATA_ERROR);
        }
        return SqlHelper.retBool(((SuperMapper) getBaseMapper()).insertBatchSomeColumn(entityList));
    }

    /**
     * 根据id修改 entity 的所有字段
     *
     * @param entity
     * @return
     */
    boolean updateAllById(T entity);

}
