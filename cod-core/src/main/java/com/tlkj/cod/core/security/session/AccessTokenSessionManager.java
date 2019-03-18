/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.core.security.session;

import com.tlkj.cod.cache.CodCacheManager;
import com.tlkj.cod.common.CodCommonUUID;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className AccessTokenSessionManager
 * @date 2018/10/22 下午1:29
 */
@Component
public class AccessTokenSessionManager extends DefaultWebSessionManager {

    @Autowired
    CodCacheManager codCacheManager;

    public static final String ACCESS_TOKEN = "access_token";

    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

    /*@Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        if(request instanceof HttpServletRequest) {
            String id = WebUtils.toHttp(request).getHeader(ACCESS_TOKEN);
            // 如果请求头中有 Authorization 则其值为sessionId
            if (!StringUtils.isEmpty(id)) {
                request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
                request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
                request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
                return id;
            }
        }

        // 否则按默认规则从cookie取sessionId
        return super.getSessionId(request, response);
    }*/


    @Override
    public SessionDAO getSessionDAO() {
        return super.getSessionDAO();
    }

    @Override
    public Serializable getSessionId(SessionKey key) {
        Serializable sessionId = key.getSessionId();
        if(sessionId == null){
            HttpServletRequest request = WebUtils.getHttpRequest(key);
            HttpServletResponse response = WebUtils.getHttpResponse(key);
            sessionId = this.getSessionId(request,response);
        }
        HttpServletRequest request = WebUtils.getHttpRequest(key);
        request.setAttribute(ACCESS_TOKEN, sessionId.toString());
        return sessionId;
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        HttpServletRequest request1 = WebUtils.toHttp(request);
        String token = request1.getParameter(ACCESS_TOKEN);
        if(StringUtils.isBlank(token)) {
            token = CodCommonUUID.getUUID();
        }
        Subject subject = (Subject) codCacheManager.get(token);
        if (subject == null){
            return "";
        }
        return (Serializable) subject.getPrincipals().getPrimaryPrincipal();
        /*//这段代码还没有去查看其作用，但是这是其父类中所拥有的代码，重写完后我复制了过来...开始
        request1.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,
                ShiroHttpServletRequest.COOKIE_SESSION_ID_SOURCE);
        request1.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, token);
        request1.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
        request1.setAttribute(ShiroHttpServletRequest.SESSION_ID_URL_REWRITING_ENABLED, isSessionIdUrlRewritingEnabled());
        //这段代码还没有去查看其作用，但是这是其父类中所拥有的代码，重写完后我复制了过来...结束
        return token;*/

    }
}
