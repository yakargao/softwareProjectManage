package com.gaoyanshan.bysj.project.repository;

import com.gaoyanshan.bysj.project.entity.Statistic;
import com.gaoyanshan.bysj.project.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

/**
 * <pre>类名: StatisticRepository</pre>
 * <pre>描述: 统计信息数据</pre>
 * <pre>日期: 19-4-3 下午4:43</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@Repository
public interface StatisticRepository extends JpaRepository<Statistic,Integer>,JpaSpecificationExecutor<Statistic> {
    List<Statistic> findAllByProjectIdAndAndUserIdOrderByEndTimeDesc(@Param("projectId")int projectId,
                                                                            @Param("userId")int userId);
}
