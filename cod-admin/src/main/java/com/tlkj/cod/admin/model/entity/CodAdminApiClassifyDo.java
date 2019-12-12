/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.admin.model.entity;

import com.tlkj.cod.dao.model.CodDaoDo;
import lombok.Getter;
import lombok.Setter;

/**
 * 接口分类表
 * cod_sys_api_classify
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminApiClassifyDo
 * @date 2019/10/10 4:53 PM
 */
@Getter
@Setter
public class CodAdminApiClassifyDo extends CodDaoDo {

    /**
     * 表名
     */
    public static final String TABLE_NAME = "cod_sys_api_classify";

    private static final long serialVersionUID = -4390792548156740564L;

    /**
     * 主键
     */
    private String id;

    /**
     * 分类名称
     */
    private String classify_name;

    /**
     * 上级分类
     */
    private String classify_pId;

    /**
     * 级别
     */
    private Integer level;

    /**
     * 创建时间
     */
    private String create_time;

    /**
     * 更新时间
     */
    private String update_time;

}