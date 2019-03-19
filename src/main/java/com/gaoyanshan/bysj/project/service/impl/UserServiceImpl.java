package com.gaoyanshan.bysj.project.service.impl;

import com.gaoyanshan.bysj.project.constant.StatusCode;
import com.gaoyanshan.bysj.project.entity.Role;
import com.gaoyanshan.bysj.project.entity.User;
import com.gaoyanshan.bysj.project.exception.SystemException;
import com.gaoyanshan.bysj.project.repository.UserRepository;
import com.gaoyanshan.bysj.project.service.UserService;
import com.gaoyanshan.bysj.project.util.Md5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Map;

/**
 * <pre>类名: UserServiceImpl</pre>
 * <pre>描述: 用户逻辑层实现类</pre>
 * <pre>日期: 19-3-16 下午11:19</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(Map<String, String> map) {
        String email = map.get("email");
        String password = map.get("password");
        String name = map.get("name");
        String roleNameEn = map.get("roleNameEn");


        //获取Md5加盐密码
        String hashPassword = Md5Util.passwordToHash(email,password);

        //user
        User user = new User();
        user.setEmail(email);
        user.setPassword(hashPassword);
        user.setName(name);

        Role role = new Role();
        role.setRoleNameEn(roleNameEn);
        role.setUser(user);

        user.getRoles().add(role);

        User newUser = null;
        try {
             newUser = userRepository.save(user);
        }catch (Exception e){
            throw new SystemException(StatusCode.PARAMS_ERROE,"用户添加失败:"+e.getMessage());
        }

        return newUser;
    }
}