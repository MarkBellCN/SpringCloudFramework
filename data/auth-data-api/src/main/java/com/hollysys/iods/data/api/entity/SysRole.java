package com.hollysys.iods.data.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.hollysys.iods.data.api.base.BaseEntity;
import lombok.Data;

@Data
public class SysRole extends BaseEntity {
    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String remark;

    private String roleName;

}
