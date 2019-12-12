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

import com.tlkj.cod.admin.facade.CodAdminUserFacade;
import com.tlkj.cod.admin.model.dto.CodAdminDeptListDto;
import com.tlkj.cod.admin.model.dto.CodAdminRoleListDto;
import com.tlkj.cod.admin.model.dto.CodAdminUserDeptListDto;
import com.tlkj.cod.admin.model.dto.CodAdminUserDto;
import com.tlkj.cod.admin.service.CodAdminDeptService;
import com.tlkj.cod.admin.service.CodAdminPermissionService;
import com.tlkj.cod.admin.service.CodAdminRolePermissionService;
import com.tlkj.cod.admin.service.CodAdminRoleService;
import com.tlkj.cod.admin.service.CodAdminUserDeptService;
import com.tlkj.cod.admin.service.CodAdminUserRoleService;
import com.tlkj.cod.admin.service.CodAdminUserService;
import com.tlkj.cod.core.facade.LoginUserFacade;
import com.tlkj.cod.model.enums.StatusCode;
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
 * @className CodAdminUserFacadeImpl
 * @date 2018/12/14 9:03 PM
 */
@Service
public class CodAdminUserFacadeImpl implements CodAdminUserFacade, LoginUserFacade {

    @Autowired
    CodAdminRoleService codAdminRoleService;

    @Autowired
    CodAdminDeptService codAdminDeptService;

    @Autowired
    CodAdminUserDeptService codAdminUserDeptService;

    @Autowired
    CodAdminUserRoleService codAdminUserRoleService;

    @Autowired
    CodAdminUserService codAdminUserService;

    @Autowired
    CodAdminRolePermissionService codAdminRolePermissionService;

    @Autowired
    CodAdminPermissionService codAdminPermissionService;

    @Override
    public List getRole(String userId) {
        // 1. 获取全部角色
        List<CodAdminRoleListDto> allRole = codAdminRoleService.listRole("1", "999", "", "", "").getData();

        // 2. 获取用户角色
        List<CodAdminRoleListDto> userRole = codAdminUserRoleService.listRole(userId);

        // id字符串
        String tempDtoIds = userRole.stream().map(CodAdminRoleListDto::getId).collect(Collectors.joining(","));

        // 差集
        List<CodAdminRoleListDto> temp = allRole.stream().filter(d -> !tempDtoIds.contains(d.getId())).collect(Collectors.toList());

        List<List<CodAdminRoleListDto>> lists = new ArrayList<>();
        lists.add(temp);
        lists.add(userRole);
        return lists;
    }

    @Override
    public List editDept(String userId) {
        // 1. 获取全部部门
        List<CodAdminDeptListDto> deptListDtos = codAdminDeptService.listDept("", "", "", "1", "999").getData();

        // 2. 获取用户部门
        List<CodAdminUserDeptListDto> userDeptListDtos = codAdminUserDeptService.listDeptByUserId(userId);

        // id字符串
        String tempDeptIds = userDeptListDtos.stream().map(CodAdminUserDeptListDto::getDeptId).collect(Collectors.joining(","));

        // 差集
        List<CodAdminDeptListDto> temp = deptListDtos.stream().filter(d -> !tempDeptIds.contains(d.getId())).collect(Collectors.toList());

        List lists = new ArrayList<>();
        lists.add(temp);
        lists.add(userDeptListDtos);
        return lists;
    }

    /**
     * 修改密码
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return
     */
    @Override
    public StatusCode updatePassword(String token, String oldPassword, String newPassword) {
        CodAdminUserDto codAdminUserDto = codAdminUserService.getUserByCache(token);
        if (codAdminUserDto == null){
            return StatusCode.LOGIN_FAIL_CODE;
        }
        StatusCode statusCode = StatusCode.FAIL_CODE;
        if (codAdminUserDto.getLoginPass().equals(oldPassword)){
            statusCode = codAdminUserService.updatePassword(codAdminUserDto.getId(), newPassword);
        }
        return statusCode;
    }

    @Override
    public List getPermission(String id) {
        List<CodAdminRoleListDto> dtos = codAdminUserRoleService.listRole(id);
        String roleIds = dtos.stream().map(CodAdminRoleListDto::getId).collect(Collectors.joining(","));
        return codAdminPermissionService.getPermissionTree(roleIds);
    }
}
