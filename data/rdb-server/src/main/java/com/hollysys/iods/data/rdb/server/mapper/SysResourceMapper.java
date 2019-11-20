package com.hollysys.iods.data.rdb.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hollysys.iods.data.api.entity.SysResources;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysResourceMapper  extends BaseMapper<SysResources> {
    List<SysResources> getResourcesByUserId(@Param("userId") String userId);
}