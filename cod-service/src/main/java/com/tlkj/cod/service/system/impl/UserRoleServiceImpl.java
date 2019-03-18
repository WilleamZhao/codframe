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
import com.tlkj.cod.model.system.dto.CodFrameRoleListDto;
import com.tlkj.cod.model.system.entity.CodFrameRoleDo;
import com.tlkj.cod.model.system.entity.CodFrameUserRoleDo;
import com.tlkj.cod.service.system.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Desc 用户角色
 *
 * @author sourcod
 * @version 1.0
 * @className UserRoleServiceImpl
 * @date 2018/12/9 7:50 PM
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

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
        String roleIds = finder.from(CodFrameUserRoleDo.TABLE_NAME).where("user_id", userId).all(CodFrameUserRoleDo.class).stream()
                .map(CodFrameUserRoleDo::getRole_id).collect(Collectors.joining(","));
        return roleIds;
    }

    /**
     * 获取用户角色
     * @param userId 用户id
     * @return
     */
    @Override
    public List<CodFrameRoleListDto> listRole(String userId) {
        Finder.Query query = finder.from(CodFrameUserRoleDo.TABLE_NAME + " userRole").join(" join " + CodFrameRoleDo.TABLE_NAME + " role on userRole.role_id = userRole.user_id");
        List<CodFrameRoleDo> dos = query.where("user_id", userId).all(CodFrameRoleDo.class);
        List<CodFrameRoleListDto> dtos = new ArrayList<>();
        dos.forEach(item -> {
            CodFrameRoleListDto dto = new CodFrameRoleListDto();
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
        updater.delete(CodFrameUserRoleDo.TABLE_NAME).where("user_id", userId).update();
        // 2. 新增角色
        int i = 0;
        for (String roleId : roles){
            i += updater.insert(CodFrameUserRoleDo.TABLE_NAME).setId().set("user_id", userId).set("role_id", roleId).update();
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
            i += updater.delete(CodFrameUserRoleDo.TABLE_NAME).where("id", id).update();
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
        return updater.delete(CodFrameUserRoleDo.TABLE_NAME).where("user_id", userId).update() == 1 ? StatusCode.SUCCESS_CODE : StatusCode.FAIL_CODE;
    }
}
