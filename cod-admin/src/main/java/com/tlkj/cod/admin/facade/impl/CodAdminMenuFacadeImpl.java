/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.admin.facade.impl;

import com.tlkj.cod.admin.facade.CodAdminMenuFacade;
import com.tlkj.cod.admin.model.dto.CodAdminMenuDto;
import com.tlkj.cod.admin.model.dto.CodAdminUserDto;
import com.tlkj.cod.admin.service.CodAdminMenuService;
import com.tlkj.cod.admin.service.CodAdminUserRoleService;
import com.tlkj.cod.admin.service.CodAdminRoleMenuService;
import com.tlkj.cod.admin.service.CodAdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Desc 菜单facadeImpl
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminMenuFacadeImpl
 * @date 2018/12/26 2:10 PM
 */
@Service
public class CodAdminMenuFacadeImpl implements CodAdminMenuFacade {

    @Autowired
    CodAdminUserService codAdminUserService;

    @Autowired
    CodAdminUserRoleService codAdminUserRoleService;

    @Autowired
    CodAdminRoleMenuService codAdminRoleMenuService;

    @Autowired
    CodAdminMenuService codAdminMenuService;

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
    public List<CodAdminMenuDto> getMenu(String token) {
        // 1. 获取用户信息
        CodAdminUserDto dto = codAdminUserService.getUserByCache(token);
        if (dto == null){
            return null;
        }
        // 2. 获取角色信息
        String roleIds = codAdminUserRoleService.getRoleIds(dto.getId());
        // 3. 获取菜单信息
        String menuIds = codAdminRoleMenuService.getMenuIds(roleIds);
        // 4. 获取菜单
        return codAdminMenuService.getMenu(menuIds);
    }
}
