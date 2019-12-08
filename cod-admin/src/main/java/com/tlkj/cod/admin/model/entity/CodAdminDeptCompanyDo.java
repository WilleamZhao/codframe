/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.admin.model.entity;

import com.tlkj.cod.dao.model.CodDaoDo;
import lombok.Getter;
import lombok.Setter;


/**
 * Desc 部门公司关联表
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminDeptCompanyDo
 * @date 2019/1/7 10:13 PM
 */
@Getter
@Setter
public class CodAdminDeptCompanyDo extends CodDaoDo {

    public static final String TABLE_NAME = "cod_sys_dept_company";

    private static final long serialVersionUID = -2322805774341056417L;

    /**
     * 主键
     */
    private String id;

    /**
     * 部门id
     */
    private String dept_id;

    /**
     * 公司id
     */
    private String company_id;
}
