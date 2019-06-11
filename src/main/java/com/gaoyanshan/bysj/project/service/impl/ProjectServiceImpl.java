package com.gaoyanshan.bysj.project.service.impl;

import com.gaoyanshan.bysj.project.config.shiro.ShiroConfig;
import com.gaoyanshan.bysj.project.constant.Constant;
import com.gaoyanshan.bysj.project.dto.MyPage;
import com.gaoyanshan.bysj.project.dto.ProjectDTO;
import com.gaoyanshan.bysj.project.dynamic.aspect.Dynamic;
import com.gaoyanshan.bysj.project.dynamic.enumeration.DynamicEventEnum;
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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
    public Project getProject(int id) {
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
    @Dynamic(event = DynamicEventEnum.UPDATE_PROJECT)
    public Integer addProject(ProjectDTO dto,User user) {
        Project project = new Project();
        project.setTitle(dto.getTitle());
        project.setDetail(dto.getDetail());
        project.setCreateUserEmail(user.getEmail());
        project.setCreateUserName(user.getName());
        project.setCreateTime(new Date());
        project.setProgress(dto.getProgress());
        project.setGithubUrl(dto.getGithubUrl());
        project.setGithubUsername(dto.getGithubUsername());
        project.setGithubPassword(dto.getGithubPassword());
        project.setId(dto.getId());
        Project newProject = projectRepository.save(project);
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
        Pageable pageable = PageRequest.of(page-1,size,Sort.Direction.DESC,"createTime");
        Specification querySpecifi = new Specification<Project>() {
            @Override
            public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(criteriaBuilder.equal(root.get("deleted"),Constant.DB_UNDELETED));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return MyPage.transformPage(projectRepository.findAll(querySpecifi,pageable));
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

    @Transactional
    @Override
    public Boolean deleteUserOfProject(int userId, int projectId) {
        userProjectRepository.deleteByUserIdAndProjectId(userId,projectId);
        return true;
    }

    @Transactional
    @Override
    public Boolean addUserOfProject(int userId, int projectId) {

        UserProject userProject = userProjectRepository.findByUserIdAndProjectProjectId(userId,projectId);
        if (userProject == null)
            userProjectRepository.saveOneRecord(userId,projectId);
        return true;
    }

}
