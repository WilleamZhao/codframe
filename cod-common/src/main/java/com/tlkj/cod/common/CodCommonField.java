package com.tlkj.cod.common;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Desc cod-common 字段相关工具类
 *
 * @author sourcod
 * @version 1.0
 * @className CodCommonField
 * @date 2019/6/19 11:13 AM
 */
public class CodCommonField {

    /**
     * 判断字段是否是 私有&非静态&非final
     * @param field 字段
     * @return
     */
    public static boolean isModel(Field field){
        // 4. 私有的 && 非static && 非final
        return Modifier.isPrivate(field.getModifiers()) && !Modifier.isStatic(field.getModifiers()) && !Modifier.isFinal(field.getModifiers());
    }
}
