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
public class JxCategoryDto {

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 主键
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 父id
     */
    private String pId;

    /**
     * 排序
     */
    private String sort;

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

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPId(){
        return this.pId;
    }

    public void setPId(String pId){
        this.pId = pId;
    }

    public String getSort(){
        return this.sort;
    }

    public void setSort(String sort){
        this.sort = sort;
    }
}