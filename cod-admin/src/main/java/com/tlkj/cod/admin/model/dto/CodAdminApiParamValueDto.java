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
 * 参数值表
 * cod_sys_api_param_value
 *
 * codFrame auto create
 * @author sourcod
 */
@Getter
@Setter
public class CodAdminApiParamValueDto extends CodCommonModelConvert {

    /**
     * 主键
     */
    private String id;

    /**
     * 值
     */
    private String value;

    /**
     * 值描述
     */
    private String valueDesc;

    /**
     * 参数id
     */
    private String paramId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

}