package com.hollysys.platform.auth.data.server.service;

import com.hollysys.platform.auth.data.api.entity.OauthClientDetails;
import com.hollysys.platform.auth.data.server.mapper.OauthClientDetailsMapper;
import com.hollysys.platform.auth.data.api.provider.OauthClientDetailsProvider;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.Service;

/**
 *
 * @author Gen Code
 * @since 2019-12-17
 */
@Service(protocol = "dubbo")
public class OauthClientDetailsService extends ServiceImpl<OauthClientDetailsMapper, OauthClientDetails> implements OauthClientDetailsProvider {

}
