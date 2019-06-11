package com.gaoyanshan.bysj.project.repository;


/**
 * <pre>类名: DocumentRepository</pre>
 * <pre>描述: 文件资源访问控制层</pre>
 * <pre>日期: 19-3-31 下午7:30</pre>
 * <pre>作者: gaoyanshan</pre>
 */


import com.gaoyanshan.bysj.project.entity.FileResource;
import com.gaoyanshan.bysj.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FileResourceRepository extends JpaRepository<FileResource,Integer>,JpaSpecificationExecutor<FileResource> {
    List<FileResource> findAllByUploadTimeBetweenAndProjectEquals(@Param("startTime")Date startTime,
                                                                  @Param("endTime")Date endTime,
                                                                  @Param("project")Project project);

    int countAllByProjectAndFileTypeAndUploadTimeBetween(@Param("project")Project project,
                                                   @Param("fileType")int fileType,
                                                   @Param("beginTime")Date beginTime,
                                                   @Param("endTime")Date endTime);
}
