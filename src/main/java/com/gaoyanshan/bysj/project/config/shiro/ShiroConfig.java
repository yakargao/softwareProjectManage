package com.gaoyanshan.bysj.project.config.shiro;


import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <pre>类名: ShiroConfig</pre>
 * <pre>描述: shiro配置</pre>
 * <pre>日期: 19-3-16 下午8:18</pre>
 * <pre>作者: gaoyanshan</pre>
 */


@Configuration
public class ShiroConfig {

    @Value("${spring.redis.shiro.host}")
    private String host;

    @Value("${spring.redis.shiro.port}")
    private int port;

    @Value("${spring.redis.shiro.timeout}")
    private int timeout;

    @Value("${spring.redis.shiro.password}")
    private String password;

    /**
     * @desc 设置接口过滤规则
     * @param securityManager
     * @return ShiroFilterFactoryBean
     */

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/logout", "authc");
        filterChainDefinitionMap.put("/user/add","anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/login", "anon");

        //filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setLoginUrl("/unauth");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //设置为授权页面
        //未授权界面;
//        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * @desc 设置凭证匹配器
     * @return HashedCredentialsMatcher
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }

    /**
     * @desc 设置简单凭证匹配器
     * @return
     */
    @Bean
    public SimpleCredentialsMatcher simpleCredentialsMatcher(){
        return new SimpleCredentialsMatcher();
    }

    /**
     * @desc 设置自定的Realm
     * @return
     */
    @Bean
    public ShiroRealm shiroRealm() {
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return shiroRealm;
    }

    /**
     * @desc 设置安全管理器
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(cacheManager());
        //设置记住我
        return securityManager;
    }

    /**
     * @desc 设置自定义的会话管理器
     * @return
     */
    @Bean
    public SessionManager sessionManager() {
        SpmsSessionManager sessionManager = new SpmsSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    /**
     * 配置shiro redisManager
     * @return RedisManager
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        redisManager.setTimeout(timeout);
        redisManager.setPassword(password);
        return redisManager;
    }

    /**
     * cacheManager 缓存 redis实现
     * @return
     */
    @Bean
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * @return
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        redisSessionDAO.setExpire(1800000); //设置登录时间为30分钟
        return redisSessionDAO;
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
