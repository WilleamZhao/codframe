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

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * Desc Model转换类
 *
 * @author sourcod
 * @version 1.0
 * @className CodCommonModelConvert
 * @date 2019/3/12 10:17 PM
 */
public class CodCommonModelConvert {

    public static <T> T Do2Dto(Object o, Class<T> zlass){
        Field[] fields = zlass.getDeclaredFields();
        for (Field field : fields){
            field.getName();
        }
        return null;
    }
}
