package com.gaoyanshan.bysj.project.service.impl;

import com.gaoyanshan.bysj.project.constant.Constant;
import com.gaoyanshan.bysj.project.constant.RolesEnToZh;
import com.gaoyanshan.bysj.project.constant.StatusCode;
import com.gaoyanshan.bysj.project.dto.UserDTO;
import com.gaoyanshan.bysj.project.dto.UserInfo;
import com.gaoyanshan.bysj.project.dynamic.aspect.Dynamic;
import com.gaoyanshan.bysj.project.dynamic.enumeration.DynamicEventEnum;
import com.gaoyanshan.bysj.project.entity.Role;
import com.gaoyanshan.bysj.project.entity.User;
import com.gaoyanshan.bysj.project.entity.UserProject;
import com.gaoyanshan.bysj.project.exception.SystemException;
import com.gaoyanshan.bysj.project.repository.UserProjectRepository;
import com.gaoyanshan.bysj.project.repository.UserRepository;
import com.gaoyanshan.bysj.project.service.UserService;
import com.gaoyanshan.bysj.project.util.DateUtil;
import com.gaoyanshan.bysj.project.util.MailUtil;
import com.gaoyanshan.bysj.project.util.Md5Util;
import com.gaoyanshan.bysj.project.util.PasswordHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.sound.sampled.Line;
import java.sql.SQLException;
import java.util.*;

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

    @Autowired
    private UserProjectRepository userProjectRepository;

    @Autowired
    private MailUtil mailUtil;

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


    @Transactional
    @Override
    public boolean deleteUser(int id) {
        userRepository.deleteById(id);
        return true;
    }

    @Transactional
    @Override
    public boolean updateUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setPassword(Md5Util.passwordToHash(userDTO.getEmail(),userDTO.getPassword()));
        user.setName(user.getName());
        for (String roleName : userDTO.getRoles()){
            Role role = new Role(roleName, RolesEnToZh.ROLES.get(roleName));
            user.getRoles().add(role);
        }
        userRepository.save(user);
        return true;
    }

    @Override
    public List<UserInfo> getUsersByProject(int projectId) {
        List<UserProject> userProjects = userProjectRepository.findAllByProject(projectId);
        List<UserInfo> users = new ArrayList<>();
        for (UserProject userProject : userProjects){
            UserInfo userInfo = new UserInfo(userProject.getUser().getId(),
                    userProject.getUser().getEmail(),
                    userProject.getUser().getName());
            userInfo.setAvatar(userProject.getUser().getAvatar());
            users.add(userInfo);
        }
        return users;
    }

    @Override
    public List<UserInfo> getAllUsers() {
        List<UserInfo> userInfos = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for(User u : users){
            UserInfo userInfo = new UserInfo(u.getId(),
                    u.getEmail(),
                    u.getName());
            userInfo.setAvatar(u.getAvatar());
            userInfos.add(userInfo);
        }
        return userInfos;
    }

    @Override
    public HashMap<String, Object> getUserInfo(User user) {
        HashMap<String,Object> res = new HashMap<>();
        res.put("id",user.getId());
        res.put("name",user.getName());
        res.put("avatar",user.getAvatar());
        List<String> roles = new ArrayList<>();
        for (Role r : user.getRoles()){
            roles.add(r.getRoleNameEn());
        }
        res.put("roles",roles);
        return res;
    }

    @Override
    public Integer uploadAvatar(Map<String, Object> map) {
        int id = (int) map.get("id");
        String avatar = (String) map.get("avatar");
        User user = userRepository.findOneById(id);
        user.setAvatar(avatar);
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public List<UserInfo> getUsersByGroup(int group) {
        List<User> users = new ArrayList<>();
        if (group == 1){
            users = userRepository.findAll();
        }else if (group == 2){
            users = userRepository.findAllByCreateTimeBetween(DateUtil.getTimesMonthBegin(),DateUtil.getTimesMonthEnd());
        }else if (group == 3){
            users = userRepository.findAllByValidEquals(0);
        }
        List<UserInfo> userInfos = new ArrayList<>();
        for (User user : users){
            UserInfo userInfo = new UserInfo();
            userInfo.setId(user.getId());
            userInfo.setEmail(user.getEmail());
            userInfo.setName(user.getName());
            userInfo.setAvatar(user.getAvatar());
            userInfo.setValid(user.getValid());
            userInfos.add(userInfo);
        }
        return userInfos;
    }

    @Override
    public Integer adminAddUser(Map<String, String> map) {
        String name = map.get("name");
        String email = map.get("email");
        String password = "";
        User user = userRepository.findByEmail(email);
        if (user != null){
            throw new SystemException("该用户已经存在");
        }else{
            user = new User();
            user.setEmail(email);
            user.setName(name);
            user.setAvatar(Constant.DEFAULT_AVATAR);
            user.setCreateTime(new Date());
            password = PasswordHelper.generatePassword(8);
            String hashPassword = Md5Util.passwordToHash(email,password);
            user.setPassword(hashPassword);
            user = userRepository.save(user);
            mailUtil.sendSimpleEmail("软件项目管理工具密码通知","欢迎使用软件项目管理工具，你的初始化密码是：\n"+password,email);
        }
        return user.getId();
    }

    @Override
    public UserInfo getUserInfoById(int id) {
        User user = userRepository.findOneById(id);
        UserInfo userInfo = new UserInfo();
        userInfo.setId(user.getId());
        userInfo.setEmail(user.getEmail());
        userInfo.setAvatar(user.getAvatar());
        userInfo.setName(user.getName());
        userInfo.setValid(user.getValid());
        userInfo.setCreateTime(user.getCreateTime());
        return userInfo;
    }

    @Override
    public List<UserInfo> getUsersByConditions(String key) {
         List<UserInfo> userInfos = new ArrayList<>();
        Specification querySpecifi = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (key != "" && key != null){
                    predicates.add(criteriaBuilder.like(root.get("name"),"%"+key+"%"));
                    predicates.add(criteriaBuilder.like(root.get("email"),"%"+key+"%"));

                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        List<User> userList = userRepository.findAll(querySpecifi);
        return null;
    }

}
