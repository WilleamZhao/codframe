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

import com.tlkj.cod.admin.facade.CodAdminMenuFacade;
import com.tlkj.cod.admin.model.dto.CodAdminMenuDto;
import com.tlkj.cod.admin.model.dto.CodAdminMenuListDto;
import com.tlkj.cod.admin.service.CodAdminMenuService;
import com.tlkj.cod.core.annotation.CodParamVerify;
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
 * Desc 菜单管理
 *
 * @author sourcod
 * @version 1.0
 * @className MenuAction
 * @date 2018/10/29 下午2:08
 */
@RestController
@RequestMapping("system/menu")
public class CodAdminMenuAction extends GeneralResponse {

    @Autowired
    private CodAdminMenuService codAdminMenuService;

    @Autowired
    private CodAdminMenuFacade codAdminMenuFacade;

    /**
     * 获取左侧菜单接口
     */
    @RequestMapping(value = "getMenu", method = {RequestMethod.GET, RequestMethod.OPTIONS})
    public Response getMenu(HttpServletRequest request){
        String token = request.getParameter("cod_admin_token");
        List<CodAdminMenuDto> menuDtoList = codAdminMenuFacade.getMenu(token);
        return menuDtoList == null ? super.fail(StatusCode.LOGIN_FAIL_CODE) : menuDtoList.size() == 0 ? super.fail() : super.success(menuDtoList);
    }

    /**
     * 保存菜单接口
     */
    @CodParamVerify(parameter = "menuName, menuTitle, level")
    @RequestMapping(value = "save", method = {RequestMethod.POST})
    public Response saveMenu(HttpServletRequest request){
        String menuId = request.getParameter("menuId");
        String menuName = request.getParameter("menuName");
        String menuTitle = request.getParameter("menuTitle");
        String jump = request.getParameter("jump");
        String pId = request.getParameter("pId");
        String level = request.getParameter("level");
        String icon = request.getParameter("icon");
        String status = request.getParameter("status");
        String sort = request.getParameter("sort");
        StatusCode statusCode = codAdminMenuService.saveMenu(menuId, menuName, menuTitle, jump, pId, level, icon, status, sort);
        return super.success(statusCode);
    }

    /**
     * 删除菜单
     */
    @RequestMapping(value = "del", method = {RequestMethod.POST})
    public Response delMenu(HttpServletRequest request){
        String menuIds = request.getParameter("menuIds");
        StatusCode statusCode = codAdminMenuService.delMenu(menuIds);
        return super.success(statusCode);
    }

    @RequestMapping(value = "verifyMenuName", method = {RequestMethod.GET})
    public Response verifyMenuName(HttpServletRequest request){
        String menuName = request.getParameter("menuName");
        StatusCode statusCode = codAdminMenuService.verifyMenuName(menuName);
        return super.success(statusCode);
    }

    /**
     * 获取菜单列表接口
     */
    @RequestMapping(value = "list", method = {RequestMethod.GET})
    public Response listMenu(HttpServletRequest request){
        String menuName = request.getParameter("menuName");
        String menuTitle = request.getParameter("menuTitle");
        String level = request.getParameter("level");
        String status = request.getParameter("status");
        String limit = request.getParameter("limit");
        String page = request.getParameter("page");
        Page tempPage = codAdminMenuService.listMenu(page, limit, menuName, menuTitle, level, status);
        if (Page.isData(tempPage)){
            return super.success(tempPage);
        }
        return super.fail(StatusCode.DATA_NULL_CODE);
    }

    /**
     * 获取一条菜单数据
     */
    @CodParamVerify(parameter = "menuId")
    @RequestMapping(value = "getOneMenu", method = {RequestMethod.GET, RequestMethod.OPTIONS})
    public Response getOneMenu(HttpServletRequest request){
        String menuId = request.getParameter("menuId");
        CodAdminMenuListDto menuDtoList = codAdminMenuService.getOneMenu(menuId);
        return menuDtoList == null ? super.fail() : super.success(menuDtoList);
    }
}
