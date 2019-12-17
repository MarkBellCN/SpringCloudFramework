package com.hollysys.platform.auth.data.server.service;

import com.hollysys.platform.auth.data.api.entity.PmModule;
import com.hollysys.platform.auth.data.server.mapper.PmModuleMapper;
import com.hollysys.platform.auth.data.api.provider.PmModuleProvider;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.Service;

/**
 *
 * @author Gen Code
 * @since 2019-12-17
 */
@Service(protocol = "dubbo")
public class PmModuleService extends ServiceImpl<PmModuleMapper, PmModule> implements PmModuleProvider {

}
