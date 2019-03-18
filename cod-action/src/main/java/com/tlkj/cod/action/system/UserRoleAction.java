/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.action.system;

import com.tlkj.cod.model.common.Response;
import com.tlkj.cod.model.enums.StatusCode;
import com.tlkj.cod.service.system.UserRoleService;
import com.tlkj.cod.model.common.GeneralResponse;
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
 * @className UserRoleAction
 * @date 2018/11/7 下午10:34
 */
@RestController
@RequestMapping("system/user/role")
public class UserRoleAction extends GeneralResponse {

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 保存用户角色
     * @return
     */
    @RequestMapping(value = "save", method = {RequestMethod.POST})
    public Response save(HttpServletRequest request){
        // 用户id
        String id = request.getParameter("id");
        // 角色ids
        String roleId = request.getParameter("roleId");
        StatusCode statusCode = userRoleService.addUserRole(id, roleId);
        return super.output(statusCode);
    }
}
