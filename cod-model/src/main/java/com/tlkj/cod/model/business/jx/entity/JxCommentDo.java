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
public class JxCommentDo implements Serializable,Cloneable{
    /**
     * 表名
     */
    public static final String TABLE_NAME = "jx_comment";

    /**
     * 主键
     */
    private String id;

    /**
     * 内容
     */
    private String content;

    /**
     * 父评论
     */
    private String p_id;

    /**
     * 用户id
     */
    private String user_id;

    /**
     * 点赞数量
     */
    private Integer zan_num;

    /**
     * 状态;0:禁用;1:启用;-1:已删除;
     */
    private String state;

    /**
     * 分享id
     */
    private String share_id;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Integer getZan_num() {
        return zan_num;
    }

    public void setZan_num(Integer zan_num) {
        this.zan_num = zan_num;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getShare_id() {
        return share_id;
    }

    public void setShare_id(String share_id) {
        this.share_id = share_id;
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