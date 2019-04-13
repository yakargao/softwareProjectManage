package com.gaoyanshan.bysj.project.service.impl;

import com.gaoyanshan.bysj.project.config.shiro.ShiroConfig;
import com.gaoyanshan.bysj.project.constant.Constant;
import com.gaoyanshan.bysj.project.dto.MyPage;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

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

    @Autowired
    public ShiroConfig  shiroConfig;

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


    @Transactional
    @Override
    public Integer addProject(Map<String,Object> map,User user) {
        String title = null;
        String detail = null;
        List<Integer> userIds = new ArrayList<>();
        try{
            title = (String) map.get("title");
            detail = (String) map.get("detail");
            userIds = (ArrayList<Integer>)map.get("users");
        }catch (Exception e){
            throw new SystemException("前端类型有误:"+e.getMessage());
        }
        Project project = new Project();
        project.setTitle(title);
        project.setDetail(detail);
        project.setCreateUserEmail(user.getEmail());
        project.setCreateUserName(user.getName());
        project.setCreateTime(new Date());
        Project newProject = projectRepository.save(project);
        for(int uid : userIds){
            userProjectRepository.saveOneRecord(uid,newProject.getId());
        }
        return newProject.getId();
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

    @Override
    public MyPage<Project> getAllProject(Integer page, Integer size) {
        //根据sale_count排行
        Pageable pageable = PageRequest.of(page-1,10,Sort.Direction.DESC,"createTime");
        return MyPage.transformPage(projectRepository.findAll(pageable));
    }

    @Override
    public Map<String, Integer> getRecentProjrctId(User user) {
        Map<String, Integer> map = new HashMap<>();
        byte[] bytes = shiroConfig.redisManager().get(user.getEmail().getBytes());
        int pId = Integer.parseInt(new String(bytes));
        map.put("pId",pId);
        return map;
    }

    @Override
    public void setRecentProjectId(User user, int projectId) {
        String email = user.getEmail();
        String pId = projectId+"";
        shiroConfig.redisManager().set(email.getBytes(),pId.getBytes(),10000);
    }

}
