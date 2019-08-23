/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.admin.action;

import com.tlkj.cod.model.common.GeneralResponse;
import com.tlkj.cod.model.common.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Desc 系统设置Action
 *
 * @author sourcod
 * @version 1.0
 * @className SystemSetAction
 * @date 2019/3/12 10:13 PM
 */
@RestController
@RequestMapping("system/set")
public class CodAdminSystemSetAction extends GeneralResponse {

    // TODO

    /**
     * 设置列表
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Response list(HttpServletRequest request){
        String key = request.getParameter("key");

        return success();
    }
}
