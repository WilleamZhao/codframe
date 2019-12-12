/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.admin.service;

import com.tlkj.cod.admin.model.dto.CodAdminRoleListDto;
import com.tlkj.cod.model.enums.StatusCode;

import java.util.List;

/**
 * Desc 用户角色Service
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminUserRoleService
 * @date 2018/12/9 7:15 PM
 */
public interface CodAdminUserRoleService {

    /**
     * 根据用户id获取角色Ids
     * @param userId 用户id
     * @return
     */
    String getRoleIds(String userId);

    /**
     * 根据用户查询角色
     * @param userId 用户id
     */
    List<CodAdminRoleListDto> listRole(String userId);

    /**
     * 添加用户角色
     * @param userId  用户id
     * @param roleIds 角色ids
     * @return 是否添加成功
     */
    StatusCode addUserRole(String userId, String roleIds);

    /**
     * 删除用户角色
     * @param ids 用户角色ids
     * @return 是否删除成功
     */
    StatusCode delUserRole(String ids);

    /**
     * 根据用户id删除用户角色
     * @param userId 用户id
     * @return 是否删除成功
     */
    StatusCode delUserRoleByUserId(String userId);
}
