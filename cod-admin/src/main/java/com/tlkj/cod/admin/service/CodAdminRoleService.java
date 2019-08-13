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

import com.tlkj.cod.admin.model.dto.CodAdminRoleListDto;
import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.model.enums.StatusCode;

import java.util.List;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminRoleService
 * @date 2018/10/30 下午2:26
 */
public interface CodAdminRoleService {

    /**
     * 角色列表
     * @param page     第几页
     * @param pageSize 分页大小
     * @param roleName 角色名称
     * @param roleDesc 角色描述
     * @param state    状态
     * @return
     */
    Page<List<CodAdminRoleListDto>> listRole(String page, String pageSize, String roleName, String roleDesc, String state);

    /**
     * 保存角色
     * @param id         主键
     * @param roleName   角色名
     * @param roleDesc   角色描述
     * @param roleRemark 角色备注
     * @param state     状态 -1: 删除; 0: 禁用; 1: 启用;
     * @param sort       排序
     * @return
     */
    StatusCode saveRole(String id, String roleName, String roleDesc, String roleRemark, String state, String sort);

    /**
     * 删除角色
     * @param id 主键
     * @return
     */
    StatusCode delRole(String id);


}
