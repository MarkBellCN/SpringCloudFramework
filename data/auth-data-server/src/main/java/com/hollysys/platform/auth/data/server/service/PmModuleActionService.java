package com.hollysys.platform.auth.data.server.service;

import com.hollysys.platform.auth.data.api.entity.PmModuleAction;
import com.hollysys.platform.auth.data.server.mapper.PmModuleActionMapper;
import com.hollysys.platform.auth.data.api.provider.PmModuleActionProvider;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.Service;

/**
 *
 * @author Gen Code
 * @since 2019-12-17
 */
@Service(protocol = "dubbo")
public class PmModuleActionService extends ServiceImpl<PmModuleActionMapper, PmModuleAction> implements PmModuleActionProvider {

}
