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

import com.tlkj.cod.admin.facade.CodAdminRoleMenuFacade;
import com.tlkj.cod.admin.service.CodAdminMenuService;
import com.tlkj.cod.admin.service.CodAdminRoleMenuService;
import com.tlkj.cod.model.system.dto.CodFrameMenuListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminRoleMenuFacadeImpl
 * @date 2018/12/26 1:56 PM
 */
@Service
public class CodAdminRoleMenuFacadeImpl implements CodAdminRoleMenuFacade {

    @Autowired
    private CodAdminRoleMenuService codAdminRoleMenuService;

    @Autowired
    private CodAdminMenuService codAdminMenuService;
    /**
     * 根据角色id获取菜单&去重
     * @param roleIds
     * @return
     */
    @Override
    public List getMenuByRole(String roleIds) {
        String menuIds = codAdminRoleMenuService.getMenuIds(roleIds);
        menuIds.split(",");
        return null;
    }

    /**
     * 获取授权菜单
     * @param roleIds 角色ids
     * @return
     */
    @Override
    public List listAuthorization(String roleIds) {
        // 1. 根据角色获取菜单
        String menuIds = codAdminRoleMenuService.getMenuIds(roleIds);
        List<CodFrameMenuListDto> menuListDtos = codAdminMenuService.listMenu(menuIds);

        // 2. 获取全部菜单
        List<CodFrameMenuListDto> codFrameMenuListDtos = codAdminMenuService.listMenu("1", "999", "", "", "", "").getData();

        // 3. 取差集
        codFrameMenuListDtos = codFrameMenuListDtos.stream().filter(d -> !menuIds.contains(d.getId())).collect(Collectors.toList());
        List<List<CodFrameMenuListDto>> lists = new ArrayList<>();
        lists.add(menuListDtos);
        lists.add(codFrameMenuListDtos);
        return lists;
    }
}
