package com.tlkj.cod.util;

import com.tlkj.cod.common.CodCommonFindChildClass;
import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.CodModuleOrderEnum;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className CodUtilPrintModule
 * @date 2019/6/5 1:52 PM
 */
public class CodUtilPrintModule {
    public static void main(String[] args) {
        CodModuleInitialize codModuleInitialize = null;

        LinkedList<CodModuleInitialize> linkedList = new LinkedList<>();

        // 插入排序
        List<Integer> list = new ArrayList<>();
        try {
            for (Class<?> c : CodCommonFindChildClass.getAllAssignedClass(CodModuleInitialize.class, "com.tlkj.cod")) {
                codModuleInitialize = (CodModuleInitialize) c.newInstance();
                int order = codModuleInitialize.order();
                if (!CodModuleOrderEnum.isAvailable(order, c)){
                    continue;
                }

                // 不加载
                if (codModuleInitialize.order() == Integer.MIN_VALUE){
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

        for (CodModuleInitialize codModuleInitialize1 : linkedList){
            System.out.println(codModuleInitialize1.order() + codModuleInitialize1.name());
        }
    }
}
