package com.iotend.authority.dto.test;


import io.swagger.annotations.ApiModel;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * 日期测试类 DTO
 *
 * @author huang
 * @date 2019/07/24
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@ApiModel(value = "ObjDTO", description = "测试")
public class ObjDTO implements Serializable {
    private List<String> xx;
    private List<Object> yy;

}
