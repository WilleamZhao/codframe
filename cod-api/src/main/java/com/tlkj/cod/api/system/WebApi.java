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
import com.tlkj.cod.service.system.SystemSetService;
import com.tlkj.cod.model.common.GeneralResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Desc webApi
 *
 * @author sourcod
 * @version 1.0
 * @className WebApi
 * @date 2018/12/21 9:57 AM
 */
@RestController
@RequestMapping("api/web")
public class WebApi extends GeneralResponse {

    @Autowired
    private SystemSetService systemSetService;

    /**
     * 获取前端url
     */
    @RequestMapping(value = "getFrontUrl", method = {RequestMethod.GET})
    public Response getWebUrl(){
        String frontUrl = systemSetService.getSetValue("web_front_url");
        return StringUtils.isNotBlank(frontUrl) ? super.success(frontUrl) : super.fail();
    }

    /**
     * 获取网站名称
     */
    @RequestMapping(value = "getWebName", method = {RequestMethod.GET})
    public Response getWebName(){
        String webName = systemSetService.getSetValue("web_name");
        return StringUtils.isNotBlank(webName) ? super.success(webName) : super.fail();
    }
}