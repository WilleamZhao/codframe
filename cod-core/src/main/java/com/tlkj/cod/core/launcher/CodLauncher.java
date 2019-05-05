/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.core.launcher;

import com.tlkj.cod.common.CodCommonFindChildClass;
import com.tlkj.cod.core.launcher.init.InitSpring;
import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.CodModuleOrderEnum;
import com.tlkj.cod.launcher.CodServerInitialize;
import com.tlkj.cod.launcher.model.LauncherModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * Desc codFrame框架 启动引导
 *
 * @author sourcod
 * @version 1.0
 * @className com.tlkj.cod.launcher.CodLauncher
 * @date 2019/4/25 4:51 PM
 */
public class CodLauncher {

    // private static List<Integer> list = new ArrayList<>();
    // private static LinkedList<CodModuleInitialize> linkedList = new LinkedList<>();

    private static List list = new ArrayList();
    private static final LauncherModel launcherModel = new LauncherModel();
    private static Logger logger = LoggerFactory.getLogger(CodLauncher.class);

    /**
     * 1. 加载模块
     * 2. 加载server init
     * 3. 顺序执行
     * 4. 启动完成
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("开始启动了");
        if (launcherModel.isStart()){
            return;
        }
        CodModuleInitialize codModuleInitialize = null;

        LinkedList<CodModuleInitialize> linkedList = new LinkedList<>();

        // 插入排序
        List<Integer> list = new ArrayList<>();
        try {
            for (Class<?> c : CodCommonFindChildClass.getAllAssignedClass(CodModuleInitialize.class, "com.tlkj.cod")) {
                codModuleInitialize = (CodModuleInitialize) c.newInstance();
                int order = codModuleInitialize.order();
                if (order == CodModuleOrderEnum.SPRING.getOrder() && !c.isAssignableFrom(InitSpring.class)){
                    System.out.println("order " + CodModuleOrderEnum.SPRING.getOrder() + " 是保留序号. 跳过");
                    continue;
                }

                if (order == CodModuleOrderEnum.SERVER.getOrder() && !CodServerInitialize.class.isAssignableFrom(c)){
                    System.out.println("order " + CodModuleOrderEnum.SERVER.getOrder() + " 是保留序号. 跳过");
                    continue;
                }

                int i = 0;
                for (int o : list){
                    if (o < order){
                        i++;
                    }
                }
                list.add(order);
                linkedList.add(i, codModuleInitialize);
            }
        } catch (IllegalAccessException | InstantiationException e) {
            System.out.println("启动异常");
            e.printStackTrace();
        }
        for (CodModuleInitialize module : linkedList){
            try {
                logger.info("开始加载 {} 模块", module.name());
                module.init(launcherModel);
                logger.info("加载 {} 模块完成", module.name());
            } catch (Exception e){
                // TODO cod set log Debug
                logger.info("加载 {} 模块是吧", module.name());
                e.printStackTrace();
                module.fail(e);
            }
            /*if (module.order() == 0) {
                module.init(launcherModel);
                // initSpring();
            } else if (module.order() == 100){

            } else {
                module.init(launcherModel);
            }*/
        }
        System.out.println("启动模块数量:" + list.size());
        System.out.println("codFrame框架 启动完成.");
    }

    private void orderModule(int order, LinkedList list, CodModuleInitialize codModuleInitialize){
        // 插入排序
        List<Integer> list1 = new ArrayList<>();
        int i = 0;
        for (int o : list1){
            if (o < order){
                i++;
            }
        }
        list1.add(order);
        list.add(i, codModuleInitialize);
    }





}
