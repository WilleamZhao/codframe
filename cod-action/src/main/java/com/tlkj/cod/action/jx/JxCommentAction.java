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
import com.tlkj.cod.service.jx.JxCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Desc 评论action
 *
 * @author sourcod
 * @version 1.0
 * @className JxCommentAction
 * @date 2019/3/25 6:58 PM
 */
@RestController
@RequestMapping("action/jx/comment")
public class JxCommentAction extends GeneralResponse {

    @Autowired
    JxCommentService jxCommentService;

    /**
     * 审核
     */
    @ParamNotNull(parameter = "id")
    @RequestMapping(value = "audit", method = RequestMethod.POST)
    public Response audit(HttpServletRequest request){
        String id = getParams(request, "id");
        String state = getParams(request, "state");
        return jxCommentService.audit(id, state).toResponse();
    }

    /**
     * 保存评论
     * @param request
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Response save(HttpServletRequest request){
        String id = getParams(request, "id");
        String content = getParams(request, "content");
        String userId = getParams(request, "userId");
        String pId = getParams(request, "pId");
        return jxCommentService.save(id, content, userId, pId).toResponse();
    }

    /**
     * 获取评论详情
     */
    @ParamNotNull(parameter = "id")
    @RequestMapping(value = "get", method = RequestMethod.GET)
    public Response get(HttpServletRequest request){
        String id = getParams(request, "id");
        return jxCommentService.get(id).toResponse();
    }

    /**
     * 获取评论列表
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Response list(HttpServletRequest request){
        String page = getParams(request, "page");
        String pageSize = getParams(request, "pageSize");
        String username = getParams(request, "username");
        String content = getParams(request, "content");
        String startDate = getParams(request, "startDate");
        String endDate = getParams(request, "endDate");
        return jxCommentService.list(page, pageSize, username, content, startDate, endDate).toResponse();
    }

    @ParamNotNull(parameter = "ids")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Response del(HttpServletRequest request){
        String ids = getParams(request, "id");
        return jxCommentService.del(ids).toResponse();
    }
}
