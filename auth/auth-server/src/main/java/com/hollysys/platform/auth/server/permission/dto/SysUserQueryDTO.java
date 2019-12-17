package com.hollysys.platform.auth.server.permission.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;


@ApiModel
@Data
public class SysUserQueryDTO {
    @ApiModelProperty(value = "用户编号", example = "用户编号")
    @Pattern(regexp="^[a-zA-Z0-9_]{0,20}$",message="{userid.pattern}")
    private String userId;
}
