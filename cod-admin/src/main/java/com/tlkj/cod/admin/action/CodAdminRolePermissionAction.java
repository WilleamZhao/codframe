/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.admin.action;

import com.tlkj.cod.admin.service.CodAdminRolePermissionService;
import com.tlkj.cod.model.common.GeneralResponse;
import com.tlkj.cod.model.common.Response;
import com.tlkj.cod.model.enums.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Desc 角色权限Action
 *
 * @author sourcod
 * @version 1.0
 * @className RolePermissionAction
 * @date 2018/12/14 2:49 PM
 */
@RestController
@RequestMapping("system/role/permission")
public class CodAdminRolePermissionAction extends GeneralResponse {

    @Autowired
    CodAdminRolePermissionService codAdminRolePermissionService;

    /**
     * 获取全部权限和角色权限
     */
    @RequestMapping(value = "get", method = {RequestMethod.GET})
    public Response getPermission(HttpServletRequest request){
        String roleIds = request.getParameter("roleIds");
        List list = codAdminRolePermissionService.getRolePermission(roleIds);
        return list != null ? super.success() : super.fail();
    }

    @RequestMapping(value = "save", method = {RequestMethod.POST})
    public Response save(HttpServletRequest request){
        String id = request.getParameter("id");
        String permissionId = request.getParameter("permissionId");
        StatusCode statusCode = codAdminRolePermissionService.saveRolePermission(id, permissionId);
        return super.output(statusCode);
    }
}
