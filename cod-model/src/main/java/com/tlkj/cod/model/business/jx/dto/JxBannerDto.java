/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 */
package com.tlkj.cod.model.business.jx.dto;

import java.math.BigDecimal;

/**
 * codFrame auto create
 * Dto
 */
public class JxBannerDto {

    /**
     * 主键
     */
    private String id;

    /**
     * 图片
     */
    private String img;

    /**
     * 链接地址
     */
    private String url;

    /**
     * 类型
     */
    private String type;

    /**
     * 排序
     */
    private String sort;

    /**
     * 状态
     */
    private String state;

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

    public String getImg(){
        return this.img;
    }

    public void setImg(String img){
        this.img = img;
    }

    public String getUrl(){
        return this.url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public String getType(){
        return this.type;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getSort(){
        return this.sort;
    }

    public void setSort(String sort){
        this.sort = sort;
    }

    public String getState(){
        return this.state;
    }

    public void setState(String state){
        this.state = state;
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

    private boolean ad;

    private int asd;
    private char a;
    private double b;
    private BigDecimal bigDecimal;

    public boolean isAd() {
        return ad;
    }

    public void setAd(boolean ad) {
        this.ad = ad;
    }

    public int getAsd() {
        return asd;
    }

    public void setAsd(int asd) {
        this.asd = asd;
    }

    public char getA() {
        return a;
    }

    public void setA(char a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public BigDecimal getBigDecimal() {
        return bigDecimal;
    }

    public void setBigDecimal(BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }
}