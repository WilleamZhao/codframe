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
import com.tlkj.cod.core.security.matcher.CodCredentialsMatcher;
import com.tlkj.cod.core.security.mgt.StatelessDefaultSubjectFactory;
import com.tlkj.cod.core.security.realm.CodRealm;
import com.tlkj.cod.core.security.session.AccessTokenSessionManager;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SubjectDAO;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className ShiroConfiguration
 * @date 2018/12/19 10:59 PM
 */
@Configuration
@ComponentScan(basePackages = "com.tlkj.cod.*")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableTransactionManagement
@EnableAsync
@EnableWebMvc
@EnableScheduling
public class MainConfiguration {

    @Bean(name="shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") org.apache.shiro.mgt.SecurityManager manager) {
        ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
        filter.setSecurityManager(manager);

        //插入自定义过滤器
        Map<String, Filter> filterMap = new HashMap<String, Filter>();
        filter.setFilters(filterMap);

        //登录
        filter.setLoginUrl("/sessionFailure");
        //首页
        //filter.setSuccessUrl("/index");
        //错误页面，认证不通过跳转
        //filter.setUnauthorizedUrl("/error");
        //设置过滤器
        Map<String,String> map = new HashMap<String, String>();
        //过滤器资源放行内容
        map.put("/common/**","anon");
        map.put("/user/**","anon");
        //过滤器放行策略
        map.put("/test/**","anon");
        //权限放行
        map.put("/getBCryptpass","anon");
        map.put("/login","anon");
        map.put("/tologin","anon");
        map.put("/loginout","anon");
        map.put("/kickout","anon");
        map.put("/checkLogin","anon");
        map.put("/sessionFailure","anon");
        map.put("/error/**","anon");

        map.put("/**","authc");

        filter.setFilterChainDefinitionMap(map);
        return filter;
    }

    @Bean(name = "statelessAuthcFilter")
    public LoginFilter loginFilter(){
        return new LoginFilter();
    }

    @Bean(name = "codCredentialsMatcher")
    protected CodCredentialsMatcher codCredentialsMatcher(){
        return new CodCredentialsMatcher();
    }

    /**
     * 配置核心安全事务管理器
     * @param codRealm codRealm
     * @return SecurityManager
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager securityManager(@Qualifier("codRealm") CodRealm codRealm,
                                                     @Qualifier("statelessDefaultSubjectFactory") StatelessDefaultSubjectFactory statelessDefaultSubjectFactory,
                                                     @Qualifier("sessionManager")SessionManager sessionManager,
                                                     @Qualifier("subjectDAO")SubjectDAO subjectDAO) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(codRealm);

        securityManager.setSessionManager(sessionManager);

        // securityManager.setRememberMeManager(rememberMeManager);
        // securityManager.setCacheManager(ehCacheManager);
        securityManager.setSubjectFactory(statelessDefaultSubjectFactory);
        securityManager.setSubjectDAO(subjectDAO);
        return securityManager;
    }

    /**
     * 配置自定义的权限登录器
     * @param matcher matcher
     * @return AuthRealm
     */
    @Bean(name="codRealm")
    public CodRealm authRealm(@Qualifier("codCredentialsMatcher") CredentialsMatcher matcher) {
        CodRealm authRealm = new CodRealm();
        authRealm.setCredentialsMatcher(matcher);
        return authRealm;
    }

    /**
     * 自定义RememberMe
     * @return
     */
    public SimpleCookie getRememberMeCookie() {
        // 这个参数是cookie的名称，对应前端的checkbox 的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        // <!-- 记住我cookie生效时间30天（259200） ,单位秒;-->
        simpleCookie.setMaxAge(9000);
        return simpleCookie;
    }

    @Bean(name = "statelessDefaultSubjectFactory")
    public StatelessDefaultSubjectFactory statelessDefaultSubjectFactory(){
        return new StatelessDefaultSubjectFactory();
    }

    @Bean(name = "sessionManager")
    public AccessTokenSessionManager sessionManager(){
        return new AccessTokenSessionManager();
    }

    @Bean(name = "subjectDAO")
    public DefaultSubjectDAO defaultSubjectDAO(){
        DefaultSubjectDAO defaultSubjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator evaluator = new DefaultSessionStorageEvaluator();
        evaluator.setSessionStorageEnabled(false);
        defaultSubjectDAO.setSessionStorageEvaluator(evaluator);
        return defaultSubjectDAO;
    }


}
