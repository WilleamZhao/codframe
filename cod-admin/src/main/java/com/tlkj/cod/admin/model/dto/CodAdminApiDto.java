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
 * 接口表
 * cod_sys_api
 *
 * codFrame auto create
 * @author sourcod
 */
@Getter
@Setter
public class CodAdminApiDto extends CodCommonModelConvert {

    /**
     * 主键
     */
    private String id;

    /**
     * 接口名称
     */
    private String apiName;

    /**
     * 接口类型;对外接口, 对内接口
     */
    private String apiType;

    /**
     * 接口分类
     */
    private String apiClassifyId;

    /**
     * 接口价格;消耗积分
     */
    private String apiPrice;

    /**
     * 请求方式
     */
    private String apiMethod;

    /**
     * 用户级别
     */
    private String userLevel;

    /**
     * 返回格式;1: json; 2: xml; 3: txt
     */
    private String resultFormat;

    /**
     * 备注
     */
    private String remark;

    /**
     * 资源id
     */
    private String resourceId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

}