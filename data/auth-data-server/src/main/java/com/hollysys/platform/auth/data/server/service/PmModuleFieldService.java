package com.hollysys.platform.auth.data.server.service;

import com.hollysys.platform.auth.data.api.entity.PmModuleField;
import com.hollysys.platform.auth.data.server.mapper.PmModuleFieldMapper;
import com.hollysys.platform.auth.data.api.provider.PmModuleFieldProvider;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.Service;

/**
 *
 * @author Gen Code
 * @since 2019-12-17
 */
@Service(protocol = "dubbo")
public class PmModuleFieldService extends ServiceImpl<PmModuleFieldMapper, PmModuleField> implements PmModuleFieldProvider {

}
