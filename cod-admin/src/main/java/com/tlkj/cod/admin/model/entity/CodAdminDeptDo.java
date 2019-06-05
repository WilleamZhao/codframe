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
 * Desc 部门表Do
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminDeptDo
 * @date 2018/10/30 下午2:43
 */
@Getter
@Setter
public class CodAdminDeptDo extends CodCommonModelConvert implements Serializable {

    public static final String TABLE_NAME = "cod_sys_dept";

    /**
     * 主键
     */
    private String id;

    /**
     * 部门名称
     */
    private String dept_name;

    /**
     * 部门编号
     */
    private String dept_no;

    /**
     * 部门级别
     */
    private String dept_level;

    /**
     * 部门管理员
     */
    private String dept_admin;

    /**
     * 上级部门
     */
    private String p_id;

    /**
     * 公司id
     */
    private String company_id;

    /**
     * 状态
     */
    private String state;

    /**
     * 排序
     */
    private String sort;

    /**
     * 创建时间
     */
    private String create_time;

    /**
     * 更新时间
     */
    private String update_time;

}
