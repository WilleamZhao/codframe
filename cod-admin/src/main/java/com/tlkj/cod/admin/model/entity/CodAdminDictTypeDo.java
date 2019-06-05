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
 * Desc 字典类型表
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminDictTypeDo
 * @date 2018/11/7 下午9:55
 */
public class CodAdminDictTypeDo {

    public static String TABLE_NAME = "cod_sys_dict_type";

    /**
     * 主键
     */
    private String id;

    /**
     * 字典代码
     */
    private String type_code;

    /**
     * 字典名称
     */
    private String type_name;

    /**
     * 英文名称
     */
    private String english_name;

    /**
     * 全拼
     */
    private String allpin;

    /**
     * 字典状态
     * 0: 禁用
     * 1: 启用
     * -1: 删除
     */
    private String state;

    /**
     * 类型
     * 0: 系统默认(不可删除)
     * 1: 用户自定义
     */
    private String type;

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

    public String getType_code() {
        return type_code;
    }

    public void setType_code(String type_code) {
        this.type_code = type_code;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
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
