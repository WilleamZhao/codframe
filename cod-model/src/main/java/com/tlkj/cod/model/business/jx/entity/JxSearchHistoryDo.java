/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 */
package com.tlkj.cod.model.business.jx.entity;

import java.io.Serializable;

/**
 * codFrame auto create
 */
public class JxSearchHistoryDo implements Serializable,Cloneable{
    /**
     * 表名
     */
    public static final String TABLE_NAME = "jx_search_history";

    /**
     * 主键
     */
    private String id;

    /**
     * 内容
     */
    private String content;

    /**
     * 搜索次数
     */
    private Integer frequency;

    /**
     * 用户id
     */
    private String userid;

    /**
     * 更新时间
     */
    private String update_time;

    /**
     * 创建时间
     */
    private String create_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}