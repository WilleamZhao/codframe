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

import java.util.List;

/**
 * Desc 角色菜单Facade
 *
 * @author sourcod
 * @version 1.0
 * @className RoleMenuFacade
 * @date 2018/12/26 1:55 PM
 */
public interface RoleMenuFacade {

    /**
     * 根据角色Id获取菜单列表
     * 去掉重复菜单
     * @return 菜单列表
     */
    List getMenuByRole(String roleIds);

    /**
     * 获取角色菜单和去掉角色菜单的全部菜单
     * @param roleId 角色id
     */
    List listAuthorization(String roleId);
}
