/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.admin.api;

import com.tlkj.cod.admin.service.CodAdminDatabaseService;
import com.tlkj.cod.model.common.GeneralResponse;
import com.tlkj.cod.model.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Desc 获取数据库信息接口
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminDatabaseApi
 * @date 2019/12/7 2:22 PM
 */
@RestController
@RequestMapping("admin/api/db")
public class CodAdminDatabaseApi extends GeneralResponse {

    @Autowired
    CodAdminDatabaseService codAdminDatabaseService;

    /**
     * 获取数据库信息
     * @param request
     * @return
     */
    @RequestMapping(value = "info", method = RequestMethod.GET)
    public Response getInfo(HttpServletRequest request){
        String name = request.getParameter("name");
        return success(codAdminDatabaseService.getDatabaseInfo(name));
    }
}
