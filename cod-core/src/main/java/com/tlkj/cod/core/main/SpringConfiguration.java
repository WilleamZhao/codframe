/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.core.main;

import com.tlkj.cod.core.security.login.LoginFilter;
import com.tlkj.cod.core.security.mgt.StatelessDefaultSubjectFactory;
import com.tlkj.cod.core.security.realm.CodRealm;
import com.tlkj.cod.core.security.session.AccessTokenSessionManager;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SessionStorageEvaluator;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;


/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className SpringConfig
 * @date 2018/12/25 6:01 PM
 */
@Component
@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan("com.tlkj.cod.*")
public class SpringConfiguration {

    /*@Bean
    public StatelessDefaultSubjectFactory statelessDefaultSubjectFactory(){
        return new StatelessDefaultSubjectFactory();
    }

    @Bean
    public AccessTokenSessionManager accessTokenSessionManager(){
        return new AccessTokenSessionManager();
    }

    *//**
     * 将自己的验证方式加入容器
     *//*
    @Bean
    public CodRealm codRealm() {
        CodRealm myShiroRealm = new CodRealm();
        return myShiroRealm;
    }

    *//**
     * 权限管理，配置主要是Realm的管理认证
     * @return
     *//*
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(codRealm());
        securityManager.setAuthenticator(modularRealmAuthenticator());
        securityManager.setSessionManager(sessionManager());
        securityManager.setSubjectDAO(subjectDAO());
        securityManager.setSubjectFactory(statelessDefaultSubjectFactory());
        return securityManager;
    }

    @Bean
    public Authenticator modularRealmAuthenticator(){
        ModularRealmAuthenticator realmAuthenticator = new ModularRealmAuthenticator();
        realmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return realmAuthenticator;
    }

    @Bean
    public SessionManager sessionManager(){
        AccessTokenSessionManager sessionManager = new AccessTokenSessionManager();
        return sessionManager;
    }

    @Bean
    public DefaultSubjectDAO subjectDAO(){
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        return subjectDAO;
    }

    @Bean
    public SessionStorageEvaluator storageEvaluator(){
        DefaultSessionStorageEvaluator storageEvaluator = new DefaultSessionStorageEvaluator();
        storageEvaluator.setSessionStorageEnabled(false);
        return storageEvaluator;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        Map<String, Filter> map = new HashMap<>(1);
        map.put("statelessAuthc", new LoginFilter());

        shiroFilterFactoryBean.setFilters(map);

        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/");
        shiroFilterFactoryBean.setUnauthorizedUrl("/loginPrevent");

        Map<String, String> stringMap = new HashMap<>(5);
        stringMap.put("/system/login/createCode", "anon");
        stringMap.put("/api/**", "statelessAuthc");
        stringMap.put("/index", "anon");
        stringMap.put("/index.jsp", "anon");
        stringMap.put("/action/**", "statelessAuthc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(stringMap);
        return shiroFilterFactoryBean;
    }

    *//**
     * 设置上传文件最大值
     * 1M=1*1024*1024(B)=1048576 bytes
     *//*
    @Bean
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(104857600);
        return resolver;
    }*/
}
