/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.api.wechat;

import com.tlkj.cod.model.common.Response;
import com.tlkj.cod.model.wechat.dto.WechatLoginDto;
import com.tlkj.cod.service.wechat.WechatService;
import com.tlkj.cod.model.common.GeneralResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Desc 微信api
 *
 * @author sourcod
 * @version 1.0
 * @className WechatApi
 * @date 2018/9/13 下午1:50
 */
@RestController
@RequestMapping("wechat")
public class WechatApi extends GeneralResponse {

    @Autowired
    private WechatService wechatService;

    @ResponseBody
    @RequestMapping(value = "getOpenId", method = {RequestMethod.GET})
    public Response getOpenId(HttpServletRequest request){
        String code = request.getParameter("code");
        String appId = request.getParameter("appId");
        String secret = request.getParameter("secret");
        WechatLoginDto wechatLoginDto = wechatService.getOpenid(code, appId, secret);
        return wechatLoginDto == null ? super.fail() : super.success(wechatLoginDto);
    }
}
