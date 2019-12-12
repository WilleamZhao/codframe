/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.admin.action;

import com.tlkj.cod.admin.service.CodAdminSystemSetService;
import com.tlkj.cod.model.common.GeneralResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Desc 系统数据设置
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminSystemDataSetAction
 * @date 2019/8/29 6:04 PM
 */
@RestController
@RequestMapping("system/data/set")
public class CodAdminSystemDataSetAction extends GeneralResponse {

    @Autowired
    CodAdminSystemSetService codAdminSystemSetService;


}
