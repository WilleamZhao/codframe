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


import com.tlkj.cod.admin.model.dto.CodAdminPermissionItemDto;
import com.tlkj.cod.admin.model.dto.CodAdminPermissionTreeDto;
import com.tlkj.cod.admin.model.entity.CodAdminPermissionDo;
import com.tlkj.cod.admin.model.entity.CodAdminRolePermissionDo;
import com.tlkj.cod.admin.service.CodAdminPermissionService;
import com.tlkj.cod.core.model.dto.CodCorePermissionItemDto;
import com.tlkj.cod.core.security.util.PermissionUtil;
import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Pagination;
import com.tlkj.cod.dao.jdbc.Updater;
import com.tlkj.cod.model.enums.StatusCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc 权限ServiceImpl
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminPermissionServiceImpl
 * @date 2018/11/7 下午10:32
 */
@Service
public class CodAdminPermissionServiceImpl implements CodAdminPermissionService {

    @Autowired
    Finder finder;

    @Autowired
    Updater updater;

    /**
     * 获取权限树
     */
    @Override
    public List<CodAdminPermissionTreeDto> getPermissionTree(String roleIds) {
        // 查询权限
        Finder.Query query = finder.from(CodAdminPermissionDo.TABLE_NAME);
        if (StringUtils.isNotBlank(roleIds)){
            // 根据角色查询权限ID
            Object[] roles = roleIds.split(",");
            Object[] permissionId = finder.from(CodAdminRolePermissionDo.TABLE_NAME).in("role_id", roles).all(CodAdminRolePermissionDo.class)
                    .stream().map(CodAdminRolePermissionDo::getPermission_id).toArray();
            if (permissionId != null && permissionId.length > 0){
                query.in("id", permissionId);
            } else {
                query.where("1", "-1");
            }
        }

        List<CodAdminPermissionDo> permissionDoList = query.all(CodAdminPermissionDo.class);
        List<CodAdminPermissionTreeDto> dtos = new ArrayList<>();
        permissionDoList.forEach( item -> {
            CodAdminPermissionTreeDto dto = new CodAdminPermissionTreeDto();
            dto.setId(item.getId());
            dto.setCode(item.getPermission_code());
            dto.setName(item.getPermission_name());
            dto.setPermission(PermissionUtil.getPermission(item.getPermission()));
            dtos.add(dto);
        });
        return dtos;
    }

    /**
     * 获取权限列表
     * @param page     第几页
     * @param pageSize 每页大小
     * @param name     权限名称
     * @param code     权限代码
     * @param desc     权限描述
     * @return 权限列表
     */
    @Override
    public Page<List<CodAdminPermissionTreeDto>> listPermission(String page, String pageSize, String name, String code, String desc) {
        Finder.Query query = finder.from(CodAdminPermissionDo.TABLE_NAME);
        if (StringUtils.isNotBlank(name)){
            query.like("permission_name", "%" + name + "%");
        }

        if (StringUtils.isNotBlank(code)) {
            query.like("permission_code", "%" + code + "%");
        }

        if (StringUtils.isNotBlank(desc)) {
            query.like("permission_desc", "%" + desc + "%");
        }

        int currentPage = StringUtils.isNotBlank(page) ? Integer.parseInt(page) : 1;
        int perPage = StringUtils.isNotBlank(pageSize) ? Integer.parseInt(pageSize) : Pagination.DEFAULT_PER_PAGE;
        Pagination<CodAdminPermissionDo> pagination = query.paginate(CodAdminPermissionDo.class, currentPage, perPage);
        List<CodAdminPermissionDo> list = pagination.getData();
        List<CodAdminPermissionTreeDto> dtos = new ArrayList<>();
        list.forEach(item -> {
            CodAdminPermissionTreeDto dto = new CodAdminPermissionTreeDto();
            dto.setId(item.getId());
            dto.setPermission(PermissionUtil.convertPermission(item.getPermission()));
            dto.setName(item.getPermission_name());
            dto.setCode(item.getPermission_code());
            dto.setDesc(item.getPermission_desc());
            dto.setState(String.valueOf(item.getState()));
            dto.setNum(item.getPermission());
            dtos.add(dto);
        });
        return new Page<>(dtos, pagination);
    }

    /**
     * 保存权限
     * @param id   id
     * @param name 权限名称
     * @param code 权限代码
     * @param desc 权限描述
     * @param num  权限数
     * @return 是否保存成功
     */
    @Override
    public StatusCode savePermission(String id, String name, String code, String desc, String state, String num) {
        Updater.Update update = StringUtils.isNotBlank(id) ? updater.update(CodAdminPermissionDo.TABLE_NAME).where("id", id) : updater.insert(CodAdminPermissionDo.TABLE_NAME).setId();

        if (StringUtils.isNotBlank(name)){
            update.set("permission_name", name);
        }

        if (StringUtils.isNotBlank(code)){
            update.set("permission_code", code);
        }

        if (StringUtils.isNotBlank(desc)){
            update.set("permission_desc", desc);
        }

        if (StringUtils.isNotBlank(state)){
            update.set("state", state);
        }

        if (StringUtils.isNotBlank(num)){
            update.set("permission", num);
        }

        int i = update.update();
        if (i == 1){
            return StatusCode.SUCCESS_CODE;
        }
        return StatusCode.FAIL_CODE;
    }

    @Override
    public StatusCode delPermission(String id) {
        return updater.delete(CodAdminPermissionDo.TABLE_NAME).where("id", id).update() == 1 ? StatusCode.SUCCESS_CODE : StatusCode.FAIL_CODE;
    }

    /**
     * 获取全部权限
     * @return 全部权限
     */
    @Override
    public List<CodAdminPermissionItemDto> getPermission(String id) {
        CodAdminPermissionDo codAdminPermissionDo = null;
        if (StringUtils.isNotBlank(id)){
            codAdminPermissionDo = finder.from(CodAdminPermissionDo.TABLE_NAME).where("id", id).first(CodAdminPermissionDo.class);
        }
        List<CodCorePermissionItemDto> dtoList = PermissionUtil.convertPermission(codAdminPermissionDo != null ? codAdminPermissionDo.getPermission() : 0);
        List<CodAdminPermissionItemDto> list = new ArrayList<>();
        for (CodCorePermissionItemDto corePermissionItemDto : dtoList){
            CodAdminPermissionItemDto dto = new CodAdminPermissionItemDto();
            dto.setCode(corePermissionItemDto.getCode());
            dto.setName(corePermissionItemDto.getName());
            dto.setNum(corePermissionItemDto.getNum());
            dto.setState(corePermissionItemDto.isState());
            list.add(dto);
        }
        return list;
    }

    /**
     * 验证权限代码是否重复
     * @param code
     * @return
     */
    @Override
    public boolean verifyPermissionCode(String code) {
        int i = finder.from(CodAdminPermissionDo.TABLE_NAME).where("permission_code", code).select("count(*)").firstForObject(Integer.class);
        if (i == 0){
            return true;
        }
        return false;
    }
}
