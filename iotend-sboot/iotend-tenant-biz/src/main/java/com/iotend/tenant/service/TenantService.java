package com.iotend.tenant.service;

import com.iotend.base.service.SuperCacheService;
import com.iotend.tenant.dto.TenantConnectDTO;
import com.iotend.tenant.dto.TenantSaveDTO;
import com.iotend.tenant.entity.Tenant;
import com.iotend.tenant.enumeration.TenantStatusEnum;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 企业
 * </p>
 *
 * @author huang
 * @date 2019-10-24
 */
public interface TenantService extends SuperCacheService<Tenant> {
    /**
     * 检测 租户编码是否存在
     *
     * @param tenantCode
     * @return
     */
    boolean check(String tenantCode);

    /**
     * 保存
     *
     * @param data
     * @return
     */
    Tenant save(TenantSaveDTO data);

    /**
     * 根据编码获取
     *
     * @param tenant
     * @return
     */
    Tenant getByCode(String tenant);

    /**
     * 删除租户数据
     *
     * @param ids
     * @return
     */
    Boolean delete(List<Long> ids);

    /**
     * 通知所有服务链接数据源
     *
     * @param tenantConnect 链接信息
     * @return
     */
    Boolean connect(TenantConnectDTO tenantConnect);

    /**
     * 修改租户状态
     *
     * @param ids
     * @param status
     * @return
     */
    Boolean updateStatus(List<Long> ids, TenantStatusEnum status);
}
