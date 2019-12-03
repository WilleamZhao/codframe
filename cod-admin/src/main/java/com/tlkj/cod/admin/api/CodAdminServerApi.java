/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.admin.api;

import com.tlkj.cod.admin.model.bo.CodAdminServerLicenseBo;
import com.tlkj.cod.admin.service.CodAdminServerService;
import com.tlkj.cod.model.common.GeneralResponse;
import com.tlkj.cod.model.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Desc 服务接口
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminServerApi
 * @date 2019/12/3 8:52 PM
 */
@RestController
@RequestMapping("admin/api/server")
public class CodAdminServerApi extends GeneralResponse {

    @Autowired
    CodAdminServerService codAdminServerService;

    /**
     * 获取前端url
     */
    @RequestMapping(value = "getLicense", method = {RequestMethod.GET})
    public Response getLicense(HttpServletRequest request){
        String projectName = request.getParameter("projectName");
        String serverId = request.getParameter("serverId");
        CodAdminServerLicenseBo bo = codAdminServerService.getLicense(projectName, serverId);
        return bo != null ? success(bo) : fail();
    }
}
