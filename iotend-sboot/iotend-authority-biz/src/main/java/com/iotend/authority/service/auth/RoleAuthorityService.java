package com.iotend.authority.service.auth;

import com.iotend.authority.dto.auth.RoleAuthoritySaveDTO;
import com.iotend.authority.dto.auth.UserRoleSaveDTO;
import com.iotend.authority.entity.auth.RoleAuthority;
import com.iotend.base.service.SuperService;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 角色的资源
 * </p>
 *
 * @author huang
 * @date 2019-07-03
 */
public interface RoleAuthorityService extends SuperService<RoleAuthority> {

    /**
     * 给用户分配角色
     *
     * @param userRole
     * @return
     */
    boolean saveUserRole(UserRoleSaveDTO userRole);

    /**
     * 给角色重新分配 权限（资源/菜单）
     *
     * @param roleAuthoritySaveDTO
     * @return
     */
    boolean saveRoleAuthority(RoleAuthoritySaveDTO roleAuthoritySaveDTO);

    /**
     * 根据权限id 删除
     *
     * @param ids
     * @return
     */
    boolean removeByAuthorityId(List<Long> ids);
}