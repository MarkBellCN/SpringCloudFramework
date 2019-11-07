package com.hollysys.iods.data.api.entity;

import com.hollysys.iods.data.api.base.BaseEntity;
import lombok.Data;

@Data
public class SysRole extends BaseEntity {
    private String roleCode;

    private String roleName;

}
