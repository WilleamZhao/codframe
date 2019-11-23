/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.admin.api;

import com.tlkj.cod.model.common.GeneralResponse;
import com.tlkj.cod.model.common.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Desc 健康 api
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminHealthApi
 * @date 2019/11/23 1:44 PM
 */
@RestController
@RequestMapping("admin/health")
public class CodAdminHealthApi extends GeneralResponse {

    /**
     * 健康检查
     * @return
     */
    @RequestMapping(value = "check")
    public Response check(){
        return success();
    }
}
