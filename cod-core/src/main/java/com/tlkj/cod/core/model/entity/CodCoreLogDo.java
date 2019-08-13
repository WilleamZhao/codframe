/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.core.model.entity;

import com.tlkj.cod.common.CodCommonModelConvert;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Desc 系统日志Do
 *
 * @author sourcod
 * @version 1.0
 * @className CodCoreLogDo
 * @date 2018/12/6 4:44 PM
 */
@Getter
@Setter
public class CodCoreLogDo extends CodCommonModelConvert implements Serializable {

    private static final long serialVersionUID = 4672720709892517275L;

    public static String TABLE_NAME = "cod_sys_log";

    /**
     * 主键
     */
    private String id;

    /**
     * 内容
     */
    private String content;

    /**
     * 操作名称
     */
    private String operation_name;

    /**
     * 操作类型
     */
    private String operation_type;

    /**
     * 用户名
     */
    private String username;

    /**
     * 操作ip
     */
    private String ip;

    /**
     * 返回值
     */
    private String results;

    /**
     * 方法名
     */
    private String method_name;

    /**
     * 参数
     */
    private String params;

    /**
     * 错误消息
     */
    private String error_msg;

    /**
     * 创建时间
     */
    private String create_time;

}
