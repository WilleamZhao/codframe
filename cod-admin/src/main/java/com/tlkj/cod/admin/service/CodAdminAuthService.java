/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.admin.service;

/**
 * Desc 身份验证 service
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminAuthService
 * @date 2019/11/9 12:38 PM
 */
public interface CodAdminAuthService {

    /**
     * 获取 token
     * @return
     */
    String getToken(String username, String password);

    /**
     * 验证
     * @return
     */
    String verify();



}
