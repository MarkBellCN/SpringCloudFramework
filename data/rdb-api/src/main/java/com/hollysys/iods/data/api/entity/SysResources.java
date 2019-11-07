package com.hollysys.iods.data.api.entity;

import com.hollysys.iods.data.api.base.BaseEntity;
import lombok.Data;

@Data
public class SysResources extends BaseEntity {
    private String moduleName;

    private String moduleCode;

    private String modulePath;

    private String parentId;

    private String moduleIcon;

    private String httpMethod;

    private Integer isOperating;

    private Integer sort;

    private String systemId;

    private Integer active;
}
