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

import com.tlkj.cod.log.service.LogService;
import com.tlkj.cod.model.common.Response;
import com.tlkj.cod.model.system.core.SystemModel;
import com.tlkj.cod.service.system.SystemSetService;
import com.tlkj.cod.model.common.GeneralResponse;
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
public class TestAction extends GeneralResponse {

    @Autowired
    @Qualifier("clogImpl")
    private LogService logService;

    @Autowired
    SystemSetService systemSetService;

    /**
     * 测试日志
     * @return
     */
    @RequestMapping(value = "log", method = {RequestMethod.GET})
    public Response testLog(HttpServletRequest request){
        System.out.println("支持日志级别 = " + SystemModel.getInstance().getLog().getLevel());
        systemSetService.getLog().trace("trace");
        systemSetService.getLog().debug("debug");
        systemSetService.getLog().info("info");
        systemSetService.getLog().warn("warn");
        systemSetService.getLog().error("error");
        logService.trace("trace");
        logService.debug("debug");
        logService.info("info");
        logService.warn("warn");
        logService.error("error");
        return super.success();
    }
}
