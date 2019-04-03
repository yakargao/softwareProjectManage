package com.gaoyanshan.bysj.project.service;

import com.gaoyanshan.bysj.project.dto.APIDTO;
import com.gaoyanshan.bysj.project.dto.ApiCondition;
import com.gaoyanshan.bysj.project.dto.MyPage;
import com.gaoyanshan.bysj.project.entity.API;
import com.gaoyanshan.bysj.project.entity.User;

/**
 * <pre>类名: APISservice</pre>
 * <pre>描述: API业务逻辑层</pre>
 * <pre>日期: 19-4-2 下午3:38</pre>
 * <pre>作者: gaoyanshan</pre>
 */
public interface APISservice {

    /**
     * 新增一个接口
     * @param dto
     * @return
     */
    Integer addAPI(APIDTO dto, User user);

    /**
     * 删除一个Api
     * @param id
     */
    void deleteAPI(int id);

    /**
     * 获取一个API
     * @param id
     * @return
     */
    API getOneById(int id);

    /**
     * 条件查询API
     * @param condition
     * @return
     */

    MyPage<API> getApisByCoondition(ApiCondition condition);
}
