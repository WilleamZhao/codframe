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

import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.model.common.Response;
import com.tlkj.cod.model.system.dto.CodFrameSystemLogDto;
import com.tlkj.cod.service.system.SystemLogService;
import com.tlkj.cod.model.common.GeneralResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Desc 日志管理Action
 *
 * @author sourcod
 * @version 1.0
 * @className LogAction
 * @date 2018/10/29 下午2:26
 */
@RestController
@RequestMapping("system/log")
public class LogAction extends GeneralResponse {

    @Autowired
    SystemLogService systemLogService;

    /**
     * 查询日志
     */
    @RequestMapping("list")
    public Response listLog(HttpServletRequest request){
        String ip = request.getParameter("ip");
        String username = request.getParameter("username");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String page = request.getParameter("page");
        String pageSize = request.getParameter("limit");
        Page<List<CodFrameSystemLogDto>> listPage = systemLogService.listLog(ip, username, startDate, endDate, page, pageSize);
        if (listPage != null){
            return super.success(listPage);
        }
        return super.fail();
    }

    /**
     * 查询日志详情
     */
    @RequestMapping("get")
    public Response getLog(HttpServletRequest request){
        String ip = request.getParameter("id");
        CodFrameSystemLogDto listPage = systemLogService.getLog(ip);
        if (listPage == null){
            return super.fail();
        }
        return super.success();
    }
}
