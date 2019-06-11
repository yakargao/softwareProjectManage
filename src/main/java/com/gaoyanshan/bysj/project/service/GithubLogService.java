package com.gaoyanshan.bysj.project.service;

import com.gaoyanshan.bysj.project.dto.MyPage;
import com.gaoyanshan.bysj.project.entity.GithubLog;

public interface GithubLogService {
    MyPage<GithubLog> getGithubLog(int projectId,int page, int size);
}
