package com.hollysys.iods.data.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.hollysys.iods.data.api.base.BaseEntity;
import lombok.Data;

import java.beans.Transient;
import java.util.List;

@Data
public class SysResources extends BaseEntity {
    private static final long serialVersionUID=1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String url;

    private String icon;

    private String resourceType;

    private String status;

    private Integer pid;

    private String value;

    @TableField(exist = false)
    private List<SysResources> childes;
}
