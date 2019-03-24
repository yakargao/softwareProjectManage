package com.gaoyanshan.bysj.project;

import com.gaoyanshan.bysj.project.entity.Permission;
import com.gaoyanshan.bysj.project.entity.Role;
import com.gaoyanshan.bysj.project.entity.User;
import com.gaoyanshan.bysj.project.entity.UserProject;
import com.gaoyanshan.bysj.project.repository.UserProjectRepository;
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
    private UserProjectRepository userProjectRepository;
    @Test
    public void testJpa(){

        User user = new User();
        user.setEmail("10553099@qq.com");
        UserProject userProject = new UserProject();
        userProject.setUser(user);
        userProject.setId(31);
        System.out.println(userProjectRepository.findAllByUser(31));

    }
    @Test
    public void testFind(){

    }
}
