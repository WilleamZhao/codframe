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

import org.apache.commons.lang3.StringUtils;
import sun.rmi.rmic.iiop.ClassPathLoader;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className CodCommonFindChildClass
 * @date 2019/4/26 8:49 AM
 */
public class CodCommonFindChildClass {

    /**
     * 获取同一路径下所有子类或接口实现类
     * @param cls 类
     * @param pk  哪个包下, 不传取当前包
     */
    public static List<Class<?>> getAllAssignedClass(Class<?> cls, String pk) throws ClassNotFoundException {
        if (cls == null){
            return new ArrayList<>();
        }
        List<Class<?>> classes = new ArrayList<>();
        for (Class<?> c : getClasses(cls, pk)) {
            if (cls.isAssignableFrom(c) && !cls.equals(c)) {
                classes.add(c);
            }
        }
        return classes;
    }

    /**
     * 取得当前类路径下的所有类
     *
     */
    private static List<Class<?>> getClasses(Class<?> cls, String pk) throws ClassNotFoundException {
        if (StringUtils.isBlank(pk)){
            pk = cls.getPackage().getName();
        }
        String path = pk.replace('.', File.separatorChar);
        ClassLoader classloader = ClassLoader.getSystemClassLoader();
        Enumeration<URL> urls = null;
        try {
            urls = classloader.getResources(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (urls == null){
            return new ArrayList<>();
        }
        List<Class<?>> list = new ArrayList<>();
        while (urls.hasMoreElements()){
            URL url = urls.nextElement();
            list.addAll(getClasses(new File(url.getFile()), pk));
        }
        return list;
    }

    /**
     * 迭代查找类
     *
     * @param dir
     * @param pk
     */
    private static List<Class<?>> getClasses(File dir, String pk) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        if (!dir.exists()) {
            return classes;
        }
        File[] files = dir.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    classes.addAll(getClasses(f, pk + "." + f.getName()));
                }
                String name = f.getName();
                if (name.endsWith(".class")) {
                    String path = pk + "." + name.substring(0, name.length() - 6);
                    Class<?> aClass = ClassLoader.getSystemClassLoader().loadClass(path);
                    classes.add(aClass);
                    // System.out.println(path);
                    // classes.add(Class.forName(path));
                }
            }
        }
        return classes;
    }
}
