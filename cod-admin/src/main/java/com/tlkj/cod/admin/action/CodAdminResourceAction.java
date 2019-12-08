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

import com.tlkj.cod.admin.model.dto.CodAdminPermissionListDto;
import com.tlkj.cod.admin.service.CodAdminResourceService;
import com.tlkj.cod.model.common.GeneralResponse;
import com.tlkj.cod.model.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Desc 资源管理Action
 *
 * @author sourcod
 * @version 1.0
 * @className ResourceAction
 * @date 2018/10/29 下午2:25
 */
@RestController
@RequestMapping("system/resource")
public class CodAdminResourceAction extends GeneralResponse {

    @Autowired
    CodAdminResourceService codAdminResourceService;

    /**
     * 获取资源列表
     */
    @RequestMapping(value = "list", method = {RequestMethod.GET})
    public Response listResource(HttpServletRequest request){
        List<CodAdminPermissionListDto> maps = codAdminResourceService.listResource();
        return maps != null ? super.success(maps) : super.fail();
    }
}
