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

import com.tlkj.cod.admin.service.CodAdminSystemSetService;
import com.tlkj.cod.log.service.CodLogService;
import com.tlkj.cod.model.common.GeneralResponse;
import com.tlkj.cod.model.common.Response;
import com.tlkj.cod.model.system.core.SystemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Desc 日志测试类
 *
 * @author sourcod
 * @version 1.0
 * @className TestAction
 * @date 2018/12/25 1:05 PM
 */
@RestController
@RequestMapping("test")
public class CodAdminTestAction extends GeneralResponse {

    @Autowired
    @Qualifier("clogImpl")
    private CodLogService codLogService;

    @Autowired
    CodAdminSystemSetService codAdminSystemSetService;

    /**
     * 测试日志
     * @return
     */
    @RequestMapping(value = "log", method = {RequestMethod.GET})
    public Response testLog(HttpServletRequest request){
        System.out.println("支持日志级别 = " + SystemModel.getInstance().getLog().getLevel());
        codAdminSystemSetService.getLog().trace("trace");
        codAdminSystemSetService.getLog().debug("debug");
        codAdminSystemSetService.getLog().info("info");
        codAdminSystemSetService.getLog().warn("warn");
        codAdminSystemSetService.getLog().error("error");
        codLogService.trace("trace");
        codLogService.debug("debug");
        codLogService.info("info");
        codLogService.warn("warn");
        codLogService.error("error");
        return super.success();
    }
}
