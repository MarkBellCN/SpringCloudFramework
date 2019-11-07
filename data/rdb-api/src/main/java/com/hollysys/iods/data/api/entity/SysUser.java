package com.hollysys.iods.data.api.entity;

import com.hollysys.iods.data.api.base.BaseEntity;
import lombok.Data;

@Data
public class SysUser extends BaseEntity {

    private String userName;

    private String password;

    private String phone;

    private String gender;

    private Integer age;

    private Integer active;
}
