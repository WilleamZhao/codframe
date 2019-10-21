package com.tlkj.cod.launcher.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Desc 全局配置
 * Bean 不再这里配置
 * Bean 作用范围
 *
 * @author sourcod
 * @version 1.0
 * @className CodSpringConfiguration
 * @date 2019/5/30 10:08 AM
 */
@Configuration
/**
 * 支持注解
 * cglib 代理
 */
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class CodSpringConfiguration {


}
