package com.iotend.tenant.dao;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.iotend.base.mapper.SuperMapper;
import com.iotend.tenant.entity.GlobalUser;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 全局账号
 * </p>
 *
 * @author huang
 * @date 2019-10-25
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface GlobalUserMapper extends SuperMapper<GlobalUser> {

}
