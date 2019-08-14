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
 * Desc 菜单表Do
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminMenuDo
 * @date 2018/10/29 下午2:05
 */
@Getter
@Setter
public class CodAdminMenuDo extends CodCommonModelConvert implements Serializable {

    private static final long serialVersionUID = -7890711379967874689L;

    public static String TABLE_NAME = "cod_sys_menu";

    /**
     * 主键
     */
    private String id;

    /**
     * 菜单名称
     */
    private String menu_name;

    /**
     * 菜单显示名称
     */
    private String menu_title;

    /**
     * 跳转路径
     */
    private String jump;

    /**
     * 父id
     */
    private String p_id;

    /**
     * 菜单级别
     */
    private String level;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 菜单图标
     */
    private String status;

    /**
     * 排序
     */
    private String sort;

    /**
     * 创建时间
     */
    private String create_time;

    /**
     * 修改时间
     */
    private String update_time;

}
