package com.gaoyanshan.bysj.project.repository;

import com.gaoyanshan.bysj.project.entity.Task;
import com.gaoyanshan.bysj.project.entity.TaskType;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskTypeRepository  extends JpaRepository<TaskType,Integer> {
    TaskType findById(int id);

    List<TaskType> findAllByProjectIdAndDeleted(@Param("projectId")int projectId,@Param("deleted")int deleted);


    @Modifying
    @Query("update TaskType t set  t.deleted = 1 where id = :id")
    void  deleteById(@Param("id") int id);

}
