/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.admin.action;

import com.tlkj.cod.admin.service.CodAdminServerService;
import com.tlkj.cod.model.common.GeneralResponse;
import com.tlkj.cod.model.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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

    private CodAdminServerService codAdminServerService;

    @Autowired
    public CodAdminServerAction(CodAdminServerService codAdminServerService) {
        this.codAdminServerService = codAdminServerService;
    }

    /**
     * 停止服务
     * @param request
     * @return
     */
    @RequestMapping(value = "stop", method = {RequestMethod.GET})
    public Response stop(HttpServletRequest request){
        String name = request.getParameter("name");

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
        return super.success();
    }

    /**
     * 注册服务
     * @param request
     * @return
     */
    @RequestMapping(value = "register", method = {RequestMethod.GET, RequestMethod.POST})
    public Response register(HttpServletRequest request){
        String projectName = request.getParameter("projectName");
        String serverName = request.getParameter("serverName");
        String serverIp = request.getParameter("serverIp");
        String serverIntranetIp = request.getParameter("serverIntranetIp");
        String serverPort = request.getParameter("serverPort");
        String serverVersion = request.getParameter("serverVersion");
        String serverConfig = request.getParameter("serverConfig");
        String serverTag = request.getParameter("serverTag");
        String remark = request.getParameter("remark");
        boolean isOk = codAdminServerService.register(projectName, serverName, serverIp, serverIntranetIp, serverPort, serverVersion, serverConfig, serverTag, remark);
        return isOk ? super.success() : super.fail();
    }

    /**
     * 检查服务
     * @param request
     * @return
     */
    @RequestMapping(value = "check", method = {RequestMethod.GET})
    public Response check(HttpServletRequest request){
        String serverId = request.getParameter("id");
        codAdminServerService.check(serverId);
        return super.success();
    }

    /**
     * 自动检查服务
     * @return
     */
    @Scheduled(cron = "0 1 * * * ?")
    public void autoCheck(){
        codAdminServerService.autoCheck();
    }

    /**
     * 服务列表
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Response list(HttpServletRequest request) {
        String projectName = request.getParameter("projectName");

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


}
