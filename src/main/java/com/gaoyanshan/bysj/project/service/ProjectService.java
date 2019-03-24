package com.gaoyanshan.bysj.project.service;

import com.gaoyanshan.bysj.project.dto.ProjectDTO;
import com.gaoyanshan.bysj.project.entity.Project;
import com.gaoyanshan.bysj.project.entity.User;

import java.util.List;
import java.util.Map;

public interface ProjectService {

    Project getProjec(int id);

    List<Project> getProjectsByUserId(int id) ;

    void addProject(Map<String,Object> map, User user);

    void updateProject(int id,Map<String,Object> map);

    void deletedProject(int id);


}
