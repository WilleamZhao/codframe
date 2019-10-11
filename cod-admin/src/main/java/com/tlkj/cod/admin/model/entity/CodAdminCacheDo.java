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
 * Desc 系统缓存表
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminCacheDo
 * @date 2019/2/12 10:52 AM
 */
@Getter
@Setter
public class CodAdminCacheDo extends CodDaoDo {

    /**
     * 表名
     */
    public final static String TABLE_NAME = "cod_sys_cache";

    private static final long serialVersionUID = 8549133537189231206L;

    /**
     * 主键
     */
    private String id;

    /**
     * key
     */
    private String k;

    /**
     * value
     */
    private String v;

    /**
     * 状态
     * 0: 禁用;
     * 1: 启用;
     */
    private String state;

    /**
     * 到期时间
     */
    private int due;

    /**
     * 空闲到期还是初始化到期
     */
    private String idle;

    /**
     * 创建时间
     */
    private String create_time;

    /**
     * 更新时间
     */
    private String update_time;

}
