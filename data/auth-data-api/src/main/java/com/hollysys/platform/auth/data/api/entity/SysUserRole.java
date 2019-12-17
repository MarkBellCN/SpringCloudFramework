package com.hollysys.platform.auth.data.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class SysUserRole implements Serializable {
    @TableId(value = "id", type = IdType.UUID)
    private Integer id;

    private String userId;

    private Integer roleId;
}
