/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.admin.action;

import com.tlkj.cod.admin.facade.CodAdminPermissionFacade;
import com.tlkj.cod.admin.service.CodAdminPermissionService;
import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.model.common.GeneralResponse;
import com.tlkj.cod.model.common.Response;
import com.tlkj.cod.model.enums.StatusCode;
import com.tlkj.cod.model.system.dto.CodFramePermissionItemDto;
import com.tlkj.cod.model.system.dto.CodFramePermissionTreeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Desc 权限管理Action
 *
 * @author sourcod
 * @version 1.0
 * @className PermissionAction
 * @date 2018/10/29 下午2:25
 */
@RestController
@RequestMapping("system/permission")
public class CodAdminPermissionAction extends GeneralResponse {

    @Autowired
    CodAdminPermissionService codAdminPermissionService;

    @Autowired
    CodAdminPermissionFacade codAdminPermissionFacade;

    /**
     * 获取权限列表
     */
    @RequestMapping(value = "list", method = {RequestMethod.GET})
    public Response listPermission(HttpServletRequest request){
        String page = request.getParameter("page");
        String pageSize = request.getParameter("pageSize");
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String desc = request.getParameter("desc");
        Page<List<CodFramePermissionTreeDto>> dtoList = codAdminPermissionService.listPermission(page, pageSize, name, code, desc);
        return dtoList == null ? super.fail() : super.success(dtoList);
    }

    /**
     * 获取权限树列表
     */
    @RequestMapping(value = "listTree", method = {RequestMethod.GET})
    public Response getPermission(HttpServletRequest request){
        String roleId = request.getParameter("roleId");
        List<CodFramePermissionTreeDto> dtoList = codAdminPermissionService.getPermissionTree(roleId);
        return dtoList == null ? super.fail() : super.success(dtoList);
    }

    /**
     * 保存权限树
     */
    @RequestMapping(value = "save", method = {RequestMethod.POST})
    public Response savePermission(HttpServletRequest request){
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String desc = request.getParameter("desc");
        String state = request.getParameter("state");
        String num = request.getParameter("num");
        StatusCode statusCode = codAdminPermissionService.savePermission(id, name, code, desc, state, num);
        return super.output(statusCode);
    }

    /**
     * 删除权限
     */
    @RequestMapping(value = "del", method = {RequestMethod.POST})
    public Response delPermission(HttpServletRequest request){
        String id = request.getParameter("id");
        StatusCode statusCode = codAdminPermissionService.delPermission(id);
        return super.output(statusCode);
    }

    /**
     * 获取全部权限
     */
    @RequestMapping(value = "getPermission", method = {RequestMethod.GET})
    public Response getAllPermission(HttpServletRequest request){
        String id = request.getParameter("id");
        List<CodFramePermissionItemDto> dto = codAdminPermissionService.getPermission(id);
        return super.success(dto);
    }

    /**
     * 验证代码是否重复
     */
    @RequestMapping(value = "verifyCode", method = {RequestMethod.GET})
    public Response verifyCode(HttpServletRequest request){
        String code = request.getParameter("code");
        return codAdminPermissionService.verifyPermissionCode(code) ? super.success() : super.fail();
    }
}
