/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.admin.service;

import com.tlkj.cod.admin.model.dto.CodAdminPermissionItemDto;
import com.tlkj.cod.admin.model.dto.CodAdminPermissionTreeDto;
import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.model.enums.StatusCode;

import java.util.List;

/**
 * Desc 权限 CodAdminPermissionService
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminPermissionService
 * @date 2018/11/7 下午10:32
 */
public interface CodAdminPermissionService {

    /**
     * 根据角色获取权限
     * @param roleIds 角色ids
     * @return 权限列表
     */
    List<CodAdminPermissionTreeDto> getPermissionTree(String roleIds);

    /**
     * 获取权限列表
     * @param page     第几页
     * @param pageSize 每页大小
     * @param name     权限名称
     * @param code     权限代码
     * @param desc     权限描述
     * @return 权限列表
     */
    Page<List<CodAdminPermissionTreeDto>> listPermission(String page, String pageSize, String name, String code, String desc);

    /**
     * 保存权限
     * @param id    id
     * @param name  权限名称
     * @param code  权限代码
     * @param desc  权限描述
     * @param state 状态
     * @param num   权限数
     * @return 是否保存成功
     */
    StatusCode savePermission(String id, String name, String code, String desc, String state, String num);

    /**
     * 删除权限
     * @param id 权限id
     * @return 是否删除成功
     */
    StatusCode delPermission(String id);

    /**
     * 根据id获取权限
     * @return 权限列表
     */
    List<CodAdminPermissionItemDto> getPermission(String id);

    /**
     * 验证权限代码是否重复
     * @return 是否重复
     */
    boolean verifyPermissionCode(String code);
}
