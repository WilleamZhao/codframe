/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.api.jx;

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
 * Desc 分享Api
 *
 * @author sourcod
 * @version 1.0
 * @className JxShareApi
 * @date 2019/3/24 4:53 PM
 */
@RestController
@RequestMapping("jx/share")
public class JxShareApi extends GeneralResponse {

    @Autowired
    JxShareService jxShareService;

    /**
     * 获取精享列表
     */
    @RequestMapping(value = "list", method = {RequestMethod.GET})
    public Response list(HttpServletRequest request){
        String page = getParams(request, "page");
        String pageSize = getParams(request, "pageSize");
        return jxShareService.list(page, pageSize).toResponse();
    }

    /**
     * 获取精享详情
     */
    @ParamNotNull(parameter = "id")
    @RequestMapping(value = "get", method = {RequestMethod.GET})
    public Response get(HttpServletRequest request){
        String id = getParams(request, "id");
        return jxShareService.get(id).toResponse();
    }

    /**
     * 预览
     */
    @ParamNotNull(parameter = "id")
    @RequestMapping(value = "preview", method = {RequestMethod.GET})
    public Response preview(HttpServletRequest request){
        String id = getParams(request, "id");
        String userId = getParams(request, "userId");
        return jxShareService.preview(id, userId).toResponse();
    }

    /**
     * 下载
     */
    @ParamNotNull(parameter = "id")
    @RequestMapping(value = "download", method = {RequestMethod.GET})
    public Response download(HttpServletRequest request){
        String id = getParams(request, "id");
        String userId = getParams(request, "userId");
        return jxShareService.download(id, userId).toResponse();
    }

}
