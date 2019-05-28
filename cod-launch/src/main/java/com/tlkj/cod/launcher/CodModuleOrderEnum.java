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
    DATA(-100),

    /**
     * 启动spring
     */
    SPRING(0),

    /**
     * 启动服务
     */
    SERVER(100);

    private int order;

    CodModuleOrderEnum(int order){
        this.order = order;
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

    public static void main(String[] args) {
        System.out.println(CodModuleOrderEnum.SPRING.getOrder());
    }
}
