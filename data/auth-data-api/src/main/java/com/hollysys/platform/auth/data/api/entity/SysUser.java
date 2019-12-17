package com.hollysys.platform.auth.data.api.entity;

import com.hollysys.platform.common.core.entity.BaseEntity;
import lombok.Data;

@Data
public class SysUser extends BaseEntity {
    private String userId;

    private String userName;

    private String password;

    private String phone;

    private String gender;

    private Integer age;

    private Integer active;
}
