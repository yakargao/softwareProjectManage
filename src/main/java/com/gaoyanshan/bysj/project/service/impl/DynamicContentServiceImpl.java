package com.gaoyanshan.bysj.project.service.impl;

import com.gaoyanshan.bysj.project.dto.MyPage;
import com.gaoyanshan.bysj.project.dynamic.entity.DynamicContent;
import com.gaoyanshan.bysj.project.dynamic.repository.DynamicRepository;
import com.gaoyanshan.bysj.project.service.DynamicContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class DynamicContentServiceImpl implements DynamicContentService {


    @Autowired
    private DynamicRepository dynamicRepository;

    @Override
    public MyPage<DynamicContent> getDynamicContentsByUserId(int page,int size ,int userId) {
        Pageable pageable = PageRequest.of(page-1,size, Sort.Direction.DESC,"createTime");
        return MyPage.transformPage(dynamicRepository.findAll(pageable));
    }
}
