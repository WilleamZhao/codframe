/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.admin.model.entity;

import com.tlkj.cod.common.CodCommonModelConvert;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Desc 字典类型表
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminDictTypeDo
 * @date 2018/11/7 下午9:55
 */
@Getter
@Setter
public class CodAdminDictTypeDo extends CodCommonModelConvert implements Serializable {

    public static String TABLE_NAME = "cod_sys_dict_type";

    /**
     * 主键
     */
    private String id;

    /**
     * 字典代码
     */
    private String type_code;

    /**
     * 字典名称
     */
    private String type_name;

    /**
     * 英文名称
     */
    private String english_name;

    /**
     * 全拼
     */
    private String allpin;

    /**
     * 字典状态
     * 0: 禁用
     * 1: 启用
     * -1: 删除
     */
    private String state;

    /**
     * 类型
     * 0: 系统默认(不可删除)
     * 1: 用户自定义
     */
    private String type;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private String create_time;

    /**
     * 更新时间
     */
    private String update_time;

}
