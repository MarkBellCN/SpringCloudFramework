package com.hollysys.platform.auth.data.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hollysys.platform.auth.data.api.entity.SysResources;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SysResourceMapper  extends BaseMapper<SysResources> {
    Set<SysResources> getResourcesByUserId(@Param("userId") String userId);
}
