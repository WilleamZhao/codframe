/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.core.service;

import com.tlkj.cod.model.system.entity.CodFrameUserDo;

/**
 * Desc 用户Service
 *
 * @author sourcod
 * @version 1.0
 * @className LoginUserService
 * @date 2018/12/19 9:15 PM
 */
public interface LoginUserService {

    /**
     * 根据用户名获取用户信息
     * @param username 用户名
     * @return 用户信息
     */
    CodFrameUserDo getUserByUsername(String username);


}
