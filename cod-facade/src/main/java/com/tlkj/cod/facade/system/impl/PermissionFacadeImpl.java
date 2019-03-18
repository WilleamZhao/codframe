/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.facade.system.impl;

import com.tlkj.cod.facade.system.PermissionFacade;
import com.tlkj.cod.model.system.dto.CodFramePermissionTreeDto;
import com.tlkj.cod.model.system.dto.CodFrameRoleListDto;
import com.tlkj.cod.service.system.PermissionService;
import com.tlkj.cod.service.system.UserRoleService;
import com.tlkj.cod.service.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Desc 权限facade
 *
 * @author sourcod
 * @version 1.0
 * @className PermissionFacadeImpl
 * @date 2018/12/13 11:54 AM
 */
@Service
public class PermissionFacadeImpl implements PermissionFacade {

    @Autowired
    UserService userService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    PermissionService permissionService;

    /**
     * 根据用户id获取权限
     * @param userId 用户id
     * @return 权限列表
     */
    @Override
    public List<CodFramePermissionTreeDto> getPermissionTreeByUserId(String userId) {
        List<CodFrameRoleListDto> dtos = userRoleService.listRole(userId);
        String roleIds = dtos.stream().map(CodFrameRoleListDto::getId).collect(Collectors.joining(","));
        return permissionService.getPermissionTree(roleIds);
    }

    @Override
    public CodFramePermissionTreeDto getResource() {
        CodFrameRoleListDto dto = new CodFrameRoleListDto();

        return null;
    }
}
