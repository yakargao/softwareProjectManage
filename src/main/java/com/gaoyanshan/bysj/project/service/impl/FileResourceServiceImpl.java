package com.gaoyanshan.bysj.project.service.impl;

import com.gaoyanshan.bysj.project.constant.Constant;
import com.gaoyanshan.bysj.project.dto.FileCondition;
import com.gaoyanshan.bysj.project.dto.FileDTO;
import com.gaoyanshan.bysj.project.dto.MyPage;
import com.gaoyanshan.bysj.project.dto.Types;
import com.gaoyanshan.bysj.project.dynamic.aspect.Dynamic;
import com.gaoyanshan.bysj.project.dynamic.enumeration.DynamicEventEnum;
import com.gaoyanshan.bysj.project.entity.FileResource;
import com.gaoyanshan.bysj.project.entity.Project;
import com.gaoyanshan.bysj.project.entity.User;
import com.gaoyanshan.bysj.project.exception.SystemException;
import com.gaoyanshan.bysj.project.repository.FileResourceRepository;
import com.gaoyanshan.bysj.project.repository.ProjectRepository;
import com.gaoyanshan.bysj.project.service.FileResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <pre>类名: FileResourceServiceImpl</pre>
 * <pre>描述: 文件类业务逻辑层</pre>
 * <pre>日期: 19-4-1 下午9:34</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@Service
public class FileResourceServiceImpl implements FileResourceService{

    @Autowired
    private ProjectRepository projectRepository;


    @Autowired
    private FileResourceRepository fileResourceRepository;

    @Override
    public List<Types> getAllTypes() {
        return Constant.FILE_TYPES;
    }

    @Dynamic(event = DynamicEventEnum.UPDATE_FILE)
    @Override
    public List<Integer> addFiles(FileDTO dto, User user) {

        List<Integer> res = new ArrayList<>();
        FileResource fileResource = new FileResource();
        fileResource.setId(dto.getId());
        Project project = projectRepository.findOneById(dto.getProjectId());
        if (project == null)
            throw new SystemException("该项目不存在");
        fileResource.setProject(project);
        fileResource.setTitle(dto.getTitle());
        fileResource.setDescription(dto.getDescription());
        fileResource.setUploadTime(new Date());
        fileResource.setFileType(dto.getType());
        fileResource.setUser(user);
        for (String path : dto.getPaths()){
            fileResource.setPath(path);
            FileResource f = fileResourceRepository.save(fileResource);
            res.add(f.getId());
        }
        return res;
    }

    @Override
    public void deletedFile() {

    }

    @Override
    public List<FileDTO> getFiles() {
        return null;
    }

    @Override
    public FileDTO getFileById() {
        return null;
    }

    @Override
    public List<FileDTO> getFilesByPid(int pId) {
        return null;
    }

    @Override
    public MyPage<FileResource> getFilesByCondition(FileCondition condition) {
        Pageable pageable = PageRequest.of(condition.getPage()-1,condition.getSize(),
                Sort.Direction.DESC,"uploadTime");
        Specification specification = new Specification<FileResource>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<FileResource> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                //按时间查询
                if (condition.getStartTime() != null){
                    predicates.add(criteriaBuilder.greaterThan(root.get("uploadTime"),condition.getStartTime()));
                }
                if (condition.getEndTime() != null){
                    predicates.add(criteriaBuilder.lessThan(root.get("uploadTime"),condition.getEndTime()));
                }
                //按照用户查询
                if (condition.getUserId() != 0){
                    predicates.add(criteriaBuilder.equal(root.get("user").get("id"),condition.getpId()));
                }
                //按照工程查询
                if (condition.getpId() != 0){
                    predicates.add(criteriaBuilder.equal(root.get("project").get("id"),condition.getpId()));
                }if (condition.getType() != 0)
                {
                    predicates.add(criteriaBuilder.equal(root.get("fileType"),condition.getType()));
                }
                //按照类型

                predicates.add(criteriaBuilder.equal(root.get("deleted"),0));
                Predicate predicate = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                if (condition.getKey() != null){
                    Predicate predicate2 = criteriaBuilder.or(criteriaBuilder.like(root.get("title"),"%"+condition.getKey()+"%"),
                            criteriaBuilder.like(root.get("description"),"%"+condition.getKey()+"%"));
                    return criteriaBuilder.and(predicate,predicate2);
                }
                return predicate;
            }
        };
        return MyPage.transformPage(fileResourceRepository.findAll(specification,pageable));
    }
}
