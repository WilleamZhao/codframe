/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.config.annotation.aop;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.util.StringValueResolver;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className CodConfigValueResolver
 * @date 2019/3/28 6:32 PM
 */
public class CodConfigValueResolver implements StringValueResolver, BeanFactoryAware {

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("asdasdasd");
    }

    @Override
    public String resolveStringValue(String strVal) {
        System.out.println("asd");
        return "asdasd";
    }

}
