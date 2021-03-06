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

import com.tlkj.cod.admin.facade.CodAdminRoleFacade;
import com.tlkj.cod.admin.model.dto.CodAdminRoleListDto;
import com.tlkj.cod.admin.service.CodAdminRoleService;
import com.tlkj.cod.dao.bean.Page;
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
 * Desc 角色管理Action
 *
 * @author sourcod
 * @version 1.0
 * @className RoleAction
 * @date 2018/10/29 下午2:21
 */
@RestController
@RequestMapping("system/role")
public class CodAdminRoleAction extends GeneralResponse {

    @Autowired
    private CodAdminRoleService codAdminRoleService;

    @Autowired
    CodAdminRoleFacade codAdminRoleFacade;

    /**
     * 保存角色
     */
    @RequestMapping(value = "save", method = {RequestMethod.POST})
    public Response saveRole(HttpServletRequest request){
        String id = request.getParameter("id");
        String roleName = request.getParameter("roleName");
        String roleDesc = request.getParameter("roleDesc");
        String roleRemark = request.getParameter("roleRemark");
        String status = request.getParameter("status");
        String sort = request.getParameter("sort");
        StatusCode statusCode = codAdminRoleService.saveRole(id, roleName, roleDesc, roleRemark, status, sort);
        return StatusCode.verifyStatusCode(statusCode) ? super.success(statusCode) : super.fail(statusCode);
    }

    /**
     * 获取角色列表
     */
    @RequestMapping(value = "list", method = {RequestMethod.GET})
    public Response listRole(HttpServletRequest request){
        String page = request.getParameter("page");
        String pageSize = request.getParameter("pageSize");
        String roleName = request.getParameter("roleName");
        String roleDesc = request.getParameter("roleDesc");
        String status = request.getParameter("status");
        Page<List<CodAdminRoleListDto>> listPage = codAdminRoleService.listRole(page, pageSize, roleName, roleDesc, status);
        if (listPage != null){
            return super.success(listPage);
        }
        return super.fail();
    }

    /**
     * 删除角色
     */
    @RequestMapping(value = "del", method = {RequestMethod.POST})
    public Response delRole(HttpServletRequest request){
        String roleIds = request.getParameter("roleIds");
        StatusCode statusCode = codAdminRoleService.delRole(roleIds);
        return super.output(statusCode);
    }

    /**
     * 获取一条角色
     */
    @RequestMapping(value = "get", method = {RequestMethod.GET})
    public Response getRole(HttpServletRequest request){
        String id = request.getParameter("id");
        return super.success();
    }

    /**
     * 编辑权限
     */
    @RequestMapping(value = "editPermission", method = {RequestMethod.GET})
    public Response treeRole(HttpServletRequest request){
        String roleId = request.getParameter("roleId");
        List list = codAdminRoleFacade.getPermission(roleId);
        return list == null ? super.fail() : super.success(list);
    }

    /**
     * 编辑菜单
     */
    @RequestMapping(value = "editMenu", method = {RequestMethod.GET})
    public Response editMenu(HttpServletRequest request){
        String roleId = request.getParameter("roleId");
        List list = codAdminRoleFacade.editMenu(roleId);
        return list == null ? super.fail() : super.success(list);
    }
}
