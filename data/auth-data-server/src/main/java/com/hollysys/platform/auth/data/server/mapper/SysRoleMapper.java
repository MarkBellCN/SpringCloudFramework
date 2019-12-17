package com.hollysys.platform.auth.data.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hollysys.platform.auth.data.api.entity.SysRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {
    Set<SysRole> getRoleByUserId(@Param("userId") String userId);
}
