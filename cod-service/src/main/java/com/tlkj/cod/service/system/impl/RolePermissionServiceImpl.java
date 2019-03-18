/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.service.system.impl;

import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Updater;
import com.tlkj.cod.model.enums.StatusCode;
import com.tlkj.cod.model.system.entity.CodFrameRolePermissionDo;
import com.tlkj.cod.service.system.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Desc 角色权限ServiceImpl
 *
 * @author sourcod
 * @version 1.0
 * @className RolePermissionServiceImpl
 * @date 2018/12/14 3:11 PM
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    Finder finder;

    @Autowired
    Updater updater;

    /**
     * 获取角色权限
     * @param roleIds 角色id
     * @return
     */
    @Override
    public List getRolePermission(String roleIds) {
        String[] roleIdss = roleIds.split(",");
        List<CodFrameRolePermissionDo> codFrameRolePermissionDo = finder.from(CodFrameRolePermissionDo.TABLE_NAME).in("role_id", roleIdss).all(CodFrameRolePermissionDo.class);
        return codFrameRolePermissionDo;
    }

    /**
     * 保存角色权限
     * @param roleId        角色id
     * @param permissionIds 权限id
     * @return 是否保存成功
     */
    @Override
    public StatusCode saveRolePermission(String roleId, String permissionIds) {
        String[] permissionId = permissionIds.split(",");
        // 1. 删除该角色下所有权限
        updater.delete(CodFrameRolePermissionDo.TABLE_NAME).where("role_id", roleId).update();

        // 2. 新增权限
        int i = 0;
        for (String perId : permissionId){
            int num = finder.from(CodFrameRolePermissionDo.TABLE_NAME).where("role_id", roleId).where("permission_id", perId).select("count(*)").firstForObject(Integer.class);
            if (num == 0){
                i += updater.insert(CodFrameRolePermissionDo.TABLE_NAME).setId().set("role_id", roleId).set("permission_id", perId).update();
            }
        }
        if (i <= permissionId.length){
            return StatusCode.SUCCESS_CODE;
        }
        return StatusCode.FAIL_CODE;
    }
}
