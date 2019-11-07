package com.hollysys.iods.data.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class SysUserRole implements Serializable {
    @TableId
    private String id;

    private String userId;

    private String roleId;
}
