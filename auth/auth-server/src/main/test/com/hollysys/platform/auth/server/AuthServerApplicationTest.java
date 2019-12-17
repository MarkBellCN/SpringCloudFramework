package com.hollysys.platform.auth.server;

import com.hollysys.platform.auth.data.api.entity.PmUser;
import com.hollysys.platform.auth.data.api.provider.PmUserProvider;
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
    @Reference(check = false, timeout = 3000)
    private PmUserProvider pmUserProvider;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void testAddSysUser() throws Exception {
        PmUser pmUser = new PmUser();
        pmUser.setId("10000");
        pmUser.setUsername("admin");
        pmUser.setPassword("test");
        pmUser.setPassword(passwordEncoder.encode(pmUser.getPassword()));
        pmUserProvider.save(pmUser);
    }
}
