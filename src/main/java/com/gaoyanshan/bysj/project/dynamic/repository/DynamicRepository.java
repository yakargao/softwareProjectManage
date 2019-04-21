package com.gaoyanshan.bysj.project.dynamic.repository;

import com.gaoyanshan.bysj.project.dynamic.entity.DynamicContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <pre>类名: DynamicRepository</pre>
 * <pre>描述: 个人操作动态存储</pre>
 * <pre>日期: 2019/3/30 22:34</pre>
 * <pre>作者: gaoyanshan</pre>
 */
@Repository
public interface DynamicRepository extends JpaRepository<DynamicContent,Integer>, JpaSpecificationExecutor<DynamicContent> {

    List<DynamicContent> getByUserIdOrderByCreateTimeDesc(@Param("userId") Integer userId);
}
