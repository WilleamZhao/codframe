/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 */

package com.tlkj.cod.model.business.jx.dto;

/**
 * codFrame auto create
 * Dto
 */
public class JxSearchHistoryDto {

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
    private String frequency;

    /**
     * 用户id
     */
    private String userid;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 创建时间
     */
    private String createTime;

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getContent(){
        return this.content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String getFrequency(){
        return this.frequency;
    }

    public void setFrequency(String frequency){
        this.frequency = frequency;
    }

    public String getUserid(){
        return this.userid;
    }

    public void setUserid(String userid){
        this.userid = userid;
    }

    public String getUpdateTime(){
        return this.updateTime;
    }

    public void setUpdateTime(String updateTime){
        this.updateTime = updateTime;
    }

    public String getCreateTime(){
        return this.createTime;
    }

    public void setCreateTime(String createTime){
        this.createTime = createTime;
    }
}