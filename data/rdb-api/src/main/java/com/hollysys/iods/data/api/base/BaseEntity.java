package com.hollysys.iods.data.api.base;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {
    @TableId
    private String id;

    private Date createDate;

    private Date updateDate;
}
