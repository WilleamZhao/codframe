/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.admin.service.impl;

import com.tlkj.cod.admin.model.dto.CodAdminRoleListDto;
import com.tlkj.cod.admin.model.entity.CodAdminRoleDo;
import com.tlkj.cod.admin.model.entity.CodAdminUserRoleDo;
import com.tlkj.cod.admin.service.CodAdminUserRoleService;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Updater;
import com.tlkj.cod.model.enums.StatusCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Desc 用户角色
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminUserRoleServiceImpl
 * @date 2018/12/9 7:50 PM
 */
@Service
public class CodAdminUserRoleServiceImpl implements CodAdminUserRoleService {

    @Autowired
    Finder finder;

    @Autowired
    Updater updater;

    /**
     * 根据用户Id获取角色Ids
     * @param userId 用户id
     * @return 角色Ids
     */
    @Override
    public String getRoleIds(String userId) {
        String roleIds = finder.from(CodAdminUserRoleDo.TABLE_NAME).where("user_id", userId).all(CodAdminUserRoleDo.class).stream()
                .map(CodAdminUserRoleDo::getRole_id).collect(Collectors.joining(","));
        return roleIds;
    }

    /**
     * 获取用户角色
     * @param userId 用户id
     * @return
     */
    @Override
    public List<CodAdminRoleListDto> listRole(String userId) {
        Finder.Query query = finder.from(CodAdminUserRoleDo.TABLE_NAME + " userRole").join(" join " + CodAdminRoleDo.TABLE_NAME + " role on userRole.role_id = userRole.user_id");
        List<CodAdminRoleDo> dos = query.where("user_id", userId).all(CodAdminRoleDo.class);
        List<CodAdminRoleListDto> dtos = new ArrayList<>();
        dos.forEach(item -> {
            CodAdminRoleListDto dto = new CodAdminRoleListDto();
            dto.setId(item.getId());
            dto.setSort(item.getSort());
            dto.setStatus(item.getState());
            dto.setRoleName(item.getRole_name());
            dto.setRoleDesc(item.getRole_desc());
            dto.setRoleRemark(item.getRole_remark());
            dto.setId(item.getId());
            dtos.add(dto);
        });
        return dtos;
    }

    /**
     * 添加用户角色
     * @param userId  用户id
     * @param roleIds 角色ids
     * @return
     */
    @Override
    public StatusCode addUserRole(String userId, String roleIds) {
        String[] roles = roleIds.split(",");
        // 1. 删除该用户下所有角色
        updater.delete(CodAdminUserRoleDo.TABLE_NAME).where("user_id", userId).update();
        // 2. 新增角色
        int i = 0;
        for (String roleId : roles){
            i += updater.insert(CodAdminUserRoleDo.TABLE_NAME).setId().set("user_id", userId).set("role_id", roleId).update();
        }
        if (i == roles.length){
            return StatusCode.SUCCESS_CODE;
        }
        return StatusCode.FAIL_CODE;
    }

    /**
     * 删除用户角色
     * @param ids 用户角色ids
     * @return
     */
    @Override
    public StatusCode delUserRole(String ids) {
        String[] tempIds = ids.split(",");
        int i = 0;
        for (String id : tempIds){
            i += updater.delete(CodAdminUserRoleDo.TABLE_NAME).where("id", id).update();
        }
        if (i == tempIds.length){
            return StatusCode.SUCCESS_CODE;
        }
        return StatusCode.FAIL_CODE;
    }

    /**
     * 删除用户所有角色
     * @param userId 用户id
     * @return
     */
    @Override
    public StatusCode delUserRoleByUserId(String userId) {
        return updater.delete(CodAdminUserRoleDo.TABLE_NAME).where("user_id", userId).update() == 1 ? StatusCode.SUCCESS_CODE : StatusCode.FAIL_CODE;
    }
}
