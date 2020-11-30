package com.iotend.authority.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 登陆返回信息
 * @author: huang
 * @create: 2020-11-30 13:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "LoginDTO", description = "登录信息")
public class LoginDTO implements Serializable {
    private static final long serialVersionUID = -3124612657759050173L;
    @ApiModelProperty(value = "用户信息")
    private UserDTO user;
    /**
     * token
     */
    @ApiModelProperty(value = "token")
    private String token;
    /**
     * 有效时间：单位：秒
     */
    @ApiModelProperty(value = "有效期")
    private Long expire;

    @ApiModelProperty(value = "刷新令牌")
    private String refreshToken;

    @ApiModelProperty(value = "权限列表")
    private List<String> permissionsList;
}

