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
 * Desc 字典数据表
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminDictItemDo
 * @date 2018/11/8 下午9:35
 */
@Getter
@Setter
public class CodAdminDictItemDo extends CodDaoDo {

    private static final long serialVersionUID = -3918612944593337362L;

    public static String TABLE_NAME = "cod_sys_dict_item";

    /**
     * 主键
     */
    private String id;

    /**
     * 类型id
     */
    private String type_id;

    /**
     * 数据名称
     */
    private String item_name;

    /**
     * 数据代码
     */
    private String item_code;

    /**
     * 数据值
     */
    private String item_value;

    /**
     * 是否固定
     */
    private String isfixed;

    /**
     * 状态
     * 0: 禁用
     * 1: 启用
     * -1: 删除
     */
    private String state;

    /**
     * 类型
     * 0: 系统默认 (不可删除)
     * 1: 用户自定义
     */
    private String type;

    /**
     * 英文名称
     */
    private String english_name;

    /**
     * 全拼
     */
    private String allpin;

    /**
     * 简拼
     */
    private String simplepin;

    /**
     * 排序
     */
    private String sort;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private String create_time;

    /**
     * 更新时间
     */
    private String update_time;
}
