package com.hollysys.platform.auth.data.server.service;

import com.hollysys.platform.auth.data.api.entity.PmRole;
import com.hollysys.platform.auth.data.server.mapper.PmRoleMapper;
import com.hollysys.platform.auth.data.api.provider.PmRoleProvider;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.Service;

/**
 *
 * @author Gen Code
 * @since 2019-12-17
 */
@Service(protocol = "dubbo")
public class PmRoleService extends ServiceImpl<PmRoleMapper, PmRole> implements PmRoleProvider {

}
