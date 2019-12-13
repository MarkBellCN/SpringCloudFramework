package com.hollysys.iods.data.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class SysRoleResources implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer resourceId;

    private Integer roleId;
}
