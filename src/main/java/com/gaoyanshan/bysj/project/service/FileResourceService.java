package com.gaoyanshan.bysj.project.service;

import com.gaoyanshan.bysj.project.dto.FileCondition;
import com.gaoyanshan.bysj.project.dto.FileDTO;
import com.gaoyanshan.bysj.project.dto.MyPage;
import com.gaoyanshan.bysj.project.dto.Types;
import com.gaoyanshan.bysj.project.entity.FileResource;
import com.gaoyanshan.bysj.project.entity.User;

import java.io.File;
import java.util.List;

/**
 * <pre>类名: FileResourceService</pre>
 * <pre>描述: 文件资源业务逻辑层</pre>
 * <pre>日期: 19-4-1 下午9:23</pre>
 * <pre>作者: gaoyanshan</pre>
 */


public interface FileResourceService {

    /**
     * 获取所有文件类型
     * @return
     */
    List<Types> getAllTypes();

    /**
     * 新增文件
     * @return
     */
    List<Integer> addFiles(FileDTO dto,User user);

    /**
     * 删除文件
     */
    void deletedFile();

    /**
     * 获取文件列表
     * @return
     */
    List<FileDTO> getFiles();

    /**
     * 通过id获取文件详情
     * @return
     */
    FileDTO getFileById();

    /**
     * 通过项目id获取项目的文件
     * @param pId
     * @return
     */
    List<FileDTO> getFilesByPid(int pId);

    MyPage<FileResource> getFilesByCondition(FileCondition condition);

}
