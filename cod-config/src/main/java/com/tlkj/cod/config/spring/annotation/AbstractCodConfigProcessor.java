package com.tlkj.cod.config.spring.annotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * desc 处理@Value抽象类
 * @author sourcod
 */
public abstract class AbstractCodConfigProcessor implements BeanPostProcessor, PriorityOrdered {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class clazz = bean.getClass();
        for (Field field : findAllField(clazz)) {
            processField(bean, beanName, field);
        }

        for (Method method : findAllMethod(clazz)) {
            processMethod(bean, beanName, method);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * subclass should implement this method to process field
     */
    protected abstract void processField(Object bean, String beanName, Field field);

    /**
     * subclass should implement this method to process method
     */
    protected abstract void processMethod(Object bean, String beanName, Method method);


    @Override
    public int getOrder() {
        //make it as late as possible
        return Ordered.LOWEST_PRECEDENCE;
    }

    private List<Field> findAllField(Class clazz) {
        final List<Field> res = new LinkedList<>();
        ReflectionUtils.doWithFields(clazz, res::add);
        return res;
    }

    private List<Method> findAllMethod(Class clazz) {
        final List<Method> res = new LinkedList<>();
        ReflectionUtils.doWithMethods(clazz, res::add);
        return res;
    }
}
