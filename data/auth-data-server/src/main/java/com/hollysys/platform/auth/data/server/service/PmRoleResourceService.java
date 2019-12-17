package com.hollysys.platform.auth.data.server.service;

import com.hollysys.platform.auth.data.api.entity.PmRoleResource;
import com.hollysys.platform.auth.data.server.mapper.PmRoleResourceMapper;
import com.hollysys.platform.auth.data.api.provider.PmRoleResourceProvider;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.Service;

/**
 *
 * @author Gen Code
 * @since 2019-12-17
 */
@Service(protocol = "dubbo")
public class PmRoleResourceService extends ServiceImpl<PmRoleResourceMapper, PmRoleResource> implements PmRoleResourceProvider {

}
