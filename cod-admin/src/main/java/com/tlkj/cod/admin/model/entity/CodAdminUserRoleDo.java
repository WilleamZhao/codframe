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

import com.tlkj.cod.dao.model.CodDaoDo;
import lombok.Getter;
import lombok.Setter;

/**
 * Desc 用户角色关联表Do
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminUserRoleDo
 * @date 2018/10/29 上午10:52
 */
@Getter
@Setter
public class CodAdminUserRoleDo extends CodDaoDo {

    private static final long serialVersionUID = 5677920231064319404L;

    public static String TABLE_NAME = "cod_sys_user_role";

    /**
     * 主键
     */
    private String id;

    /**
     * 用户id
     */
    private String user_id;

    /**
     * 角色id
     */
    private String role_id;

}
