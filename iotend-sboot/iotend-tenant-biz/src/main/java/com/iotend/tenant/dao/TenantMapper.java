package com.iotend.tenant.dao;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.iotend.base.mapper.SuperMapper;
import com.iotend.tenant.entity.Tenant;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 企业
 * </p>
 *
 * @author huang
 * @date 2019-10-25
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface TenantMapper extends SuperMapper<Tenant> {

    /**
     * 根据租户编码查询
     *
     * @param code 租户编码
     * @return
     */
    Tenant getByCode(@Param("code") String code);
}
