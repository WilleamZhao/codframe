/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.admin.model.entity;

import java.io.Serializable;

/**
 * Desc 系统缓存表
 *
 * @author sourcod
 * @version 1.0
 * @className CodFrameCacheDo
 * @date 2019/2/12 10:52 AM
 */
public class CodFrameCacheDo implements Serializable {

    /**
     * 表名
     */
    public final static String TABLE_NAME = "cod_sys_cache";

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getDue() {
        return due;
    }

    public void setDue(int due) {
        this.due = due;
    }

    public String getIdle() {
        return idle;
    }

    public void setIdle(String idle) {
        this.idle = idle;
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
