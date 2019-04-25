/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.action.jx;

import com.tlkj.cod.model.common.GeneralResponse;
import com.tlkj.cod.model.common.Response;
import com.tlkj.cod.service.jx.JxAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className JxAdAction
 * @date 2019/3/24 10:02 PM
 */
@RestController
@RequestMapping("action/jx/ad")
public class JxAdAction extends GeneralResponse {

    @Autowired
    JxAdService jxAdService;

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Response save(HttpServletRequest request){
        String id = getParams(request, "id");
        String title = getParams(request, "title");
        String url = getParams(request, "url");
        String startDate = getParams(request, "startDate");
        String endDate = getParams(request, "endDate");
        return jxAdService.save(id, title, url, startDate, endDate).toResponse();
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Response delete(HttpServletRequest request){
        String ids = getParams(request, "ids");
        return jxAdService.delete(ids).toResponse();
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Response list(HttpServletRequest request){
        String page = getParams(request, "page");
        String pageSize = getParams(request, "pageSize");
        String title = getParams(request, "title");
        return jxAdService.list(page, pageSize, title).toResponse();
    }

    @RequestMapping(value = "get", method = RequestMethod.GET)
    public Response get(HttpServletRequest request){
        String id = getParams(request, "id");
        return jxAdService.get(id).toResponse();
    }

}
