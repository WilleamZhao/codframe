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
public class JxAdDto {

    /**
     * 主键
     */
    private String id;

    /**
     * 图片地址
     */
    private String imgUrl;

    /**
     * 广告名称
     */
    private String name;

    /**
     * 广告位置;1:右侧;2:banner;
     */
    private String location;

    /**
     * 广告顺序
     */
    private String sort;

    /**
     * url
     */
    private String url;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 提示
     */
    private String tip;

    /**
     * 状态;0:禁用;1:启用;
     */
    private String state;

    /**
     * 联系人
     */
    private String contact;

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

    public String getImgUrl(){
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl){
        this.imgUrl = imgUrl;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getLocation(){
        return this.location;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public String getSort(){
        return this.sort;
    }

    public void setSort(String sort){
        this.sort = sort;
    }

    public String getUrl(){
        return this.url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public String getStartTime(){
        return this.startTime;
    }

    public void setStartTime(String startTime){
        this.startTime = startTime;
    }

    public String getEndTime(){
        return this.endTime;
    }

    public void setEndTime(String endTime){
        this.endTime = endTime;
    }

    public String getTip(){
        return this.tip;
    }

    public void setTip(String tip){
        this.tip = tip;
    }

    public String getState(){
        return this.state;
    }

    public void setState(String state){
        this.state = state;
    }

    public String getContact(){
        return this.contact;
    }

    public void setContact(String contact){
        this.contact = contact;
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