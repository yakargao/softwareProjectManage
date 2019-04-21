package com.gaoyanshan.bysj.project.service.impl;

import com.gaoyanshan.bysj.project.constant.Constant;
import com.gaoyanshan.bysj.project.dto.MyPage;
import com.gaoyanshan.bysj.project.entity.GithubLog;
import com.gaoyanshan.bysj.project.entity.Project;
import com.gaoyanshan.bysj.project.repository.GithubLogRepository;
import com.gaoyanshan.bysj.project.service.GithubLogService;
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
public class GithubLogServiceImpl implements GithubLogService {


    @Autowired
    private GithubLogRepository githubLogRepository;

    @Override
    public MyPage<GithubLog> getGithubLog(int projectId,int page, int size) {
        Pageable pageable = PageRequest.of(page-1,size, Sort.Direction.DESC,"commitDate");

        Specification specification = new Specification<GithubLog>() {
            @Override
            public Predicate toPredicate(Root<GithubLog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(criteriaBuilder.equal(root.get("projectId"), projectId));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };

        return MyPage.transformPage(githubLogRepository.findAll(specification,pageable));
    }
}
