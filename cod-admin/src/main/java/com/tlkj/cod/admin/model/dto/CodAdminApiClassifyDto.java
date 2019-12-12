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
 * 接口分类表
 * cod_sys_api_classify
 *
 * codFrame auto create
 * @author sourcod
 */
@Getter
@Setter
public class CodAdminApiClassifyDto extends CodCommonModelConvert {

    /**
     * 主键
     */
    private String id;

    /**
     * 分类名称
     */
    private String classifyName;

    /**
     * 上级分类
     */
    private String classifyPid;

    /**
     * 级别
     */
    private String level;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

}