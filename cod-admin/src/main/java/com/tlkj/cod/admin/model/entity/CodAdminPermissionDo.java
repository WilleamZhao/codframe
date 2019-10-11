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
 * Desc 权限表Do
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminPermissionDo
 * @date 2018/10/30 下午2:40
 */
@Getter
@Setter
public class CodAdminPermissionDo extends CodDaoDo {

    public static String TABLE_NAME = "cod_sys_permission";

    private static final long serialVersionUID = 3758547462601696112L;

    /**
     * 主键
     */
    private String id;

    /**
     * 权限名称
     */
    private String permission_name;

    /**
     * 权限描述
     */
    private String permission_desc;

    /**
     * 权限代码
     */
    private String permission_code;

    /**
     * 权限
     */
    private int permission;

    /**
     * 状态
     */
    private int state;

}
