/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.core.security.realm;

import com.tlkj.cod.cache.CodCacheManager;
import com.tlkj.cod.common.CodCommonUUID;
import com.tlkj.cod.core.facade.LoginUserFacade;
import com.tlkj.cod.core.security.token.StatelessToken;
import com.tlkj.cod.core.service.LoginUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 * Desc CodRealm
 *
 * @author sourcod
 * @version 1.0
 * @className CodRealm
 * @date 2018/10/22 下午1:13
 */
@Component
public class CodRealm extends AuthorizingRealm {

    @Autowired
    @Qualifier("userServiceImpl")
    @Lazy
    private LoginUserService userService;

    @Autowired
    @Qualifier("userFacadeImpl")
    @Lazy
    private LoginUserFacade loginUserFacade;

    @Autowired
    CodCacheManager codCacheManager;

    @Override
    public String getName() {
        return "codRealm";
    }

    /**
     * 支持StateLessToken
     * @param authenticationToken token
     * @return 是否支持
     */
    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        // 仅支持StatelessToken类型的Token
        return authenticationToken instanceof StatelessToken;
    }

    /**
     * 身份认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        StatelessToken statelessToken = (StatelessToken) authenticationToken;

        return null;
    }

    public CodRealm() {
        super();
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //根据用户名查找角色，请根据需求实现
        Set set = principalCollection.getRealmNames();
        Iterator iterator = set.iterator();
        String token = "";
        while (iterator.hasNext()){
            token = iterator.next().toString();
        }
        SimpleAuthorizationInfo authorizationInfo =  new SimpleAuthorizationInfo();

        return authorizationInfo;
    }

    /**
     * 密码匹配
     */
    @Override
    protected void assertCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) throws AuthenticationException {
        System.out.println(token.getPrincipal());
        System.out.println(info.getPrincipals());
        // super.setCredentialsMatcher(new CodCredentialsMatcher());
        super.assertCredentialsMatch(token, info);
    }

    //得到密钥，此处硬编码一个
    private String getKey(String username) {
        if("admin".equals(username)) {
            return "codFrame";
        }
        return null;
    }
}
