package com.gaoyanshan.bysj.project.repository;

import com.gaoyanshan.bysj.project.entity.Document;
import com.gaoyanshan.bysj.project.entity.Project;
import com.gaoyanshan.bysj.project.entity.Task;
import com.gaoyanshan.bysj.project.entity.TaskType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * <pre>类名: DocumentRepository</pre>
 * <pre>描述: 文档类数据访问控制层</pre>
 * <pre>日期: 19-3-31 下午7:30</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@Repository
public interface DocumentRepository extends JpaRepository<Document,Integer>,JpaSpecificationExecutor<Document>{

    @Modifying
    @Query("update Document d set d.deleted = 1 where d.id = :id")
    void deleteById(@Param("id") int id);

    @Query("select d from Document  d where id=:id")
    Document findOneById(@Param("id") int id);

    List<Document> findAllByCreateTimeBetweenAndProjectEquals(@Param("startTime")Date startTime,
                                                              @Param("endTime")Date endTime,
                                                              @Param("project")Project project);
}
