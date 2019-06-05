/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.admin.model.entity;

import com.tlkj.cod.common.CodCommonModelConvert;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Desc 系统设置Do
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminSetDo
 * @date 2018/11/27 7:52 PM
 */
@Getter
@Setter
public class CodAdminSetDo extends CodCommonModelConvert implements Serializable {

    public static String TABLE_NAME = "cod_sys_set";

    /**
     * 主键
     */
    private String id;

    /**
     * 设置名称
     */
    private String set_name;

    /**
     * 设置代码
     */
    private String set_code;

    /**
     * 设置值
     */
    private String set_value;

    /**
     * 操作用户id
     */
    private String user_id;

    /**
     * 排序
     */
    private String sort;

    /**
     * 更新时间
     */
    private String update_time;

}
