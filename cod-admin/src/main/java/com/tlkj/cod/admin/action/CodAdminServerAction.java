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


}
