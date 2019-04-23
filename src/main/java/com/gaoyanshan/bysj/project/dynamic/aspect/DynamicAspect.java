package com.gaoyanshan.bysj.project.dynamic.aspect;

import com.gaoyanshan.bysj.project.dto.*;
import com.gaoyanshan.bysj.project.dynamic.entity.DynamicContent;
import com.gaoyanshan.bysj.project.dynamic.enumeration.DynamicEventEnum;
import com.gaoyanshan.bysj.project.dynamic.repository.DynamicRepository;
import com.gaoyanshan.bysj.project.entity.Task;
import com.gaoyanshan.bysj.project.entity.User;
import com.gaoyanshan.bysj.project.repository.TaskRepository;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <pre>类名: DynamicAspect</pre>
 * <pre>描述: 操作记录拦截切面</pre>
 * <pre>日期: 2019/3/29 20:04</pre>
 * <pre>作者: gaoyanshan</pre>
 */
@Aspect
@Component
public class DynamicAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicAspect.class);

    private ExecutorService dynamicThreadPool = Executors.newFixedThreadPool(3);

    @Autowired
    private DynamicRepository dynamicRepository;

    @Autowired
    private TaskRepository taskRepository;

    public DynamicAspect() {

    }

    /**
     * @Description: 拦截个人动态
     * @author gaoyanshan
     * @date   2019/3/30 23:13
     * @param  pjp 切入点
     */
    @Around("@annotation(com.gaoyanshan.bysj.project.dynamic.aspect.Dynamic)")
    public Object catchDynamic(ProceedingJoinPoint pjp){
        LOGGER.info("拦截到操作方法：{}", pjp.getSignature());
        Object[] args = pjp.getArgs();
        Object result = null;
        DynamicEventEnum event = null;
        Dynamic annotation = null;
        DynamicContent dynamicContent = null;
        try {
            MethodSignature signature = (MethodSignature)pjp.getSignature();
            annotation = (Dynamic) signature.getMethod().getAnnotation(Dynamic.class);
            event = annotation.event();
            if(DynamicEventEnum.NULL.equals(event)){
                LOGGER.debug("获取到动态事件为null不进行记录");
                return pjp.proceed();
            }
            dynamicContent = DynamicContent.getNewInstance();
            dynamicContent.setDynamicEventName(event.getEventName());
            dynamicContent.setCreateTime(new Date());
            User user = (User)SecurityUtils.getSubject().getPrincipal();
            dynamicContent.setUserId(user.getId());
            result = pjp.proceed();
        } catch (Throwable throwable) {
            LOGGER.error("调用方法: {} 失败! 异常时输入为  {} , 异常为: ", new Object[]{pjp.getSignature().toString(), pjp.getArgs(), throwable.getMessage()});
        }finally {
            this.saveDynamicContent(dynamicContent,args);
        }
        return result;
    }

    /**
     * @Description: 异步保存动态
     * @author gaoyanshan
     * @date   2019/3/30 22:37
     * @param  dynamicContent
     */
    private void saveDynamicContent(DynamicContent dynamicContent,Object[] args){
        this.dynamicThreadPool.execute(new Runnable() {
            @Override
            public void run() {

                //更新任务
                if (dynamicContent.getDynamicEventName().equals(DynamicEventEnum.UPDATE_PROJECT.getEventName())){
                    ProjectDTO dto = (ProjectDTO) args[0];
                    dynamicContent.setDynamicEventName(dynamicContent.getDynamicEventName()+"："+dto.getTitle());
                    dynamicContent.setProjectId(dto.getId());
                }

                //创建任务
                if (dynamicContent.getDynamicEventName().equals(DynamicEventEnum.CREATE_TASK.getEventName())){
                    TaskDTO dto = (TaskDTO) args[0];
                    dynamicContent.setDynamicEventName(dynamicContent.getDynamicEventName()+"："+dto.getTitle());
                    dynamicContent.setProjectId(dto.getProjectId());
                }

                //对任务执行了done
                if (dynamicContent.getDynamicEventName().equals(DynamicEventEnum.DONE_TASK.getEventName())){
                    int taskId = (int) args[0];
                    int status = (int) args[1];
                    Task task = taskRepository.findOneById(taskId);
                    if (task !=null){
                        String dynamicName = status==1?"改变任务："+task.getTitle()+"状态为完成": "改变任务："+task.getTitle()+"状态为未完成";
                        dynamicContent.setDynamicEventName(dynamicName);
                        dynamicContent.setProjectId(task.getProject().getId());
                    }
                }

                //创建文档
                if (dynamicContent.getDynamicEventName().equals(DynamicEventEnum.CREATE_DOCUMENT.getEventName())){
                    DocumentDTO dto = (DocumentDTO) args[0];
                    dynamicContent.setDynamicEventName(dynamicContent.getDynamicEventName()+"："+dto.getTitle());
                    dynamicContent.setProjectId(dto.getProjectId());
                }

                //上传文件
                if (dynamicContent.getDynamicEventName().equals(DynamicEventEnum.UPDATE_FILE.getEventName())){
                    FileDTO dto = (FileDTO) args[0];
                    dynamicContent.setDynamicEventName(dynamicContent.getDynamicEventName()+"："+dto.getTitle());
                    dynamicContent.setProjectId(dto.getProjectId());
                }

                //创建接口
                if (dynamicContent.getDynamicEventName().equals(DynamicEventEnum.CREATE_API.getEventName())){
                    APIDTO dto = (APIDTO) args[0];
                    dynamicContent.setDynamicEventName(dynamicContent.getDynamicEventName()+"："+dto.getTitle());
                    dynamicContent.setProjectId(dto.getProjectId());
                }

                dynamicRepository.save(dynamicContent);
            }
        });
    }

}
