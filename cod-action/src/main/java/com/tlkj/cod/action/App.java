/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.action;

import com.tlkj.cod.common.CodCommonFindChildClass;
import com.tlkj.cod.core.launcher.CodLauncher;
import com.tlkj.cod.core.util.CodClassLoader;
import com.tlkj.cod.launcher.CodModuleInitialize;

import java.util.List;

/**
 * Desc 启动方法
 *
 * @author sourcod
 * @version 1.0
 * @className App
 * @date 2018/11/3 上午1:08
 */
public class App {
    public static void main(String[] args) throws Exception {
        // Start start = new Start();
        // start.main(args);
        CodLauncher.main(args);

        /*CodCommonFindChildClass codCommonFindChildClass = new CodCommonFindChildClass();
        CodClassLoader codClassLoader = new CodClassLoader();
        List list = null;
        list = CodCommonFindChildClass.getAllAssignedClass(CodModuleInitialize.class, "");
        // list = CodCommonFindChildClass.getAllAssignedClass(CodModuleInitialize.class, new String[]{});
        for (Object o : list){
            System.out.println("实现" + o.toString());
        }*/
    }
}
