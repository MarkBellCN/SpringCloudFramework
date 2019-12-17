package com.hollysys.platform.auth.data.server.service;

import com.hollysys.platform.auth.data.api.entity.PmUserRole;
import com.hollysys.platform.auth.data.server.mapper.PmUserRoleMapper;
import com.hollysys.platform.auth.data.api.provider.PmUserRoleProvider;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.Service;

/**
 *
 * @author Gen Code
 * @since 2019-12-17
 */
@Service(protocol = "dubbo")
public class PmUserRoleService extends ServiceImpl<PmUserRoleMapper, PmUserRole> implements PmUserRoleProvider {

}
