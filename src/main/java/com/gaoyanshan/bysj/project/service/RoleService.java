package com.gaoyanshan.bysj.project.service;

import com.gaoyanshan.bysj.project.entity.Role;
import com.gaoyanshan.bysj.project.entity.User;

import java.util.Map;

/**
 * <pre>类名: RoleService</pre>
 * <pre>描述: 角色业务层</pre>
 * <pre>日期: 19-3-17 上午2:24</pre>
 * <pre>作者: gaoyanshan</pre>
 */
public interface RoleService {

    Role addRole(Map<String,String> map,User user);
}
