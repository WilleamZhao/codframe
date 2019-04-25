/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.core.main;

/**
 * Desc Cod框架服务启动初始化
 *
 * @author sourcod
 * @version 1.0
 * @className CodStartServerInit
 * @date 2019/4/24 6:01 PM
 */
public interface CodStartServerInit {

    /**
     * 初始化方法
     * 启动codServer之前方法, 优先级最高
     */
    void init();
}
