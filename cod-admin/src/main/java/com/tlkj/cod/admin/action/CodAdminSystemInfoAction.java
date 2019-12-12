/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.admin.action;

import com.tlkj.cod.log.annotation.CodLog;
import com.tlkj.cod.model.common.GeneralResponse;
import com.tlkj.cod.model.common.Response;
import com.tlkj.cod.model.enums.StatusCode;
import org.springframework.web.bind.annotation.RestController;

/**
 * Desc 系统信息 action
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminSystemInfoAction
 * @date 2019/8/21 11:48 PM
 */
@RestController
public class CodAdminSystemInfoAction extends GeneralResponse {

    /**
     * 服务器信息
     * @param statusCode
     * @return
     */
    @CodLog(name = "获取服务器信息")
    public Response serverInfo(StatusCode statusCode) {
        return super.output(statusCode);
    }

    /**
     * 数据库信息
     * @param statusCode
     * @return
     */
    @CodLog(name = "获取数据库信息")
    public Response databaseInfo(StatusCode statusCode) {
        return super.output(statusCode);
    }
}
