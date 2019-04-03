package com.gaoyanshan.bysj.project.service;

import com.gaoyanshan.bysj.project.dto.DocumentCondition;
import com.gaoyanshan.bysj.project.dto.DocumentDTO;
import com.gaoyanshan.bysj.project.dto.MyPage;
import com.gaoyanshan.bysj.project.dto.Types;
import com.gaoyanshan.bysj.project.entity.Document;
import com.gaoyanshan.bysj.project.entity.User;

import java.util.List;
import java.util.Map;

/**
 * <pre>类名: DocumentService</pre>
 * <pre>描述: 文档类业务逻辑层</pre>
 * <pre>日期: 19-3-31 下午7:32</pre>
 * <pre>作者: gaoyanshan</pre>
 */
public interface DocumentService {



    /**
     * 获得所有文档类型
     * @return
     */
    List<Object> getAllDocType();

    /**
     * 新建文档
     * @param documentDTO
     * @return
     */
    Integer addDocument(DocumentDTO documentDTO,User user);

    /**
     * 删除文档
     * @param id
     */
    void deleteDocument(int id);

    /**
     * 文档条件查询
     * @param condition
     * @return
     */
    MyPage<Document> getDocumentsByCondition(DocumentCondition condition);


    /**
     * 获取一个文档的详情
     * @return
     */
    DocumentDTO getDocumentById(int id);


    /**
     * 更新文档
     */
    void updateDocument();


}
