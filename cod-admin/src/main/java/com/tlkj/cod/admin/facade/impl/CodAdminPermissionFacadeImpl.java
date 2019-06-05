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

import com.tlkj.cod.admin.facade.CodAdminPermissionFacade;
import com.tlkj.cod.admin.service.CodAdminPermissionService;
import com.tlkj.cod.admin.service.CodAdminUserRoleService;
import com.tlkj.cod.admin.service.CodAdminUserService;
import com.tlkj.cod.model.system.dto.CodFramePermissionTreeDto;
import com.tlkj.cod.model.system.dto.CodFrameRoleListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Desc 权限facade
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminPermissionFacadeImpl
 * @date 2018/12/13 11:54 AM
 */
@Service
public class CodAdminPermissionFacadeImpl implements CodAdminPermissionFacade {

    @Autowired
    CodAdminUserService codAdminUserService;

    @Autowired
    CodAdminUserRoleService codAdminUserRoleService;

    @Autowired
    CodAdminPermissionService codAdminPermissionService;

    /**
     * 根据用户id获取权限
     * @param userId 用户id
     * @return 权限列表
     */
    @Override
    public List<CodFramePermissionTreeDto> getPermissionTreeByUserId(String userId) {
        List<CodFrameRoleListDto> dtos = codAdminUserRoleService.listRole(userId);
        String roleIds = dtos.stream().map(CodFrameRoleListDto::getId).collect(Collectors.joining(","));
        return codAdminPermissionService.getPermissionTree(roleIds);
    }

    @Override
    public CodFramePermissionTreeDto getResource() {
        CodFrameRoleListDto dto = new CodFrameRoleListDto();

        return null;
    }
}
