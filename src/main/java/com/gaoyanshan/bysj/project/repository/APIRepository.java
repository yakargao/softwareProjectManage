package com.gaoyanshan.bysj.project.repository;

import com.gaoyanshan.bysj.project.entity.API;
import com.gaoyanshan.bysj.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * <pre>类名: APIRepository</pre>
 * <pre>描述: API数据访问层</pre>
 * <pre>日期: 19-4-2 下午3:36</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@Repository
public interface APIRepository extends JpaRepository<API,Integer>,JpaSpecificationExecutor<API> {

    @Query("select a from API a where id=:id")
    API findOneById(@Param("id") int id);

    List<API> findAllByCreateTimeBetweenAndProjectEquals(@Param("startTime")Date startTime,
                                                         @Param("endTime")Date endTime,
                                                         @Param("project")Project project);
}
