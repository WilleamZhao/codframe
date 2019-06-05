/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.admin.service.impl;

import com.tlkj.cod.admin.service.CodAdminRoleMenuService;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Updater;
import com.tlkj.cod.model.enums.StatusCode;
import com.tlkj.cod.model.system.entity.CodFrameRoleMenuDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Desc 角色菜单ServiceImpl
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminRoleMenuServiceImpl
 * @date 2018/12/26 1:20 PM
 */
@Service
public class CodAdminRoleMenuServiceImpl implements CodAdminRoleMenuService {

    @Autowired
    private Finder finder;

    @Autowired
    private Updater updater;

    /**
     * 添加角色菜单
     * @param roleId  角色id
     * @param menuIds 菜单ids
     * @return
     */
    @Override
    public StatusCode save(String roleId, String menuIds) {
        String[] strings = menuIds.split(",");

        // 删除现有菜单
        updater.delete(CodFrameRoleMenuDo.TABLE_NAME).update();

        int i = 0;
        for (String s : strings){
            Updater.Update update = updater.insert(CodFrameRoleMenuDo.TABLE_NAME).set("role_id", roleId).setId();
            update.set("menu_id", s);
            i += update.update();
        }

        if (strings.length == i){
            return StatusCode.SUCCESS_CODE;
        }
        return StatusCode.FAIL_CODE;
    }

    /**
     * 获取角色下菜单
     * @param roleIds 角色ids
     * @return
     */
    @Override
    public String getMenuIds(String roleIds) {
        Object[] strings = roleIds.split(",");
        List<CodFrameRoleMenuDo> roleMenuDo = finder.from(CodFrameRoleMenuDo.TABLE_NAME).in("role_id", strings).all(CodFrameRoleMenuDo.class);
        String menuIds = roleMenuDo.stream().map(CodFrameRoleMenuDo::getMenu_id).distinct().collect(Collectors.joining(","));
        return menuIds;
    }
}
