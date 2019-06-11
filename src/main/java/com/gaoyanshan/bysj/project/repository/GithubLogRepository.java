package com.gaoyanshan.bysj.project.repository;

import com.gaoyanshan.bysj.project.entity.GithubLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <pre>类名: GithubLogRepository</pre>
 * <pre>描述: </pre>
 * <pre>日期: 19-4-5 上午11:32</pre>
 * <pre>作者: gaoyanshan</pre>
 */
@Repository
public interface GithubLogRepository extends JpaRepository<GithubLog,Integer>, JpaSpecificationExecutor<GithubLog> {
    GithubLog findBySha(@Param("sha")String sha);
}
