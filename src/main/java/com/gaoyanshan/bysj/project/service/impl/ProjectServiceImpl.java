package com.gaoyanshan.bysj.project.service.impl;

import com.gaoyanshan.bysj.project.constant.Constant;
import com.gaoyanshan.bysj.project.dto.ProjectDTO;
import com.gaoyanshan.bysj.project.entity.Project;
import com.gaoyanshan.bysj.project.entity.User;
import com.gaoyanshan.bysj.project.entity.UserProject;
import com.gaoyanshan.bysj.project.exception.SystemException;
import com.gaoyanshan.bysj.project.repository.ProjectRepository;
import com.gaoyanshan.bysj.project.repository.UserProjectRepository;
import com.gaoyanshan.bysj.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <pre>类名: ProjectServiceImpl</pre>
 * <pre>描述: project相关的事务处理层</pre>
 * <pre>日期: 19-3-21 下午9:05</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private UserProjectRepository userProjectRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project getProjec(int id) {
        return projectRepository.findOneById(id);
    }

    @Override
    public List<Project> getProjectsByUserId(int id) {
        List<UserProject> userProjects = userProjectRepository.findAllByUser(id);
        List<Project> projects = new ArrayList<>();
        for (UserProject userProject : userProjects){
            if (userProject.getProject().getDeleted() == Constant.DB_UNDELETED)
                projects.add(userProject.getProject());
        }
        return projects;
    }


    @Override
    public void addProject(Map<String,Object> map,User user) {
        String title = null;
        String detail = null;
        try{
            title = (String) map.get("title");
            detail = (String) map.get("detail");
        }catch (Exception e){
            throw new SystemException("前端类型有误:"+e.getMessage());
        }
        Project project = new Project();
        project.setTitle(title);
        project.setDetail(detail);
        project.setCreateUserEmail(user.getEmail());
        project.setCreateUserName(user.getName());
        project.setCreateTime(new Date());
        projectRepository.save(project);
    }

    @Override
    public void updateProject(int id, Map<String,Object> map) {
        String title = null;
        String detail = null;
        try{
            title = (String) map.get("title");
            detail = (String) map.get("detail");
        }catch (Exception e){
            throw new SystemException("前端类型有误:"+e.getMessage());
        }
        Project project = projectRepository.findOneById(id);
        project.setTitle(title);
        project.setDetail(detail);
        projectRepository.save(project);
    }

    @Override
    public void deletedProject(int id) {
        projectRepository.deleteById(id);
    }
}
