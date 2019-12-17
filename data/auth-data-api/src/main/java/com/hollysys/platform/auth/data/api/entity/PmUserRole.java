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
@TableName("PM_USER_ROLE")
public class PmUserRole extends BaseEntity {

    private static final long serialVersionUID=1L;

    @TableId("ID")
    private String id;

    private String roleId;

    private String userId;


}
