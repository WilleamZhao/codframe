/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.admin.service;

import com.tlkj.cod.model.enums.StatusCode;

/**
 * Desc 角色菜单Service
 *
 * @author sourcod
 * @version 1.0
 * @className RoleMenuService
 * @date 2018/12/26 1:19 PM
 */
public interface RoleMenuService {

    /**
     * 保存角色菜单
     * @param roleId  角色id
     * @param menuIds 菜单ids
     * @return 是否保存成功
     */
    StatusCode save(String roleId, String menuIds);

    /**
     * 获取角色菜单Ids
     * @param roleIds 角色ids
     */
    String getMenuIds(String roleIds);


}
