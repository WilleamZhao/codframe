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

// import com.tlkj.cod.core.annotation.GetParam;
import com.tlkj.cod.core.annotation.ParamNotNull;
import com.tlkj.cod.model.common.GeneralResponse;
import com.tlkj.cod.model.common.Response;
import com.tlkj.cod.model.common.SystemResponse;
import com.tlkj.cod.service.jx.JxCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Desc 分类action
 *
 * @author sourcod
 * @version 1.0
 * @className JxCategoryApi
 * @date 2019/3/24 6:16 PM
 */
@RestController
@RequestMapping("jx/category")
public class JxCategoryApi extends GeneralResponse {

    @Autowired
    JxCategoryService jxCategoryService;

    /**
     * 获取分类
     */
    @ParamNotNull(parameter = "id")
    @RequestMapping(value = "get", method = RequestMethod.GET)
    public Response get(HttpServletRequest request){
        String id = getParams(request, "id");
        return jxCategoryService.getCategory(id).toResponse();
    }

    /**
     * 保存分类
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Response save(HttpServletRequest request){
        String id = getParams(request, "id");
        String name = getParams(request, "name");
        String pId = getParams(request, "pId");
        String sort = getParams(request, "sort");
        SystemResponse response = jxCategoryService.save(id, name, pId, sort);
        return response.toResponse();
    }

    /**
     * 删除分类
     */
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public Response del(HttpServletRequest request){
        String id = getParams(request, "id");
        String state = getParams(request, "state");
        return jxCategoryService.del(id, state).toResponse();
    }
}
