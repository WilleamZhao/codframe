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

import com.tlkj.cod.model.common.GeneralResponse;
import com.tlkj.cod.model.common.Response;
import com.tlkj.cod.service.jx.JxTagService;
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
 * @className JxTagApi
 * @date 2019/3/24 6:18 PM
 */
@RestController
@RequestMapping("jx/tag")
public class JxTagApi extends GeneralResponse {

    @Autowired
    JxTagService jxTagService;

    /**
     * 获取分类
     */
    @RequestMapping(value = "get", method = RequestMethod.GET)
    public Response get(HttpServletRequest request){
        String id = getParams(request, "id");
        return jxTagService.list(id).toResponse();
    }
}
