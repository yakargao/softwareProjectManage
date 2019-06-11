package com.gaoyanshan.bysj.project.repository;

import com.gaoyanshan.bysj.project.entity.Statistic;
import com.gaoyanshan.bysj.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * <pre>类名: UserRepository</pre>
 * <pre>描述: user的dao层</pre>
 * <pre>日期: 19-3-15 下午2:44</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@Repository
public interface UserRepository extends JpaRepository<User,Integer>,JpaSpecificationExecutor<User> {

    User findByEmail(String mail);

    User findOneById(@Param("id")Integer id);

    List<User> findAllByCreateTimeBetween(@Param("beginTime")Date beginTime,@Param("endTime")Date endTime);

    List<User> findAllByValidEquals(@Param("valid")int valid);

}
