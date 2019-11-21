/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.spring.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Desc spring context 通用工具类
 *
 * @author sourcod
 * @version 1.0
 * @className CodSpringContext
 * @date 2019/11/20 5:52 PM
 */
@Component
public class CodSpringContext implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CodSpringContext.applicationContext = applicationContext;
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

    public static <T> T getBean(Class<T> zlass) throws BeansException{
        return applicationContext.getBean(zlass);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> zlass){
        return applicationContext.getBeansOfType(zlass);
    }

    public static <T> String[] getBeanNamesForType(Class<T> zlass){
        return applicationContext.getBeanNamesForType(zlass);
    }
}
