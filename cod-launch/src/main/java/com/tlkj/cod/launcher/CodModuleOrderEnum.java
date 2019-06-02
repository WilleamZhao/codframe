/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.launcher;

import com.tlkj.cod.launcher.init.CodDataInitialize;
import com.tlkj.cod.launcher.init.CodServerInitialize;
import com.tlkj.cod.launcher.init.CodServletInitialize;
import com.tlkj.cod.launcher.init.CodSpringInitialize;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className CodModuleOrderEnum
 * @date 2019/4/26 6:36 PM
 */
public enum CodModuleOrderEnum {

    /**
     * 启动data
     */
    DATA(-100, CodDataInitialize.class),

    /**
     * 启动spring
     */
    SPRING(0, CodSpringInitialize.class),

    /**
     * 启动servlet
     */
    SERVLET(50, CodServletInitialize.class),

    /**
     * 启动服务
     */
    SERVER(100, CodServerInitialize.class);

    private int order;

    private Class zlass;

    CodModuleOrderEnum(int order, Class zlass){
        this.order = order;
        this.zlass = zlass;
    }

    public int getOrder() {
        return order;
    }



    /**
     * 判断是否可用
     */
    public static boolean isAvailable(int order){
        for (CodModuleOrderEnum orderEnum : CodModuleOrderEnum.values()){
            if (orderEnum.order == order){
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否可用
     */
    public static boolean isAvailable(int order, Class zlass){
        for (CodModuleOrderEnum orderEnum : CodModuleOrderEnum.values()){
            if (orderEnum.order == order && !orderEnum.zlass.isAssignableFrom(zlass)){
                System.out.println("order " + orderEnum.order + " 是保留序号; class = " + zlass.getName());
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(CodModuleOrderEnum.SPRING.getOrder());
    }
}
