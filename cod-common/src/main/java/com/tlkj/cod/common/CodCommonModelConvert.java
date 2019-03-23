/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.common;

import com.google.common.base.CaseFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Desc model转换类
 *
 * @author sourcod
 * @version 1.0
 * @className CodCommonModelConver
 * @date 2019/3/22 8:30 PM
 */
public abstract class CodCommonModelConvert {

    private static Logger logger = LoggerFactory.getLogger(CodCommonModelConvert.class);

    /**
     * Do转Dto
     * BigDecimal 类型需要写下划线
     * @param zlass Dto类
     * @param <T>   Dto
     * @return Dto
     */
    public <T> T toDto(Class<T> zlass){

        if (!this.getClass().getSuperclass().getName().equals(CodCommonModelConvert.class.getName())){
            logger.error("不是子类. 不可转换！请继承 {} 类", CodCommonModelConvert.class.getName());
        }

        // 1. 获取Do字段
        Field[] fields = this.getClass().getDeclaredFields();
        // 2. 创建Dto实例
        T tempT = null;
        try {
            tempT = zlass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            System.err.println("创建Dto失败");
            return null;
        }
        // 3. 设置Dto值
        for (Field field : fields) {
            // 4. 私有的 && 非static && 非final
            if (Modifier.isPrivate(field.getModifiers()) && !Modifier.isStatic(field.getModifiers()) && !Modifier.isFinal(field.getModifiers())){
                try {
                    Object value = getFieldValueByName(field.getName(), this);
                    if (value == null){
                        continue;
                    }
                    // 下划线转驼峰
                    String dtoSetName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, field.getName());
                    PropertyDescriptor pd = new PropertyDescriptor(dtoSetName, zlass);
                    Method wM = pd.getWriteMethod();
                    wM.invoke(tempT, value);
                } catch (IllegalAccessException | IntrospectionException | InvocationTargetException e) {
                    e.printStackTrace();
                    String dtoSetName = field.getName();
                    logger.warn("Do类必须采用下划线模式, 错误字段{}", dtoSetName);
                    System.err.println("set 错误");
                }
            }
        }
        return tempT;
    }

    /**
     * 根据属性名获取属性值
     */
    private Object getFieldValueByName(String fieldName, Object o) {
        try {
            PropertyDescriptor pd = new PropertyDescriptor(fieldName, o.getClass());
            Method rM = pd.getReadMethod();
            return rM.invoke(o);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
