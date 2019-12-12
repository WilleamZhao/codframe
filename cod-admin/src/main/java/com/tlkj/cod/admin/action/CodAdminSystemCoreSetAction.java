/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.admin.action;

import com.tlkj.cod.admin.facade.CodAdminSystemCoreSetFacade;
import com.tlkj.cod.model.common.GeneralResponse;
import com.tlkj.cod.model.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Desc 系统核心设置
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminSystemCoreSetAction
 * @date 2019/8/22 6:14 PM
 */
@RestController
@RequestMapping("system/data")
public class CodAdminSystemCoreSetAction extends GeneralResponse {

    @Autowired
    CodAdminSystemCoreSetFacade codAdminSystemCoreSetFacade;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Response list(HttpServletRequest request) {
        String key = request.getParameter("codDataKey");
        String name = request.getParameter("codDataName");
        String page = request.getParameter("codDataPage");
        String pageSize = request.getParameter("codDataPageSize");
        codAdminSystemCoreSetFacade.list(key, name, page, pageSize);
        return super.success();
    }
}
