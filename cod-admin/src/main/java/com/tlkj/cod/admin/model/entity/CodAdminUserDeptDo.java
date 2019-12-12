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
 * Desc 用户部门表
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminUserDeptDo
 * @date 2019/1/7 11:26 PM
 */
@Getter
@Setter
public class CodAdminUserDeptDo extends CodDaoDo {

    private static final long serialVersionUID = -8250210077002603509L;

    public static final String TABLE_NAME = "cod_sys_user_dept";

    /**
     * 主键
     */
    private String id;

    /**
     * 用户id
     */
    private String user_id;

    /**
     * 部门id
     */
    private String dept_id;
}
