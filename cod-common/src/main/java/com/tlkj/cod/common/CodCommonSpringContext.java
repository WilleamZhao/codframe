/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * Desc 根据名称获取 Spring Bean
 *
 * @author sourcod
 * @version 1.0
 * @className SpringContextUtil
 * @date 2018/11/6 下午4:56
 */
public class CodCommonSpringContext implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CodCommonSpringContext.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    public static Object getBean(String name) throws BeansException{
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(String name, Class<T> zlass) throws BeansException{
        return applicationContext.getBean(name, zlass);
    }

    public <T> T getBean(Class<T> zlass) throws BeansException{
        return  applicationContext.getBean(zlass);
    }

    public <T> Map<String, T> getBeansOfType(Class<T> zlass){
        return applicationContext.getBeansOfType(zlass);
    }

    public <T> String[] getBeanNamesForType(Class<T> zlass){
        return applicationContext.getBeanNamesForType(zlass);
    }
}
