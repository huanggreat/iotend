package com.iotend.authority.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 角色组织关系
 * @author: huang
 * @create: 2020-11-30 13:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "RoleOrgSaveDTO", description = "角色组织关系")
public class RoleOrgSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     * #c_auth_role
     */
    @ApiModelProperty(value = "角色ID")
    private Long roleId;
    /**
     * 组织ID
     * #c_core_org
     */
    @ApiModelProperty(value = "组织ID")
    private Long orgId;

}