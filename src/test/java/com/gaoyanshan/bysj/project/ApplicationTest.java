package com.gaoyanshan.bysj.project;

import com.gaoyanshan.bysj.project.entity.Permission;
import com.gaoyanshan.bysj.project.entity.Role;
import com.gaoyanshan.bysj.project.entity.User;
import com.gaoyanshan.bysj.project.repository.UserRepository;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    private static final Logger log = LoggerFactory.getLogger(ApplicationTest.class);

    @Test
    public void contextLoads(){
        log.info("okl");
    }

    @Autowired
    private UserRepository userRepository;
    @Test
    public void testJpa(){

        User user = new User();
        user.setEmail("1112dsssa352@qq.com");
        user.setPassword("1234");
        user.setName("gaoyanshan");
        user.setValid(1);
        Role role = new Role();
        role.setRoleNameEn("admin");
        role.setRoleNameZh("管理员");
        role.setUser(user);
        Permission permission = new Permission();
        permission.setPermission("/*");
        permission.setRole(role);

        role.getPermissions().add(permission);
        user.getRoles().add(role);


    }
    @Test
    public void testFind(){

    }
}
