/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.api.system;

import com.tlkj.cod.model.common.Response;
import com.tlkj.cod.model.system.dto.CodFrameUserDto;
import com.tlkj.cod.service.system.UserService;
import com.tlkj.cod.model.common.GeneralResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Desc 用户api
 *
 * @author sourcod
 * @version 1.0
 * @className UserApi
 * @date 2018/12/21 11:21 AM
 */
@RestController
@RequestMapping("api/user")
public class UserApi extends GeneralResponse {

    @Autowired
    UserService userService;

    /**
     * 获取用户信息
     */
    @RequestMapping(value = "getUser", method = {RequestMethod.GET})
    public Response getUserByCache(HttpServletRequest request){
        String token = request.getParameter("token");
        CodFrameUserDto dto = userService.getUserByCache(token);
        return dto == null ? super.fail() : super.success(dto);
    }
}
