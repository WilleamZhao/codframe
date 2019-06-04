/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.admin.facade.impl;

import com.tlkj.cod.admin.facade.MenuFacade;
import com.tlkj.cod.admin.service.MenuService;
import com.tlkj.cod.admin.service.RoleMenuService;
import com.tlkj.cod.admin.service.UserRoleService;
import com.tlkj.cod.admin.service.UserService;
import com.tlkj.cod.model.system.dto.CodFrameMenuDto;
import com.tlkj.cod.model.system.dto.CodFrameUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Desc 菜单facadeImpl
 *
 * @author sourcod
 * @version 1.0
 * @className MenuFacadeImpl
 * @date 2018/12/26 2:10 PM
 */
@Service
public class MenuFacadeImpl implements MenuFacade {

    @Autowired
    UserService userService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    RoleMenuService roleMenuService;

    @Autowired
    MenuService menuService;

    /**
     * 根据token获取菜单
     * 1. 获取用户信息
     * 2. 获取角色信息
     * 3. 获取菜单信息
     * 4. 获取菜单
     * @param token token
     * @return 左侧菜单
     */
    @Override
    public List<CodFrameMenuDto> getMenu(String token) {
        // 1. 获取用户信息
        CodFrameUserDto dto = userService.getUserByCache(token);
        if (dto == null){
            return null;
        }
        // 2. 获取角色信息
        String roleIds = userRoleService.getRoleIds(dto.getId());
        // 3. 获取菜单信息
        String menuIds = roleMenuService.getMenuIds(roleIds);
        // 4. 获取菜单
        return menuService.getMenu(menuIds);
    }
}
