package com.gaoyanshan.bysj.project.service.impl;

import com.gaoyanshan.bysj.project.dto.APIDTO;
import com.gaoyanshan.bysj.project.dto.ApiCondition;
import com.gaoyanshan.bysj.project.dto.MyPage;
import com.gaoyanshan.bysj.project.dynamic.aspect.Dynamic;
import com.gaoyanshan.bysj.project.dynamic.enumeration.DynamicEventEnum;
import com.gaoyanshan.bysj.project.entity.API;
import com.gaoyanshan.bysj.project.entity.Project;
import com.gaoyanshan.bysj.project.entity.User;
import com.gaoyanshan.bysj.project.exception.SystemException;
import com.gaoyanshan.bysj.project.repository.APIRepository;
import com.gaoyanshan.bysj.project.repository.ProjectRepository;
import com.gaoyanshan.bysj.project.service.APISservice;
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
 * <pre>类名: APIServiceImpl</pre>
 * <pre>描述: </pre>
 * <pre>日期: 19-4-2 下午3:48</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@Service
public class APIServiceImpl implements APISservice{

    @Autowired
    private APIRepository apiRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Dynamic(event = DynamicEventEnum.CREATE_API)
    @Override
    public Integer addAPI(APIDTO dto, User user) {
        API api = new API();
        api.setId(dto.getId());
        api.setTitle(dto.getTitle());
        api.setCreateTime(new Date());
        Project project = projectRepository.findOneById(dto.getProjectId());
        if (project == null)
            throw  new SystemException("该工程不存在");
        api.setProject(project);
        api.setContent(dto.getContent());
        api.setSummary(dto.getSummary());
        api.setUrl(dto.getUrl());
        api.setUser(user);
        api = apiRepository.save(api);
        return api.getId();
    }

    @Override
    public void deleteAPI(int id) {

    }

    @Override
    public API getOneById(int id) {
        return apiRepository.findOneById(id);
    }

    @Override
    public MyPage<API> getApisByCoondition(ApiCondition condition) {
        Pageable pageable = PageRequest.of(condition.getPage()-1,condition.getSize(),
                Sort.Direction.DESC,"createTime");
        Specification specification = new Specification<API>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<API> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (condition.getKey() != "" && condition.getKey() !=null)
                    predicates.add(criteriaBuilder.like(root.get("title"),"%"+condition.getKey().trim()+"%"));
                if (condition.getpId() != 0)
                    predicates.add(criteriaBuilder.equal(root.get("project").get("id"),condition.getpId()));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return MyPage.transformPage(apiRepository.findAll(specification,pageable));
    }
}
