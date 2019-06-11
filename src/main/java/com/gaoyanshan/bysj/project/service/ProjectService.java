package com.gaoyanshan.bysj.project.service;

import com.gaoyanshan.bysj.project.dto.MyPage;
import com.gaoyanshan.bysj.project.dto.ProjectDTO;
import com.gaoyanshan.bysj.project.entity.Project;
import com.gaoyanshan.bysj.project.entity.User;

import java.util.List;
import java.util.Map;

public interface ProjectService {

    /**
     * 获得项目
     * @param id
     * @return
     */
    Project getProject(int id);

    /**
     * 通过用户iD获得项目
     * @param id
     * @return
     */
    List<Project> getProjectsByUserId(int id) ;


    /**
     * 新增项目
     * @param user
     */
    Integer addProject(ProjectDTO dto, User user);

    /**
     * 更新项目
     * @param id
     * @param map
     */
    void updateProject(int id,Map<String,Object> map);

    /**
     * 删除项目
     * @param id
     */
    void deletedProject(int id);

    /**
     * 获得所有项目
     * @return
     */
    MyPage<Project> getAllProject(Integer page, Integer size);

    Map<String,Integer> getRecentProjrctId(User user);

    void setRecentProjectId(User user,int projectId);

    /**
     * 删除项目成员
     * @param userId
     * @param projectId
     * @return
     */
    Boolean deleteUserOfProject(int userId,int projectId);

    /**
     * 新增用户成员
     * @param userId
     * @param projectId
     * @return
     */
    Boolean addUserOfProject(int userId,int projectId);
}
