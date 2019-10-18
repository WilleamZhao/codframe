/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.admin.action;

import com.tlkj.cod.admin.service.CodAdminApiService;
import com.tlkj.cod.model.common.GeneralResponse;
import com.tlkj.cod.model.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Desc 接口管理
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminApiAction
 * @date 2019/10/10 11:07 AM
 */
@RestController
@RequestMapping("system/api")
public class CodAdminApiAction extends GeneralResponse {

    @Autowired
    CodAdminApiService codAdminApiService;

    /**
     * 接口列表
     * @param request
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Response list(HttpServletRequest request){
        String page = getParams(request, "page");
        String pageSize = getParams(request, "pageSize");

        return success();
    }
}
