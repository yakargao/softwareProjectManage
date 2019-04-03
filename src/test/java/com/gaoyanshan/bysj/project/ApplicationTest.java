package com.gaoyanshan.bysj.project;

import com.gaoyanshan.bysj.project.entity.Permission;
import com.gaoyanshan.bysj.project.entity.Role;
import com.gaoyanshan.bysj.project.entity.User;
import com.gaoyanshan.bysj.project.entity.UserProject;
import com.gaoyanshan.bysj.project.repository.UserProjectRepository;
import com.gaoyanshan.bysj.project.repository.UserRepository;
import com.gaoyanshan.bysj.project.util.DateUtil;
import com.gaoyanshan.bysj.project.util.MailUtil;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

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

        System.out.println(DateUtil.getDayBegin());
        System.out.println(DateUtil.getYesterdayBegin());

        log.info("今天开始时间",DateUtil.getDayBegin().toString());
        log.info("昨天开始时间",DateUtil.getYesterdayBegin().toString());
        log.info("昨天开始时间",DateUtil.getTimesWeekBegin());
    }

    @Test
    public void testMap(){
        Map<Integer,Integer> map = new HashMap<>();
    }

}
