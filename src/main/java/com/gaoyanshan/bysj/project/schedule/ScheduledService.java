package com.gaoyanshan.bysj.project.schedule;

import com.gaoyanshan.bysj.project.dynamic.aspect.DynamicAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * <pre>类名: ScheduledService</pre>
 * <pre>描述: 定时调度服务</pre>
 * <pre>日期: 19-4-3 下午1:34</pre>
 * <pre>作者: gaoyanshan</pre>
 */
@Configuration
@EnableScheduling
public class ScheduledService {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledService.class);
//    @Scheduled(fixedRate = 10000)
//    public void test1() throws InterruptedException {
//        System.out.println("定时任务1================");
//    }
//
//    @Scheduled(cron = "0/5 * * * * *")
//    public void test2() throws InterruptedException {
//        System.out.println("定时任务2================");
//    }
}
