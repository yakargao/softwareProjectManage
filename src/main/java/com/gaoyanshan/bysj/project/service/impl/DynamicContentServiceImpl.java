package com.gaoyanshan.bysj.project.service.impl;

import com.gaoyanshan.bysj.project.dto.MyPage;
import com.gaoyanshan.bysj.project.dynamic.entity.DynamicContent;
import com.gaoyanshan.bysj.project.dynamic.repository.DynamicRepository;
import com.gaoyanshan.bysj.project.entity.GithubLog;
import com.gaoyanshan.bysj.project.service.DynamicContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


@Service
public class DynamicContentServiceImpl implements DynamicContentService {


    @Autowired
    private DynamicRepository dynamicRepository;

    @Override
    public MyPage<DynamicContent> getDynamicContentsByUserId(int page,int size ,int userId) {
        Pageable pageable = PageRequest.of(page-1,size, Sort.Direction.DESC,"createTime");
        return MyPage.transformPage(dynamicRepository.findAll(pageable));
    }

    @Override
    public MyPage<DynamicContent> getDynamicContentByProjectId(int page, int size, int projectId) {
        Pageable pageable = PageRequest.of(page-1,size, Sort.Direction.DESC,"createTime");
        Specification specification = new Specification<DynamicContent>() {
            @Override
            public Predicate toPredicate(Root<DynamicContent> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(criteriaBuilder.equal(root.get("projectId"), projectId));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return MyPage.transformPage(dynamicRepository.findAll(specification,pageable));
    }
}
