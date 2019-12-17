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
@TableName("OAUTH_CLIENT_DETAILS")
public class OauthClientDetails extends BaseEntity {

    private static final long serialVersionUID=1L;

    @TableId("CLIENT_ID")
    private String clientId;

    private String resourceIds;

    private String clientSecret;

    private String scope;

    private String authorizedGrantTypes;

    private String webServerRedirectUri;

    private String authorities;

    private String accessTokenValidity;

    private String refreshTokenValidity;

    private String additionalInformation;

    private String archived;

    private String trusted;

    private String autoapprove;


}
