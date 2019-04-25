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

import com.tlkj.cod.core.annotation.ParamNotNull;
import com.tlkj.cod.model.common.GeneralResponse;
import com.tlkj.cod.model.common.Response;
import com.tlkj.cod.service.jx.JxShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Desc 分享action
 *
 * @author sourcod
 * @version 1.0
 * @className JxShareAction
 * @date 2019/3/24 10:02 PM
 */
@RestController
@RequestMapping("action/jx/share")
public class JxShareAction extends GeneralResponse {

    @Autowired
    JxShareService jxShareService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Response list(HttpServletRequest request){
        String userId = getParams(request, "userId");
        String startDate = getParams(request, "startDate");
        String endDate = getParams(request, "endDate");
        String title = getParams(request, "title");
        String author = getParams(request, "author");
        String page = getParams(request, "page");
        String pageSize = getParams(request, "pageSize");
        return jxShareService.list(page, pageSize).toResponse();
    }

    @ParamNotNull(parameter = "id")
    @RequestMapping(value = "get", method = RequestMethod.GET)
    public Response get(HttpServletRequest request){
        String id = getParams(request, "id");
        return jxShareService.get(id).toResponse();
    }

    @ParamNotNull(parameter = "title")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Response save(HttpServletRequest request){
        String id = getParams(request, "id");
        String img = getParams(request, "img");
        String title = getParams(request, "title");
        String intro = getParams(request, "intro");
        String content = getParams(request, "content");
        String previewUrl = getParams(request, "previewUrl");
        String downloadUrl = getParams(request, "downloadUrl");
        String fine = getParams(request, "fine");
        String author = getParams(request, "author");
        String zanNum = getParams(request, "zanNum");
        String commentNum = getParams(request, "commentNum");
        String previewNum = getParams(request, "previewNum");
        String price = getParams(request, "price");
        String top = getParams(request, "top");
        String sort = getParams(request, "sort");
        String recommend = getParams(request, "recommend");
        return jxShareService.save(id, img, title, intro, content, previewUrl, downloadUrl, fine, author, zanNum,
                commentNum, previewNum, price, top, sort, recommend).toResponse();
    }

    /**
     * 删除
     */
    @ParamNotNull(parameter = "ids")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Response del(HttpServletRequest request){
        String ids = getParams(request, "ids");
        return jxShareService.del(ids).toResponse();
    }
}
