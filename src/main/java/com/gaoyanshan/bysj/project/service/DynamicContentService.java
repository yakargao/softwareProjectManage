package com.gaoyanshan.bysj.project.service;

import com.gaoyanshan.bysj.project.dto.MyPage;
import com.gaoyanshan.bysj.project.dynamic.entity.DynamicContent;

public interface DynamicContentService {

    /**
     * 通过用户ID获取用户的动态
     * @param userId
     * @return
     */
    MyPage<DynamicContent> getDynamicContentsByUserId(int page,int size,int userId);

    /**
     * 获取相关项目的活动日志
     * @param page
     * @param size
     * @param projectId
     * @return
     */
    MyPage<DynamicContent> getDynamicContentByProjectId(int page,int size,int projectId);


}
