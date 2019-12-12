/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.admin.model.dto;

import com.tlkj.cod.common.CodCommonModelConvert;
import lombok.Getter;
import lombok.Setter;

/**
 * Desc 系统核心设置 Dto
 * 服务配置
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminSystemCoreSetDto
 * @date 2019/8/23 11:06 AM
 */
@Getter
@Setter
public class CodAdminSystemCoreSetDto extends CodCommonModelConvert {

    private String id;
    private String key;
    private String value;
    private String name;
    private String desc;
    private String sort;
    private String createTime;
    private String updateTime;
}
