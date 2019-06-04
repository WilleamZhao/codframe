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

import com.tlkj.cod.admin.facade.RoleFacade;
import com.tlkj.cod.admin.service.MenuService;
import com.tlkj.cod.admin.service.PermissionService;
import com.tlkj.cod.admin.service.RoleMenuService;
import com.tlkj.cod.model.system.dto.CodFrameMenuListDto;
import com.tlkj.cod.model.system.dto.CodFramePermissionTreeDto;
import org.apache.commons.lang3.StringUtils;
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
 * @className RoleFacadeImpl
 * @date 2018/12/14 2:56 PM
 */
@Service
public class RoleFacadeImpl implements RoleFacade {

    @Autowired
    PermissionService permissionService;

    @Autowired
    RoleMenuService roleMenuService;

    @Autowired
    MenuService menuService;

    /**
     * 获取权限
     * 1. 获取全部权限
     * 2. 获取该角色已有权限
     * 3. 取差集
     * 4. 返回
     * @param roleIds 角色id
     * @return
     */
    @Override
    public List getPermission(String roleIds) {
        // 1. 获取全部权限
        List<CodFramePermissionTreeDto> dtoAll = permissionService.listPermission("1", "999", "", "", "").getData();

        // 2. 获取角色权限
        List<CodFramePermissionTreeDto> dtos = permissionService.getPermissionTree(roleIds);
        // id字符串
        String tempDtoIds = dtos.stream().map(CodFramePermissionTreeDto::getId).collect(Collectors.joining(","));

        List<List<CodFramePermissionTreeDto>> lists = new ArrayList<>();

        // 差集
        List<CodFramePermissionTreeDto> temp = dtoAll.stream().filter(d -> !tempDtoIds.contains(d.getId())).collect(Collectors.toList());

        lists.add(temp);
        lists.add(dtos);
        return lists;
    }

    /**
     * 编辑菜单
     * @param roleIds 角色id
     * @return
     */
    @Override
    public List editMenu(String roleIds) {
        // 1. 根据角色获取菜单
        String menuIds = roleMenuService.getMenuIds(roleIds);
        List<CodFrameMenuListDto> menuListDtos = new ArrayList<>();
        if (StringUtils.isNotBlank(menuIds)){
            menuListDtos = menuService.listMenu(menuIds);
        }

        // 2. 获取全部菜单
        List<CodFrameMenuListDto> codFrameMenuListDtos = menuService.listMenu("");

        // 3. 取差集
        codFrameMenuListDtos = codFrameMenuListDtos.stream().filter(d -> !menuIds.contains(d.getId())).collect(Collectors.toList());
        List<List<CodFrameMenuListDto>> lists = new ArrayList<>();
        lists.add(codFrameMenuListDtos);
        lists.add(menuListDtos);
        return lists;
    }
}
