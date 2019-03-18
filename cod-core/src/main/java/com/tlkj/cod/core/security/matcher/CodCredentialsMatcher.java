/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.core.security.matcher;

import com.tlkj.cod.model.system.entity.CodFrameUserDo;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.stereotype.Component;

/**
 * Desc Cod 密码验证类
 *
 * @author sourcod
 * @version 1.0
 * @className CodCredentialsMatcher
 * @date 2018/12/17 8:17 PM
 */
@Component
public class CodCredentialsMatcher extends SimpleCredentialsMatcher {

    @Override
    protected Object getCredentials(AuthenticationInfo info) {
        return super.getCredentials(info);
    }

    /**
     * 验证 token和密码
     * @param token token
     * @param info  密码
     * @return
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String) token.getPrincipal();
        String password = (String) token.getCredentials();
        CodFrameUserDo codFrameUserDo = (CodFrameUserDo) info.getPrincipals().getPrimaryPrincipal();
        return codFrameUserDo.getLogin_pass().equals(password);
    }
}
