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

import com.tlkj.cod.core.annotation.ParamNotNull;
import com.tlkj.cod.facade.system.RoleMenuFacade;
import com.tlkj.cod.model.common.Response;
import com.tlkj.cod.model.enums.StatusCode;
import com.tlkj.cod.service.system.RoleMenuService;
import com.tlkj.cod.model.common.GeneralResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Desc 角色菜单Action
 *
 * @author sourcod
 * @version 1.0
 * @className RoleMenuAction
 * @date 2018/12/26 1:15 PM
 */
@RestController
@RequestMapping("system/role/menu")
public class RoleMenuAction extends GeneralResponse {

    @Autowired
    private RoleMenuFacade roleMenuFacade;

    @Autowired
    RoleMenuService roleMenuService;

    /**
     * 添加角色菜单表
     */
    @RequestMapping(value = "save", method = {RequestMethod.POST})
    public Response add(HttpServletRequest request){
        // 角色id
        String roleId = request.getParameter("roleId");

        // 菜单id
        String menuId = request.getParameter("menuIds");
        StatusCode statusCode = roleMenuService.save(roleId, menuId);
        return output(statusCode);
    }

    /**
     * 维护菜单
     */
    @RequestMapping(value = "listAuthorization", method = {RequestMethod.GET})
    public Response listAuthorization(HttpServletRequest request){
        String roleId = request.getParameter("roleId");
        List list = roleMenuFacade.listAuthorization(roleId);
        return list != null ? success(list) : fail();
    }
}
