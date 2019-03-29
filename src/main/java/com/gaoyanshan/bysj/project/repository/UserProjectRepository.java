package com.gaoyanshan.bysj.project.repository;

import com.gaoyanshan.bysj.project.entity.User;
import com.gaoyanshan.bysj.project.entity.UserProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * <pre>类名: UserProjectRepository</pre>
 * <pre>描述: UserProject的Dao层</pre>
 * <pre>日期: 19-3-24 上午2:38</pre>
 * <pre>作者: gaoyanshan</pre>
 */
@Repository
public interface UserProjectRepository extends JpaRepository<UserProject,Integer>{

    @Query("select u from UserProject u where u.user.id=:id")
    List<UserProject> findAllByUser(@Param("id") Integer id);

    @Query("SELECT u from UserProject u where u.project.id=:id")
    List<UserProject> findAllByProject(@Param("id")Integer id);

    @Modifying
    @Query(value = "insert into user_project(user_id,project_id) values(?1,?2)",nativeQuery = true)
    void saveOneRecord(int userId, int projectId);
}
