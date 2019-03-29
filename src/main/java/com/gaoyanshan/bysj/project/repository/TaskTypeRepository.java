package com.gaoyanshan.bysj.project.repository;

import com.gaoyanshan.bysj.project.entity.Task;
import com.gaoyanshan.bysj.project.entity.TaskType;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskTypeRepository  extends JpaRepository<TaskType,Integer> {
    TaskType findById(int id);

}
