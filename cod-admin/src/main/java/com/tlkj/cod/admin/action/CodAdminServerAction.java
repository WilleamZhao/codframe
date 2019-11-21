/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.admin.action;

import com.tlkj.cod.model.common.GeneralResponse;
import com.tlkj.cod.model.common.Response;
import com.tlkj.cod.server.facade.CodServerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Desc 服务管理 action
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminServerAction
 * @date 2019/8/23 12:36 PM
 */
@RestController
@RequestMapping("admin/server")
public class CodAdminServerAction extends GeneralResponse {

    private CodServerFacade codServerFacade;

    @Autowired
    @Lazy
    public CodAdminServerAction(CodServerFacade codServerFacade) {
        this.codServerFacade = codServerFacade;
    }

    /**
     * 服务列表
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Response list(HttpServletRequest request) {
        String name = request.getParameter("name");
        return super.success();
    }

    /**
     * 修改服务
     */
    @RequestMapping(value = "update", method = {RequestMethod.POST})
    public Response update(HttpServletRequest request) {
        String name = request.getParameter("name");
        return super.success();
    }

    /**
     * 停止服务
     * @param request
     * @return
     */
    @RequestMapping(value = "stop", method = {RequestMethod.GET})
    public Response stop(HttpServletRequest request){
        String name = request.getParameter("name");
        codServerFacade.stop();
        return super.success();
    }

    /**
     * 重启服务
     * @param request
     * @return
     */
    @RequestMapping(value = "restart", method = {RequestMethod.GET})
    public Response restart(HttpServletRequest request){
        String name = request.getParameter("name");
        codServerFacade.restart();
        return super.success();
    }


}
