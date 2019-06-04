/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.admin.facade;

import com.tlkj.cod.model.enums.StatusCode;

import java.util.List;

/**
 * Desc 用户Facade
 *
 * @author sourcod
 * @version 1.0
 * @className UserFacade
 * @date 2018/12/14 9:03 PM
 */
public interface UserFacade {

    /**
     * 根据用户id获取用户角色和全部角色
     * @param userId 用户id
     */
    List getRole(String userId);

    /**
     * 编辑部门
     * @param userId 用户id
     */
    List editDept(String userId);

    /**
     * 修改密码
     * @param token       token
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    StatusCode updatePassword(String token, String oldPassword, String newPassword);
}
