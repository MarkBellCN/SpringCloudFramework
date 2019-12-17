package com.hollysys.platform.auth.data.api.provider;

import com.hollysys.platform.auth.data.api.entity.PmUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 * @author Gen Code
 * @since 2019-12-17
 */
public interface PmUserProvider extends IService<PmUser> {

    PmUser getSysUserByUserName(String username);
}
