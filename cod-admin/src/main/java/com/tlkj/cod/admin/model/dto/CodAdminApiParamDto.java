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
 * 接口参数 Dto
 * cod_sys_api_param
 *
 * codFrame auto create
 * @author sourcod
 */
@Getter
@Setter
public class CodAdminApiParamDto extends CodCommonModelConvert {

    /**
     * 主键
     */
    private String id;

    /**
     * 参数名称
     */
    private String paramName;

    /**
     * 参数key
     */
    private String paramKey;

    /**
     * 参数值
     */
    private String paramValue;

    /**
     * 参数类型;1: 请求参数
     2: 返回参数
     */
    private String paramType;

    /**
     * 接口id
     */
    private String apiId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

}