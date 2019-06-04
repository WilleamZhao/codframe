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

import com.tlkj.cod.admin.facade.MenuFacade;
import com.tlkj.cod.admin.service.MenuService;
import com.tlkj.cod.core.annotation.ParamNotNull;
import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.model.common.GeneralResponse;
import com.tlkj.cod.model.common.Response;
import com.tlkj.cod.model.enums.StatusCode;
import com.tlkj.cod.model.system.dto.CodFrameMenuDto;
import com.tlkj.cod.model.system.dto.CodFrameMenuListDto;
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
public class MenuAction extends GeneralResponse {

    @Autowired
    private MenuService menuService;

    @Autowired
    private MenuFacade menuFacade;

    /**
     * 获取左侧菜单接口
     */
    @RequestMapping(value = "getMenu", method = {RequestMethod.GET, RequestMethod.OPTIONS})
    public Response getMenu(HttpServletRequest request){
        String token = request.getParameter("access_token");
        List<CodFrameMenuDto> menuDtoList = menuFacade.getMenu(token);
        return menuDtoList == null ? super.fail(StatusCode.LOGIN_FAIL_CODE) : menuDtoList.size() == 0 ? super.fail() : super.success(menuDtoList);
    }

    /**
     * 保存菜单接口
     */
    @ParamNotNull(parameter = "menuName, menuTitle, level")
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
        StatusCode statusCode = menuService.saveMenu(menuId, menuName, menuTitle, jump, pId, level, icon, status, sort);
        return super.success(statusCode);
    }

    /**
     * 删除菜单
     */
    @RequestMapping(value = "del", method = {RequestMethod.POST})
    public Response delMenu(HttpServletRequest request){
        String menuIds = request.getParameter("menuIds");
        StatusCode statusCode = menuService.delMenu(menuIds);
        return super.success(statusCode);
    }

    @RequestMapping(value = "verifyMenuName", method = {RequestMethod.GET})
    public Response verifyMenuName(HttpServletRequest request){
        String menuName = request.getParameter("menuName");
        StatusCode statusCode = menuService.verifyMenuName(menuName);
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
        Page tempPage = menuService.listMenu(page, limit, menuName, menuTitle, level, status);
        if (Page.isData(tempPage)){
            return super.success(tempPage);
        }
        return super.fail();
    }

    /**
     * 获取一条菜单数据
     */
    @ParamNotNull(parameter = "menuId")
    @RequestMapping(value = "getOneMenu", method = {RequestMethod.GET, RequestMethod.OPTIONS})
    public Response getOneMenu(HttpServletRequest request){
        String menuId = request.getParameter("menuId");
        CodFrameMenuListDto menuDtoList = menuService.getOneMenu(menuId);
        return menuDtoList == null ? super.fail() : super.success(menuDtoList);
    }
}
