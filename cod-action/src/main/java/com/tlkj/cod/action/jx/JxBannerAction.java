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

import com.tlkj.cod.model.business.jx.dto.JxBannerDto;
import com.tlkj.cod.model.common.GeneralResponse;
import com.tlkj.cod.model.common.Response;
import com.tlkj.cod.model.common.SystemResponse;
import com.tlkj.cod.service.jx.JxBannerService;
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
 * @className JxBannerAction
 * @date 2019/3/24 10:01 PM
 */
@RestController
@RequestMapping("action/jx/banner")
public class JxBannerAction extends GeneralResponse {

    @Autowired
    JxBannerService jxBannerService;

    /**
     * banner列表
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Response list(HttpServletRequest request){
        String page = getParams(request, "page");
        String pageSize = getParams(request, "pageSize");
        SystemResponse response = jxBannerService.list(page, pageSize);
        return response.toResponse();
    }

    /**
     * 获取banner详情
     * @param request
     * @return
     */
    @RequestMapping(value = "get", method = RequestMethod.GET)
    public Response get(HttpServletRequest request){
        String id = getParams(request, "id");
        return jxBannerService.get(id).toResponse();
    }

    /**
     * 删除banner
     * @param request
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Response delete(HttpServletRequest request){
        String ids = getParams(request, "ids");
        return jxBannerService.del(ids).toResponse();
    }

    /**
     * 保存
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Response save(HttpServletRequest request){
        String id = getParams(request, "id");
        String img = getParams(request, "img");
        String url = getParams(request, "url");
        String type = getParams(request, "type");
        String sort = getParams(request, "sort");
        String state = getParams(request, "state");
        return jxBannerService.save(id, img, url, type, sort, state).toResponse();
    }

}
