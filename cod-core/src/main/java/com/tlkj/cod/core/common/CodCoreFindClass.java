/*
 * Copyright (c) 2019.
 * sourcod.com
 * All rights reserved
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.core.common;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className CodCommonFindChildClass
 * @date 2019/4/26 8:49 AM
 */
public class CodCoreFindClass {

    /**
     * 获取同一路径下所有子类或接口实现类
     * @param cls 类
     * @param pk  哪个包下, 不传取当前包
     */
    public static List<Class<?>> getAllAssignedClass(Class<?> cls, String pk) {
        if (cls == null){
            return new ArrayList<>();
        }
        List<Class<?>> classes = new ArrayList<>();
        for (Class<?> c : getClasses(cls, pk)) {
            if (cls.isAssignableFrom(c) && !c.isInterface() && !cls.equals(c)) {
                classes.add(c);
            }
        }
        return classes;
    }

    /**
     * 取得当前类路径下的所有类
     *
     */
    private static List<Class<?>> getClasses(Class<?> cls, String pk) {
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
            // 得到协议的名称
            String protocol = url.getProtocol();
            // 如果是以文件的形式保存在服务器上
            if ("file".equals(protocol)) {
                // 获取包的物理路径
                String filePath = null;
                try {
                    filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                // 以文件的方式扫描整个包下的文件 并添加到集合中
                findClassesByFile(pk, filePath, list);
            } else if ("jar".equals(protocol)) {
                // 如果是jar包文件
                // 获取jar
                JarFile jar = null;
                try {
                    jar = ((JarURLConnection) url.openConnection()).getJarFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //扫描jar包文件 并添加到集合中
                if (jar != null) {
                    findClassesByJar(pk, jar, list);
                }
            }
            // list.addAll(getClasses(new File(url.getFile()), pk));
        }
        return list;
    }

    /**
     * 迭代查找类
     *
     * @param dir
     * @param pk
     */
    private static List<Class<?>> getClasses(File dir, String pk) throws NoSuchMethodException {
        List<Class<?>> classes = new ArrayList<>();
        // TODO
        /*File[] files = dir.listFiles();
        if (files != null) {
            System.out.println("长度: " + files.length);
            for (File f : files) {
                if (f.isDirectory()) {
                    classes.addAll(getClasses(f, pk + "." + f.getName()));
                }
                String name = f.getName();
                if (name.endsWith(".class")) {
                    String path = pk + "." + name.substring(0, name.length() - 6);
                    System.out.println(path);
                    Class<?> aClass = ClassLoader.getSystemClassLoader().loadClass(path);
                    classes.add(aClass);
                    // System.out.println(path);
                    // classes.add(Class.forName(path));
                }
            }
        }*/
        return classes;
    }


    private static void findClassesByFile(String pkgName, String pkgPath, List<Class<?>> classes) {
        // 获取此包的目录 建立一个File
        File dir = new File(pkgPath);
        // 如果不存在或者 也不是目录就直接返回
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        File[] dirfiles = dir.listFiles(pathname -> pathname.isDirectory() || pathname.getName().endsWith("class"));

        if (dirfiles == null || dirfiles.length == 0) {
            return;
        }

        String className;
        Class clz;
        // 循环所有文件
        for (File f : dirfiles) {
            // 如果是目录 则继续扫描
            if (f.isDirectory()) {
                findClassesByFile(pkgName + "." + f.getName(),pkgPath + File.separator + f.getName(),classes);
                continue;
            }
            // 如果是java类文件 去掉后面的.class 只留下类名
            className = f.getName();
            className = className.substring(0, className.length() - 6);

            String path = pkgName + "." + className;

            //加载类
            clz = loadClass(path);
            // 添加到集合中去
            if (clz != null) {
                classes.add(clz);
            }
        }
    }

    private static void findClassesByJar(String pkgName, JarFile jar, List<Class<?>> classes) {
        String pkgDir = pkgName.replace(".", File.separator);
        // 从此jar包 得到一个枚举类
        Enumeration<JarEntry> entry = jar.entries();

        JarEntry jarEntry;
        String name, className;
        Class<?> claze;
        // 同样的进行循环迭代
        while (entry.hasMoreElements()) {
            // 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文
            jarEntry = entry.nextElement();

            name = jarEntry.getName();
            // 如果是以/开头的
            if (name.charAt(0) == '/') {
                // 获取后面的字符串
                name = name.substring(1);
            }

            if (jarEntry.isDirectory() || !name.startsWith(pkgDir) || !name.endsWith(".class")) {
                continue;
            }
            //如果是一个.class文件 而且不是目录
            // 去掉后面的".class" 获取真正的类名
            className = name.substring(0, name.length() - 6);
            //加载类
            claze = loadClass(className.replace(File.separator, "."));
            // 添加到集合中去
            if (claze != null) {
                classes.add(claze);
            }
        }
    }

    /**
     * 加载类
     * @param fullClzName 类全名
     * @return
     */
    private static Class<?> loadClass(String fullClzName) {
        try {
            return Thread.currentThread().getContextClassLoader().loadClass(fullClzName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
