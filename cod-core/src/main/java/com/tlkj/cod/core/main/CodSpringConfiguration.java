/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.core.main;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Desc Cod SpringBean 配置
 *
 * @author sourcod
 * @version 1.0
 * @className CodConfiguration
 * @date 2018/12/19 10:59 PM
 */
@Configuration
// @ComponentScan(basePackages = "com.tlkj.cod")
@EnableAspectJAutoProxy(proxyTargetClass = true)
// @EnableTransactionManagement
// @EnableAsync
@Lazy
// @EnableWebMvc
// @EnableScheduling
public class CodSpringConfiguration  extends WebMvcConfigurerAdapter {

    /*@Bean(name = "postProcessor")
    public AbstractCodConfigProcessor codConfigProcessor(){
        return new SpringValueProcessor();
    }*/

    /*@Bean(name="shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") org.apache.shiro.mgt.SecurityManager manager) {
        ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
        filter.setSecurityManager(manager);

        //插入自定义过滤器
        Map<String, Filter> filterMap = new HashMap<>();
        filter.setFilters(filterMap);

        //登录
        filter.setLoginUrl("/sessionFailure");
        //首页
        //filter.setSuccessUrl("/index");
        //错误页面，认证不通过跳转
        //filter.setUnauthorizedUrl("/error");
        //设置过滤器
        Map<String,String> map = new HashMap<>();
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

    *//**
     * 配置核心安全事务管理器
     * @param codRealm codRealm
     * @return SecurityManager
     *//*
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

    *//**
     * 配置自定义的权限登录器
     * @param matcher matcher
     * @return AuthRealm
     *//*
    @Bean(name="codRealm")
    public CodRealm authRealm(@Qualifier("codCredentialsMatcher") CredentialsMatcher matcher) {
        CodRealm authRealm = new CodRealm();
        authRealm.setCredentialsMatcher(matcher);
        return authRealm;
    }

    *//**
     * 自定义RememberMe
     * @return
     *//*
    public SimpleCookie getRememberMeCookie() {
        // 这个参数是cookie的名称，对应前端的checkbox 的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        // <!-- 记住我cookie生效时间30天（259200） ,单位秒;-->
        simpleCookie.setMaxAge(9000);
        return simpleCookie;
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
    }*/


}
