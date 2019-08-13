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
 * Desc 权限资源关联表Do
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminPermissionResource
 * @date 2018/10/30 下午3:12
 */
@Getter
@Setter
public class CodAdminPermissionResource extends CodCommonModelConvert implements Serializable {

    public static String TABLE_NAME = "cod_sys_permission_resource";

    /**
     * 主键
     */
    private String id;

    /**
     * 权限id
     */
    private String permission_id;

    /**
     * 资源id
     */
    private String resource_id;

}
