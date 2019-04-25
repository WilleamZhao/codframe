/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.action.system;

import com.tlkj.cod.facade.system.UserFacade;
import com.tlkj.cod.model.enums.StatusCode;
import com.tlkj.cod.model.system.dto.CodFrameUserDto;
import com.tlkj.cod.service.system.UserRoleService;
import com.tlkj.cod.model.common.GeneralResponse;
import com.tlkj.cod.log.annotation.Log;
import com.tlkj.cod.core.annotation.ParamNotNull;
import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.model.common.Response;
import com.tlkj.cod.service.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Desc 用户管理
 *
 * @author sourcod
 * @version 1.0
 * @className UserAction
 * @date 2018/10/29 上午11:17
 */
@RestController
@RequestMapping("system/user")
public class UserAction extends GeneralResponse {

    @Autowired
    private UserService userService;

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 从缓存中获取用户信息
     */
    @RequestMapping(value = "getUser", method = {RequestMethod.GET})
    public Response getUserByCache(HttpServletRequest request){
        String token = request.getParameter("access_token");
        CodFrameUserDto dto = userService.getUserByCache(token);
        return dto == null ? super.fail(StatusCode.LOGIN_FAIL_CODE) : super.success(dto);
    }

    /**
     * 获取用户信息
     */
    @ParamNotNull(parameter = "id")
    @RequestMapping(value = "get", method = {RequestMethod.GET})
    public Response getUser(HttpServletRequest request){
        String id = request.getParameter("id");
        CodFrameUserDto userDto = userService.getUser(id);
        return super.success(userDto);
    }

    /**
     * 查询所有用户信息
     */
    @RequestMapping(value = "list", method = {RequestMethod.GET})
    @Log
    @ParamNotNull(parameter = "page, limit")
    public Response listUser(HttpServletRequest request){
        String page = request.getParameter("page");
        String pageSize = request.getParameter("limit");
        String userName = request.getParameter("userName");
        String loginAccount = request.getParameter("loginAccount");
        String userPhone = request.getParameter("userPhone");
        String userSex = request.getParameter("userSex");
        String state = request.getParameter("state");
        Page<List<CodFrameUserDto>> listPage = userService.listUser(page, pageSize, userName, loginAccount, userPhone, userSex, state);
        return super.success(listPage);
    }

    /**
     * 保存用户
     */
    @RequestMapping(value = "save", method = {RequestMethod.POST})
    @ParamNotNull(parameter = "username")
    public Response saveUser(HttpServletRequest request){
        String id = request.getParameter("id");
        String userName = request.getParameter("username");
        String loginAccount = request.getParameter("loginAccount");
        String userHead = request.getParameter("userHead");
        String userPhone = request.getParameter("userPhone");
        String userEmail = request.getParameter("userEmail");
        String userSex = request.getParameter("userSex");
        String state = request.getParameter("state");
        StatusCode statusCode = userService.saveUser(id, userName, loginAccount, userHead, userPhone, userEmail, userSex, state);
        return super.output(statusCode);
    }

    /**
     * 给用户添加角色
     */
    @RequestMapping(value = "addRole", method = {RequestMethod.POST})
    public Response addRole(HttpServletRequest request){
        String userId = request.getParameter("id");
        String roleIds = request.getParameter("roleIds");
        StatusCode statusCode = userRoleService.addUserRole(userId, roleIds);
        return super.output(statusCode);
    }

    /**
     * 获取角色和已存在角色
     */
    @RequestMapping(value = "editRole", method = {RequestMethod.GET})
    public Response listRoleAlready(HttpServletRequest request){
        String id = request.getParameter("userId");
        List list = userFacade.getRole(id);
        return list == null ? super.fail() : super.success(list);
    }

    /**
     * 获取部门和已存在部门
     */
    @RequestMapping(value = "editDept", method = {RequestMethod.GET})
    public Response editDept(HttpServletRequest request){
        String id = request.getParameter("userId");
        List list = userFacade.editDept(id);
        return list == null ? super.fail() : super.success(list);
    }


}
