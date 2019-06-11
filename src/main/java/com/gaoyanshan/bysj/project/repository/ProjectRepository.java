package com.gaoyanshan.bysj.project.repository;

import com.gaoyanshan.bysj.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * <pre>类名: ProjectRepository</pre>
 * <pre>描述: 项目的dao层</pre>
 * <pre>日期: 19-3-21 下午8:59</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@Repository
public interface ProjectRepository extends JpaRepository<Project,Integer>,JpaSpecificationExecutor<Project> {
    Project findOneById(Integer integer);

    @Modifying
    @Query(value = "update Project p set p.deleted = 1 where p.id=:id")
    void deleteById(@Param("id") Integer id);

    @Query("select p from Project p where p.deleted = 0")
    List<Project> findAllProjects();
}
