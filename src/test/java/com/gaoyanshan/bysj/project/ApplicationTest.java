package com.gaoyanshan.bysj.project;

import com.gaoyanshan.bysj.project.config.shiro.ShiroConfig;
import com.gaoyanshan.bysj.project.entity.User;
import com.gaoyanshan.bysj.project.entity.UserProject;
import com.gaoyanshan.bysj.project.repository.UserProjectRepository;
import com.gaoyanshan.bysj.project.util.DateUtil;
import com.gaoyanshan.bysj.project.util.MailUtil;
import org.crazycake.shiro.RedisManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
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



    @Autowired
    private ShiroConfig shiroConfig;

    @Test
    public void  testRedis(){
        shiroConfig.redisManager().set("test".getBytes(),"1".getBytes(),1000);
    }


    @Autowired
    MailUtil mailUtil;

    @Test
    public void testEmail(){
       // mailUtil.sendSimpleEmail("标题","<h1>车市<h1>","18815135208@163.com");
        mailUtil.sendHtmlEmail("html","<h1>dasd</h1><a href='www.baidu.com'>百度<a>","18815135208@163.com");
    }

}
