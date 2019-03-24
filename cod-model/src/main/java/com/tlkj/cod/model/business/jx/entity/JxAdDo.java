/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 */
package com.tlkj.cod.model.business.jx.entity;

import com.tlkj.cod.common.CodCommonModelConvert;

import java.io.Serializable;


/**
 * codFrame auto create
 */
public class JxAdDo extends CodCommonModelConvert implements Serializable,Cloneable{
    /**
     * 表名
     */
    public static final String TABLE_NAME = "jx_ad";

    /**
     * 主键
     */
    private String id;

    /**
     * 图片地址
     */
    private String img_url;

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
    private Integer sort;

    /**
     * url
     */
    private String url;

    /**
     * 开始时间
     */
    private String start_time;

    /**
     * 结束时间
     */
    private String end_time;

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

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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