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
public class JxOrderDto {

    /**
     * 主键
     */
    private String id;

    /**
     * 订单号
     */
    private String no;

    /**
     * 商品名称
     */
    private String shopName;

    /**
     * 商品价格
     */
    private String shopPrice;

    /**
     * 真实价格
     */
    private String realPrice;

    /**
     * 订单状态;1:未支付;2:已支付;
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

    public String getNo(){
        return this.no;
    }

    public void setNo(String no){
        this.no = no;
    }

    public String getShopName(){
        return this.shopName;
    }

    public void setShopName(String shopName){
        this.shopName = shopName;
    }

    public String getShopPrice(){
        return this.shopPrice;
    }

    public void setShopPrice(String shopPrice){
        this.shopPrice = shopPrice;
    }

    public String getRealPrice(){
        return this.realPrice;
    }

    public void setRealPrice(String realPrice){
        this.realPrice = realPrice;
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
}