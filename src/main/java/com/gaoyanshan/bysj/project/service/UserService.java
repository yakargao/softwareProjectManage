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


    Integer uploadAvatar(Map<String,Object> map);

}
