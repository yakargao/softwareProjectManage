package com.gaoyanshan.bysj.project.config.shiro;

import com.gaoyanshan.bysj.project.entity.Permission;
import com.gaoyanshan.bysj.project.entity.Role;
import com.gaoyanshan.bysj.project.entity.User;
import com.gaoyanshan.bysj.project.repository.UserRepository;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>类名: ShiroRealm</pre>
 * <pre>描述: shrio的realm</pre>
 * <pre>日期: 19-3-16 下午8:45</pre>
 * <pre>作者: gaoyanshan</pre>
 */
public class ShiroRealm extends AuthorizingRealm {

    @Override
    public String getName() {
        return "ShiroRealm";
    }

    @Autowired
    private UserRepository userRepository;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principalCollection.getPrimaryPrincipal();
        for (Role role : user.getRoles()) {
            System.out.println(role.getRoleNameEn());
            authorizationInfo.addRole(role.getRoleNameEn());
            for (Permission p : role.getPermissions()) {
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //获取用户的输入的账号.
        String mail = (String) authenticationToken.getPrincipal();
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User user = userRepository.findByEmail(mail);


        //账号冻结
        //用户不存在
        if (user == null)
            return null;
        //密码错误

        //返回
        Map<String,String> map = new HashMap<>();
        map.put("email",user.getEmail());
        map.put("userName",user.getName());
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(map,
                user.getPassword(),
                ByteSource.Util.bytes(user.getEmail()),
                getName());
        return authenticationInfo;
    }
}
