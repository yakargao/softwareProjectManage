package com.gaoyanshan.bysj.project.service.impl;

import com.gaoyanshan.bysj.project.constant.StatusCode;
import com.gaoyanshan.bysj.project.entity.Role;
import com.gaoyanshan.bysj.project.entity.User;
import com.gaoyanshan.bysj.project.exception.SystemException;
import com.gaoyanshan.bysj.project.repository.RoleRepository;
import com.gaoyanshan.bysj.project.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <pre>类名: RoleServiceImpl</pre>
 * <pre>描述: 角色事务层实现</pre>
 * <pre>日期: 19-3-17 上午2:28</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@Service
public class RoleServiceImpl implements RoleService {


    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role addRole(Map<String, String> map, User user) {
        String roleNameEn = null;
        try{
            roleNameEn = (String) map.get("roleNameEn");
        }catch (Exception e){
            throw new SystemException(StatusCode.PARAMS_ERROE,"前端字段错误："+e.getMessage());
        }
        Role role = new Role();
        role.setUser(user);
        role.setRoleNameEn(roleNameEn);
        return roleRepository.save(role);
    }
}
