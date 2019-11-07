package com.hollysys.iods.data.rdb.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hollysys.iods.data.api.entity.SysUser;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {
    @Select("select * from sys_user where user_name = #{userName}")
    SysUser getSysUserByUserName(String userName);
}
