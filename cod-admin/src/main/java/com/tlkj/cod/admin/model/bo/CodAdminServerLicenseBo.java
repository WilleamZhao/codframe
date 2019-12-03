/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.admin.model.bo;

import lombok.Getter;
import lombok.Setter;

/**
 * Desc 服务 获取 license Bo
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminServerLicenseBo
 * @date 2019/12/3 8:55 PM
 */
@Getter
@Setter
public class CodAdminServerLicenseBo {

    /**
     * license 类型
     * 1: 0: 禁用; 1: 启用;
     * 2: 时间 (yyyy-MM-dd HH:mm:ss)
     *
     */
    private String type;

    /**
     * license 值
     */
    private String value;
}
