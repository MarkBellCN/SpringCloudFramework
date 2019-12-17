package com.hollysys.platform.auth.data.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.hollysys.platform.common.core.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
/**
 *
 * @author Gen Code
 * @since 2019-12-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("PM_MODULE")
public class PmModule extends BaseEntity {

    private static final long serialVersionUID=1L;

    @TableId("ID")
    private String id;

    private String clientId;

    private String parentId;

    private String name;

    private String url;

    private String method;

    private String icon;

    private String level;

    private String enable;

    private Integer order;


}
