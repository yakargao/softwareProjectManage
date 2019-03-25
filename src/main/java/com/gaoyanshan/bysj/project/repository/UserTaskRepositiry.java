package com.gaoyanshan.bysj.project.repository;

import com.gaoyanshan.bysj.project.entity.UserTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <pre>类名: UserTaskRepositiry</pre>
 * <pre>描述: 用户任务信息表dao</pre>
 * <pre>日期: 19-3-25 下午7:07</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@Repository
public interface UserTaskRepositiry extends JpaRepository<UserTask,Integer>{
}
