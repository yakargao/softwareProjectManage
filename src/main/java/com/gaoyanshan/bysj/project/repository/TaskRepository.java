package com.gaoyanshan.bysj.project.repository;

import com.gaoyanshan.bysj.project.entity.Project;
import com.gaoyanshan.bysj.project.entity.Task;
import com.gaoyanshan.bysj.project.entity.TaskType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Date;
import java.util.List;

/**
 * <pre>类名: TaskRepository</pre>
 * <pre>描述: task的dao</pre>
 * <pre>日期: 19-3-25 下午8:23</pre>
 * <pre>作者: gaoyanshan</pre>
 */
@Repository
public interface TaskRepository extends JpaRepository<Task,Integer>,JpaSpecificationExecutor<Task> {

    @Modifying
    @Query("update Task t set t.deleted = 1 where t.id = :id")
    void deleteById(@Param("id") int id);

    @Query("select t from Task t where t.id=:id")
    Task findOneById(@Param("id") int taskId);

    List<Task> findAllByProjectId(int id);

    @Modifying
    @Query("update Task t set t.isDone= :status,t.doneTime = :doneTime  where t.id=:id")
    void updateStatus(@Param("id") int taskId, @Param("status") int status,@Param("doneTime")Date doneTime);

    List<Task> findAllByExpectedTimeBetween(@Param("startTime")Date startTime,@Param("endTime")Date endTime);

    int countAllByDoneTimeBetweenAndProjectEquals(@Param("startTime")Date startTime,@Param("endTime")Date endTime,@Param("project")Project project);

    int countAllByCreateTimeBetween(@Param("startTime")Date startTime,@Param("endTime")Date endTime);


    List<Task> findAllByDoneTimeBetweenAndIsDoneEqualsAndProjectEquals(@Param("startTime")Date startTime,
                                                                       @Param("endTime")Date endTime,
                                                                       @Param("isDone")int isDone,
                                                                       @Param("project")Project project);

    List<Task> findAllByCreateTimeBetweenAndProjectEquals(@Param("startTime")Date startTime,
                                                          @Param("endTime")Date endTime,
                                                          @Param("project")Project project);


    List<Task> findAllByTaskTypeAndDeletedOrderByIsDoneAsc(@Param("type") TaskType type, @Param("deleted") int deleted);

    int countAllByIsDoneAndProjectAndCreateTimeBetween(@Param("isDone")int isDone,
                                                                    @Param("project")Project project,
                                                                    @Param("startTime")Date startTime,
                                                                    @Param("endTime")Date endTime);

    int countAllByExpectedTimeBeforeAndIsDoneEqualsAndCreateTimeBetween(@Param("time")Date time,
                                                                        @Param("isDone")int isDone,
                                                                        @Param("startTime")Date startTime,
                                                                        @Param("endTime")Date endTime);

    @Modifying
    @Query("update Task t set t.deleted = 1 where  t.taskType.id = :taskTypeId")
    void deleteAllByTaskTypeId(@Param("taskTypeId")int taskTypeId);

}
