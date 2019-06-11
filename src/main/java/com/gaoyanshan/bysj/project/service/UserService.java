package com.gaoyanshan.bysj.project.service;


import com.gaoyanshan.bysj.project.dto.UserDTO;
import com.gaoyanshan.bysj.project.dto.UserInfo;
import com.gaoyanshan.bysj.project.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>类名: UserService</pre>
 * <pre>描述: 用户逻辑层接口</pre>
 * <pre>日期: 19-3-16 下午11:16</pre>
 * <pre>作者: gaoyanshan</pre>
 */
public interface UserService {
    /**
     * 添加用户
     * @param map
     * @return
     */
    User addUser(Map<String,String> map);

    /**
     * 删除用户
     * @param id
     * @return
     */
    boolean deleteUser(int id);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    boolean updateUser(UserDTO user);

    /**
     * 获得项目成员
     * @param projectId
     * @return
     */
    List<UserInfo> getUsersByProject(int projectId);


    /**
     * 获取所有用户
     * @return
     */
    List<UserInfo> getAllUsers();


    /**
     * 获取用户信息
     * @param user
     * @return
     */
    HashMap<String,Object> getUserInfo(User user);


    /**
     * 更新头像
     * @param map
     * @return
     */
    Integer uploadAvatar(Map<String,Object> map);


    /**
     * 分组查询
     * @param group
     * @return
     */
    List<UserInfo> getUsersByGroup(int group);


    /**
     * 管理员新增用户
     * @param map
     * @return
     */
    Integer adminAddUser(Map<String,String> map);


    /**
     * 获取用户信息
     * @param id
     * @return
     */
    UserInfo getUserInfoById(int id);


    /**
     * 根据条件查找用户
     * @param key
     * @return
     */
    List<UserInfo> getUsersByConditions(String key);


    /**
     * 更新用户密码
     * @param map
     * @return
     */
    Integer updatePassword(Map<String,Object> map);


}
