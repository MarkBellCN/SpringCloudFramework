package com.hollysys.iods.web.api.controller;

import com.hollysys.iods.data.api.entity.SysUser;
import com.hollysys.iods.data.api.provider.SysUserProvider;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysUserController {
    @Reference
    private SysUserProvider sysUserProvider;

    @GetMapping("/user/{userName}")
    public SysUser getSysUserByUserName(@PathVariable("userName") String userName){
        return sysUserProvider.getSysUserByUserName(userName);
    }

}
