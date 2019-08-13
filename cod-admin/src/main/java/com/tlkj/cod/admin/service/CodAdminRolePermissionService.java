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

import com.tlkj.cod.model.enums.StatusCode;

import java.util.List;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminRolePermissionService
 * @date 2018/12/14 2:51 PM
 */
public interface CodAdminRolePermissionService {

    /**
     * 获取角色权限和全部权限
     * @param roleIds 角色id
     */
    List getRolePermission(String roleIds);

    /**
     * 保存角色权限表
     * @param roleId        角色id
     * @param permissionIds 权限id
     * @return
     */
    StatusCode saveRolePermission(String roleId, String permissionIds);
}
