package com.gaoyanshan.bysj.project.service.impl;

import com.gaoyanshan.bysj.project.constant.Constant;
import com.gaoyanshan.bysj.project.dto.*;
import com.gaoyanshan.bysj.project.entity.Document;
import com.gaoyanshan.bysj.project.entity.Project;
import com.gaoyanshan.bysj.project.entity.User;
import com.gaoyanshan.bysj.project.exception.SystemException;
import com.gaoyanshan.bysj.project.repository.DocumentRepository;
import com.gaoyanshan.bysj.project.repository.ProjectRepository;
import com.gaoyanshan.bysj.project.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <pre>类名: DocumentServiceImpl</pre>
 * <pre>描述: 文档类业务逻辑层实现</pre>
 * <pre>日期: 19-3-31 下午7:42</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@Service
public class DocumentServiceImpl implements DocumentService{

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private ProjectRepository projectRepository;


    @Override
    public List<Object> getAllDocType() {
        List<Object> typeList = new ArrayList<>();
        for (Map.Entry<Integer,String> entry : Constant.DOCUMENT_TYPES.entrySet()){
            Types types = new Types();
            types.setId(entry.getKey());
            types.setName(entry.getValue());
            typeList.add(types);
        }
        return typeList;
    }

    @Transactional
    @Override
    public Integer addDocument(DocumentDTO documentDTO, User user) {
        Document document = new Document();
        Project project = projectRepository.findOneById(documentDTO.getProjectId());
        if (project == null)
            throw new SystemException("该项目不存在");
        document.setId(documentDTO.getId());
        document.setProject(project);
        document.setTitle(documentDTO.getTitle());
        document.setSummary(documentDTO.getSummary());
        document.setContent(documentDTO.getContent());
        document.setCreateUser(user);
        document.setCreateTime(new Date());
        document.setDocumentType(documentDTO.getType());
        document = documentRepository.save(document);
        return document.getId();
    }

    @Transactional
    @Override
    public void deleteDocument(int id) {
        documentRepository.deleteById(id);
    }

    @Override
    public MyPage<Document> getDocumentsByCondition(DocumentCondition condition) {
        Pageable pageable = PageRequest.of(condition.getPage()-1,condition.getSize(),
                                            Sort.Direction.DESC,"createTime");
        Specification specification = new Specification<Document>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<Document> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                //按时间段查询
                if(condition.getStartTime() != null){
                    predicates.add(criteriaBuilder.greaterThan(root.get("createTime"), condition.getStartTime()));
                }
                if(condition.getEndTime() != null){
                    predicates.add(criteriaBuilder.lessThan(root.get("createTime"), condition.getEndTime()));
                }
                if (condition.getUserId() != 0){
                    predicates.add(criteriaBuilder.equal(root.get("createrUser").get("id"),condition.getUserId()));
                }
                if (condition.getType() != 0)
                {
                    predicates.add(criteriaBuilder.equal(root.get("documentType"),condition.getType()));
                }
                predicates.add(criteriaBuilder.equal(root.get("deleted"),0));
                predicates.add(criteriaBuilder.equal(root.get("project").get("id"),condition.getpId()));
                Predicate predicate = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

                if (condition.getKey() != null){
                    Predicate predicate2 = criteriaBuilder.or(criteriaBuilder.like(root.get("title"),"%"+condition.getKey()+"%"),
                           criteriaBuilder.like(root.get("summary"),"%"+condition.getKey()+"%"),
                           criteriaBuilder.like(root.get("content"),"%"+condition.getKey()+"%"));
                    return criteriaBuilder.and(predicate,predicate2);
                }
                return predicate;
            }
        };

        return MyPage.transformPage(documentRepository.findAll(specification,pageable));
    }

    @Override
    public DocumentDTO getDocumentById(int id) {
        Document document = documentRepository.findOneById(id);
        DocumentDTO dto = new DocumentDTO();
        dto.setTitle(document.getTitle());
        dto.setContent(document.getContent());
        dto.setSummary(document.getSummary());
        dto.setId(document.getId());
        dto.setType(document.getDocumentType());
        dto.setCreateTime(document.getCreateTime());
        dto.setUserInfo(new UserInfo(document.getCreateUser().getId(),
                document.getCreateUser().getEmail(),
                document.getCreateUser().getName()));
        return dto;
    }

    @Override
    public void updateDocument() {

    }
}
