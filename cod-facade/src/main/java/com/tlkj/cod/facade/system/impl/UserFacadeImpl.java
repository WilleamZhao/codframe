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

import com.tlkj.cod.core.facade.LoginUserFacade;
import com.tlkj.cod.facade.system.UserFacade;
import com.tlkj.cod.model.enums.StatusCode;
import com.tlkj.cod.model.system.dto.CodFrameDeptListDto;
import com.tlkj.cod.model.system.dto.CodFrameRoleListDto;
import com.tlkj.cod.model.system.dto.CodFrameUserDeptListDto;
import com.tlkj.cod.model.system.dto.CodFrameUserDto;
import com.tlkj.cod.service.system.DeptService;
import com.tlkj.cod.service.system.PermissionService;
import com.tlkj.cod.service.system.RolePermissionService;
import com.tlkj.cod.service.system.RoleService;
import com.tlkj.cod.service.system.UserDeptService;
import com.tlkj.cod.service.system.UserRoleService;
import com.tlkj.cod.service.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className UserFacadeImpl
 * @date 2018/12/14 9:03 PM
 */
@Service
public class UserFacadeImpl implements UserFacade, LoginUserFacade {

    @Autowired
    RoleService roleService;

    @Autowired
    DeptService deptService;

    @Autowired
    UserDeptService userDeptService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;

    @Autowired
    RolePermissionService rolePermissionService;

    @Autowired
    PermissionService permissionService;

    @Override
    public List getRole(String userId) {
        // 1. 获取全部角色
        List<CodFrameRoleListDto> allRole = roleService.listRole("1", "999", "", "", "").getData();

        // 2. 获取用户角色
        List<CodFrameRoleListDto> userRole = userRoleService.listRole(userId);

        // id字符串
        String tempDtoIds = userRole.stream().map(CodFrameRoleListDto::getId).collect(Collectors.joining(","));

        // 差集
        List<CodFrameRoleListDto> temp = allRole.stream().filter(d -> !tempDtoIds.contains(d.getId())).collect(Collectors.toList());

        List<List<CodFrameRoleListDto>> lists = new ArrayList<>();
        lists.add(temp);
        lists.add(userRole);
        return lists;
    }

    @Override
    public List editDept(String userId) {
        // 1. 获取全部部门
        List<CodFrameDeptListDto> deptListDtos = deptService.listDept("", "", "", "1", "999").getData();

        // 2. 获取用户部门
        List<CodFrameUserDeptListDto> userDeptListDtos = userDeptService.listDeptByUserId(userId);

        // id字符串
        String tempDeptIds = userDeptListDtos.stream().map(CodFrameUserDeptListDto::getDeptId).collect(Collectors.joining(","));

        // 差集
        List<CodFrameDeptListDto> temp = deptListDtos.stream().filter(d -> !tempDeptIds.contains(d.getId())).collect(Collectors.toList());

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
        CodFrameUserDto codFrameUserDto = userService.getUserByCache(token);
        if (codFrameUserDto == null){
            return StatusCode.LOGIN_FAIL_CODE;
        }
        StatusCode statusCode = StatusCode.FAIL_CODE;
        if (codFrameUserDto.getLoginPass().equals(oldPassword)){
            statusCode = userService.updatePassword(codFrameUserDto.getId(), newPassword);
        }
        return statusCode;
    }

    @Override
    public List getPermission(String id) {
        List<CodFrameRoleListDto> dtos = userRoleService.listRole(id);
        String roleIds = dtos.stream().map(CodFrameRoleListDto::getId).collect(Collectors.joining(","));
        return permissionService.getPermissionTree(roleIds);
    }
}
