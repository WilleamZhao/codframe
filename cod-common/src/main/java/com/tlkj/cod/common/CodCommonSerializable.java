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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Desc 序列化工具
 *
 * @author sourcod
 * @version 1.0
 * @className CodCommonSerializable
 * @date 2019/2/13 4:06 PM
 */
public class CodCommonSerializable implements Serializable {

    private static final long serialVersionUID = 2052299797514689440L;

    public void test(){
        System.out.println("测试成功!");
    }

    /**
     * 序列化
     * @param obj 对象
     * @return 序列化后字符串
     */
    public static String serialize(Object obj)  {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        String string = "";
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            string = byteArrayOutputStream.toString("ISO-8859-1");
        } catch (IOException e) {
            System.out.println("序列化失败");
        } finally {
            try {
                objectOutputStream.close();
                byteArrayOutputStream.close();
            } catch (IOException e) {
                System.out.println("关闭流异常");
            }
        }
        return string;
    }

    /**
     * 反序列化
     * @param str 字符串
     * @return 对象
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object serializeToObject (String str) throws IOException, ClassNotFoundException {
        return serializeToObject(str, Object.class);
    }

    /**
     * 反序列化
     * @param str 字符串
     * @return 对象
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static <E> E serializeToObject (String str, Class<E> zlass) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        E object =  zlass.cast(objectInputStream.readObject());
        objectInputStream.close();
        byteArrayInputStream.close();
        return object;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        String ser = serialize(new CodCommonSerializable());
        System.out.println(ser);
        System.out.println(ser.length());

        CodCommonSerializable codCommonSerializable = serializeToObject(ser, CodCommonSerializable.class);

        codCommonSerializable.test();
    }
}
