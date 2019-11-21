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

import com.tlkj.cod.admin.service.CodAdminSystemDataSetService;
import com.tlkj.cod.data.model.dto.CodDataConfigDto;
import com.tlkj.cod.model.common.GeneralResponse;
import com.tlkj.cod.model.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    // @Autowired
    // CodAdminSystemSetService codAdminSystemSetService;

    @Autowired
    CodAdminSystemDataSetService codAdminSystemDataSetService;

    /**
     * 设置列表
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Response list(HttpServletRequest request){
        String key = request.getParameter("key");
        String name = request.getParameter("name");
        String page = request.getParameter("page");
        String pageSize = request.getParameter("pageSize");
        List list = codAdminSystemDataSetService.list(key, name, page, pageSize);
        return success(list);
    }

    /**
     * 设置详情
     */
    @RequestMapping(value = "get", method = RequestMethod.GET)
    public Response get(HttpServletRequest request){
        String id = request.getParameter("id");
        CodDataConfigDto codDataConfigDto = codAdminSystemDataSetService.get(id);
        return success(codDataConfigDto);
    }

    /**
     * 保存设置
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Response save(HttpServletRequest request){
        String id = request.getParameter("id");
        String key = request.getParameter("key");
        String value = request.getParameter("value");
        String name = request.getParameter("name");
        String sort = request.getParameter("sort");
        String type = request.getParameter("type");
        boolean isSave = codAdminSystemDataSetService.save(id, key, value, name, sort);
        return success(isSave);
    }

    /**
     * 删除设置
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Response delete(HttpServletRequest request){
        String id = request.getParameter("id");
        String type = request.getParameter("type");
        boolean isSave = codAdminSystemDataSetService.delete(id);
        return success(isSave);
    }
}
