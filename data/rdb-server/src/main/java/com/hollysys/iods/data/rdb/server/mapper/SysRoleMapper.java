package com.hollysys.iods.data.rdb.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hollysys.iods.data.api.entity.SysRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {
    Set<SysRole> getRoleByUserId(@Param("userId") String userId);
}
