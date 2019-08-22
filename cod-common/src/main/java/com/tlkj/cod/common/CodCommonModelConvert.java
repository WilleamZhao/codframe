/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
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
import java.util.HashMap;
import java.util.Map;

/**
 * Desc model转换类
 * 下划线转驼峰
 * 目标转换类字段必须包括被转换类字段
 *
 * @author sourcod
 * @version 1.0
 * @className CodCommonModelConver
 * @date 2019/3/22 8:30 PM
 */
public abstract class CodCommonModelConvert {

    private static Logger logger = LoggerFactory.getLogger(CodCommonModelConvert.class);

    /**
     * 下划线转驼峰
     * BigDecimal 类型需要写下划线
     * @param zlass 目标类
     * @param <T>   Dto
     * @return Dto
     */
    public <T> T toDto(Class<T> zlass){
        return to(zlass, ConvertType.toDto);
    }

    /**
     * 驼峰转下划线
     * BigDecimal 类型需要写下划线
     * @param zlass 目标类
     * @param <T>   Do
     * @return Do
     */
    public <T> T toDo(Class<T> zlass){
        return to(zlass, ConvertType.toDo);
    }

    private <T> T to(Class<T> zlass, ConvertType type){
        if (!this.getClass().getSuperclass().getName().equals(CodCommonModelConvert.class.getName())){
            logger.error("不是子类. 不可转换！请继承 {} 类", CodCommonModelConvert.class.getName());
        }

        // 1. 获取字段
        Field[] fields = this.getClass().getDeclaredFields();
        // 2. 创建实例
        T tempT = null;
        try {
            tempT = zlass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            System.err.println("创建实例失败");
            return null;
        }
        // 3. 设置值
        for (Field field : fields) {
            // 4. 私有的 && 非static && 非final
            if (Modifier.isPrivate(field.getModifiers()) && !Modifier.isStatic(field.getModifiers()) && !Modifier.isFinal(field.getModifiers())){
                try {
                    Object value = getFieldValueByName(field.getName(), this);
                    if (value == null){
                        continue;
                    }
                    // 驼峰转下划线
                    String dtoSetName = getSetName(field.getName(), type);
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
     * 实体类转Map
     * @return map
     */
    public Map<String, Object> toMap() {
        if (!this.getClass().getSuperclass().getName().equals(CodCommonModelConvert.class.getName())){
            logger.error("不是子类. 不可转换！请继承 {} 类", CodCommonModelConvert.class.getName());
        }
        Map<String, Object> map = new HashMap<>();
        for (Field field : this.getClass().getDeclaredFields()){
            // 4. 私有的 && 非static && 非final
            if (Modifier.isPrivate(field.getModifiers()) && !Modifier.isStatic(field.getModifiers()) && !Modifier.isFinal(field.getModifiers())){
                Object value = getFieldValueByName(field.getName(), this);
                map.put(field.getName(), value);
            }
        }
        return map;
    }

    /**
     * 实体类转Map
     * @param object pojo
     * @return map
     */
    public static Map<String, Object> toMap(Object object) {
        Map<String, Object> map = new HashMap<>();
        for (Field field : object.getClass().getDeclaredFields()){
            // 4. 私有的 && 非static && 非final
            if (Modifier.isPrivate(field.getModifiers()) && !Modifier.isStatic(field.getModifiers()) && !Modifier.isFinal(field.getModifiers())){
                PropertyDescriptor pd = null;
                try {
                    pd = new PropertyDescriptor(field.getName(), object.getClass());
                    Method rM = pd.getReadMethod();
                    Object value =  rM.invoke(object);
                    map.put(field.getName(), value);
                } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }

    /**
     * 获取set方法名
     * @param name 字段名
     * @param type 转换方式
     * @return
     */
    private String getSetName(String name, ConvertType type){
        String setName = "";
        switch (type){
            case toDo:
                setName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name);
                return setName;
            case toDto:
                setName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name);
                return setName;
            default:
                break;
        }
        return setName;
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

    /**
     * 转换类型
     */
    public enum ConvertType{

        /**
         * 驼峰转下划线
         */
        toDo,

        /**
         * 下划线转驼峰
         */
        toDto
    }
}
