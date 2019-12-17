package com.hollysys.platform.auth.data.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.hollysys.platform.common.core.entity.BaseEntity;
import lombok.Data;

@Data
public class SysRole extends BaseEntity {
    @TableId(value = "id", type = IdType.UUID)
    private Integer id;

    private String remark;

    private String roleName;

}
