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

/**
 * Desc 字典数据表
 *
 * @author sourcod
 * @version 1.0
 * @className CodFrameDictItemDo
 * @date 2018/11/8 下午9:35
 */
public class CodFrameDictItemDo {

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public String getItem_value() {
        return item_value;
    }

    public void setItem_value(String item_value) {
        this.item_value = item_value;
    }

    public String getIsfixed() {
        return isfixed;
    }

    public void setIsfixed(String isfixed) {
        this.isfixed = isfixed;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEnglish_name() {
        return english_name;
    }

    public void setEnglish_name(String english_name) {
        this.english_name = english_name;
    }

    public String getAllpin() {
        return allpin;
    }

    public void setAllpin(String allpin) {
        this.allpin = allpin;
    }

    public String getSimplepin() {
        return simplepin;
    }

    public void setSimplepin(String simplepin) {
        this.simplepin = simplepin;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}
