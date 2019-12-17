package com.hollysys.platform.auth.data.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hollysys.platform.auth.data.api.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {
    @Select("select * from sys_user where user_id = #{userId}")
    SysUser getSysUserByUserId(@Param("userId") String userId);
}
