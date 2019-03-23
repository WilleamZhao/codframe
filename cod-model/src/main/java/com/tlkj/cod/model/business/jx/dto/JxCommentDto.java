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
public class JxCommentDto {

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
    private String pId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 点赞数量
     */
    private String zanNum;

    /**
     * 状态;0:禁用;1:启用;-1:已删除;
     */
    private String state;

    /**
     * 分享id
     */
    private String shareId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

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

    public String getPId(){
        return this.pId;
    }

    public void setPId(String pId){
        this.pId = pId;
    }

    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getZanNum(){
        return this.zanNum;
    }

    public void setZanNum(String zanNum){
        this.zanNum = zanNum;
    }

    public String getState(){
        return this.state;
    }

    public void setState(String state){
        this.state = state;
    }

    public String getShareId(){
        return this.shareId;
    }

    public void setShareId(String shareId){
        this.shareId = shareId;
    }

    public String getCreateTime(){
        return this.createTime;
    }

    public void setCreateTime(String createTime){
        this.createTime = createTime;
    }

    public String getUpdateTime(){
        return this.updateTime;
    }

    public void setUpdateTime(String updateTime){
        this.updateTime = updateTime;
    }
}