package com.gaoyanshan.bysj.project.schedule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * <pre>类名: ScheduleTaskConfig</pre>
 * <pre>描述: 任务调度器配置</pre>
 * <pre>日期: 19-4-3 下午1:56</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@Configuration
@EnableScheduling
@EnableAsync
public class ScheduleTaskConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(taskExecutor());
    }
    @Bean
    public Executor taskExecutor(){
        return Executors.newScheduledThreadPool(5);
    }
}
