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
import com.tlkj.cod.model.common.SystemResponse;
import com.tlkj.cod.user.facade.CodCommonUserFacade;
import com.tlkj.cod.user.model.dto.CodCommonUserDto;
import com.tlkj.cod.user.model.enums.LoginType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Desc 用户Api
 *
 * @author sourcod
 * @version 1.0
 * @className JxUserApi
 * @date 2019/3/24 4:33 PM
 */
@RestController
@RequestMapping("jx/user")
public class JxUserApi extends GeneralResponse {

    @Autowired
    CodCommonUserFacade codCommonUserFacade;

    @ParamNotNull(parameter = "username")
    @RequestMapping(value = "login", method = {RequestMethod.POST})
    public Response login(HttpServletRequest request){
        String username = getParams(request, "username");
        String password = getParams(request, "password");
        String code = getParams(request, "code");
        String type = getParams(request, "type");
        SystemResponse response = codCommonUserFacade.login(LoginType.getType(type), username, password, code);
        return output(response);
    }

    @RequestMapping(value = "register", method = {RequestMethod.POST})
    public Response register(HttpServletRequest request, CodCommonUserDto dto){
        String username = getParams(request, "username");
        String code = getParams(request, "code");
        String type = getParams(request, "type");
        SystemResponse response = codCommonUserFacade.register(LoginType.getType(type), username, code, dto);
        return output(response);
    }



}
