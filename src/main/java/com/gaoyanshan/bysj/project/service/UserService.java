package com.gaoyanshan.bysj.project.service;


import com.gaoyanshan.bysj.project.entity.User;

import java.util.Map;

/**
 * <pre>类名: UserService</pre>
 * <pre>描述: 用户逻辑层接口</pre>
 * <pre>日期: 19-3-16 下午11:16</pre>
 * <pre>作者: gaoyanshan</pre>
 */
public interface UserService {
    User addUser(Map<String,String> map);
}
