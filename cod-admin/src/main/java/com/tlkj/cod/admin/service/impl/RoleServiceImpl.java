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

import com.tlkj.cod.admin.service.RoleService;
import com.tlkj.cod.admin.service.SystemSetService;
import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Pagination;
import com.tlkj.cod.dao.jdbc.Updater;
import com.tlkj.cod.log.annotation.Log;
import com.tlkj.cod.log.service.LogService;
import com.tlkj.cod.model.enums.StatusCode;
import com.tlkj.cod.model.system.dto.CodFrameRoleListDto;
import com.tlkj.cod.model.system.entity.CodFrameRoleDo;
import com.tlkj.cod.model.system.entity.CodFrameRoleMenuDo;
import com.tlkj.cod.model.system.entity.CodFrameUserRoleDo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc 角色管理Service
 *
 * @author sourcod
 * @version 1.0
 * @className RoleServiceImpl
 * @date 2018/10/30 下午7:49
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    Finder finder;

    @Autowired
    Updater updater;

    @Autowired
    SystemSetService service;

    @Autowired
    LogService logService;

    /**
     * 获取角色列表
     * @param page     第几页
     * @param pageSize 分页大小
     * @param roleName 角色名称
     * @param roleDesc 角色描述
     * @param state    状态
     * @return
     */
    @Log(name = "获取角色列表")
    @Override
    public Page<List<CodFrameRoleListDto>> listRole(String page, String pageSize, String roleName, String roleDesc, String state) {
        Finder.Query query = finder.from(CodFrameRoleDo.TABLE_NAME);
        if (StringUtils.isNotBlank(roleName)){
            query.like("role_name", "%" + roleName + "%");
        }

        if (StringUtils.isNotBlank(roleDesc)){
            query.like("role_desc", "%" + roleDesc + "%");
        }

        if (StringUtils.isNotBlank(state)){
            query.where("state", state);
        }

        int currentPage = StringUtils.isNotBlank(page) ? Integer.parseInt(page) : 1;
        int perPage = StringUtils.isNotBlank(pageSize) ? Integer.parseInt(pageSize) : Pagination.DEFAULT_PER_PAGE;
        Pagination<CodFrameRoleDo> pagination = query.paginate(CodFrameRoleDo.class, currentPage, perPage);
        List<CodFrameRoleDo> data = pagination.getData();
        if (pagination.getData() == null){
            return null;
        }

        List<CodFrameRoleListDto> listDtos = new ArrayList<>();
        data.forEach(item -> {
            CodFrameRoleListDto dto = new CodFrameRoleListDto();
            dto.setId(item.getId());
            dto.setRoleName(item.getRole_name());
            dto.setRoleDesc(item.getRole_desc());
            dto.setRoleRemark(item.getRole_remark());
            dto.setStatus(item.getState());
            dto.setSort(item.getSort());
            dto.setCreateTime(item.getCreate_time());
            dto.setUpdateTime(item.getUpdate_time());
            listDtos.add(dto);
        });
        return new Page<>(listDtos, pagination);
    }

    /**
     * 保存角色
     * @param id         主键
     * @param roleName   角色名
     * @param roleDesc   角色描述
     * @param roleRemark 角色备注
     * @param state      状态 -1: 删除; 0: 禁用; 1: 启用;
     * @param sort       排序
     */
    @Override
    public StatusCode saveRole(String id, String roleName, String roleDesc, String roleRemark, String state, String sort) {
        Updater.Update update = StringUtils.isNotBlank(id) ? updater.update(CodFrameRoleDo.TABLE_NAME).where("id", id) : updater.insert(CodFrameRoleDo.TABLE_NAME).setId();
        if (StringUtils.isNotBlank(roleName)){
            update.set("role_name", roleName);
        }

        if (StringUtils.isNotBlank(roleDesc)){
            update.set("role_desc", roleDesc);
        }

        if (StringUtils.isNotBlank(roleRemark)){
            update.set("role_remark", roleRemark);
        }

        if (StringUtils.isNotBlank(state)){
            update.set("state", state);
        }

        if (StringUtils.isNotBlank(sort)){
            update.set("sort", sort);
        }

        if (update.update() == 1){
            return StatusCode.SUCCESS_CODE;
        }
        return StatusCode.FAIL_CODE;
    }

    /**
     * 删除角色
     * @param id 主键
     * @return
     */
    @Override
    public StatusCode delRole(String id) {
        String[] roles = id.split(",");
        int roleNum = 0;
        int userRoleNum = 0;
        int roleMenuNum = 0;
        for (String roleId : roles) {
            roleNum += updater.delete(CodFrameRoleDo.TABLE_NAME).where("id", roleId).update();
            userRoleNum += updater.delete(CodFrameUserRoleDo.TABLE_NAME).where("role_id", roleId).update();
            roleMenuNum += updater.delete(CodFrameRoleMenuDo.TABLE_NAME).where("role_id", roleId).update();
        }
        // updater.delete(CodFrameRe.TABLE_NAME).where("", id);
        if (roleNum >= 1){
            logService.info("删除管理用户数={}", userRoleNum);
            logService.info("删除角色菜单数={}", roleMenuNum);
            return StatusCode.SUCCESS_CODE;
        }
        return StatusCode.FAIL_CODE;
    }
}
