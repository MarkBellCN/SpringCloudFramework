package com.hollysys.platform.auth.data.server.mapper;

import com.hollysys.platform.auth.data.api.entity.PmUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gen Code
 * @since 2019-12-17
 */
@Repository
public interface PmUserMapper extends BaseMapper<PmUser> {

    @Select("select * from pm_user where username = #{username}")
    PmUser getSysUserByUserName(String username);
}
