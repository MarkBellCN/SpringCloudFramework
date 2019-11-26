package com.hollysys.platform.auth.server;

import com.hollysys.iods.data.api.entity.SysUser;
import com.hollysys.iods.data.api.provider.SysUserProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthServerApplication.class)
public class AuthServerApplicationTest {
    @Reference
    private SysUserProvider sysUserProvider;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void testAddSysUser() throws Exception {
        SysUser sysUser = new SysUser();
        sysUser.setUserId("10000");
        sysUser.setUserName("admin");
        sysUser.setPassword("test");
        sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        sysUserProvider.save(sysUser);
    }
}