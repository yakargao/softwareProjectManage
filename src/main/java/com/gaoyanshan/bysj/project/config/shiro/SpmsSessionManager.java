package com.gaoyanshan.bysj.project.config.shiro;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;
import static org.apache.shiro.web.servlet.ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE;


/**
 * <pre>类名: SpmsSessionManager</pre>
 * <pre>描述: 重写session管理器</pre>
 * <pre>日期: 19-3-16 下午8:01</pre>
 * <pre>作者: gaoyanshan</pre>
 */
public class SpmsSessionManager extends DefaultWebSessionManager {

    private static final String AUTHORIZATION = "authorization";

    public SpmsSessionManager() {
        super();
    }


    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {

        String sessionId = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        //如果请求头中有 Authorization 则其值为sessionId
        if (!StringUtils.isEmpty(sessionId)){
            request.setAttribute(REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return sessionId;
        }
        //否则按默认规则从cookie取sessionId
        return super.getSessionId(request, response);
    }




}
