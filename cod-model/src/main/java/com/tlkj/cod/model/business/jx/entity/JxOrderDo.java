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
public class JxOrderDo extends CodCommonModelConvert implements Serializable,Cloneable{
    /**
     * 表名
     */
    public static final String TABLE_NAME = "jx_order";

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
    private String shop_name;

    /**
     * 商品价格
     */
    private Double shop_price;

    /**
     * 真实价格
     */
    private Double real_price;

    /**
     * 订单状态;1:未支付;2:已支付;
     */
    private String state;

    /**
     * 创建时间
     */
    private String create_time;

    /**
     * 更新时间
     */
    private String update_time;

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

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public Double getShop_price() {
        return shop_price;
    }

    public void setShop_price(Double shop_price) {
        this.shop_price = shop_price;
    }

    public Double getReal_price() {
        return real_price;
    }

    public void setReal_price(Double real_price) {
        this.real_price = real_price;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
