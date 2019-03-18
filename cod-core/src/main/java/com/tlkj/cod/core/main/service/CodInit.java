/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.core.main.service;

/**
 * Desc Cod初始化接口
 *
 * @author sourcod
 * @version 1.0
 * @className CodInit
 * @date 2019/2/12 10:33 AM
 */
public interface CodInit {

    /**
     * 启动接口
     * @return
     */
    boolean startup();

    /**
     * 环绕接口
     * @return
     */
    boolean around();

    /**
     * 停止接口
     * @return
     */
    boolean shutdown();
}
