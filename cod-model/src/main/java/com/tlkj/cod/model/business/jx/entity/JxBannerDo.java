/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 */

package com.tlkj.cod.model.business.jx.entity;

import com.tlkj.cod.common.CodCommonJson;
import com.tlkj.cod.common.CodCommonModelConvert;
import com.tlkj.cod.model.business.jx.dto.JxBannerDto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * codFrame auto create
 */
public class JxBannerDo extends CodCommonModelConvert implements Serializable,Cloneable{

    /**
     * 表名
     */
    public static final String TABLE_NAME = "jx_banner";

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

    private boolean ad;

    private int asd;
    private char a;
    private double b;
    private String bigDecimal;

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

    public String getBigDecimal() {
        return bigDecimal;
    }

    public void setBigDecimal(String bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    public static void main(String[] args) {
        JxBannerDo bannerDo = new JxBannerDo();
        bannerDo.setId("123");
        bannerDo.setSort("1");
        bannerDo.setImg("asdasda");
        bannerDo.setType("2");
        bannerDo.setCreate_time("asdasda");
        bannerDo.setA('a');
        bannerDo.setAd(false);
        bannerDo.setAsd(123123123);
        bannerDo.setB(456.456);
        bannerDo.setBigDecimal("asdas");


        System.out.println(CodCommonJson.dump(bannerDo));
        JxBannerDto jxBannerDto = bannerDo.toDto(JxBannerDto.class);
        System.out.println(CodCommonJson.dump(jxBannerDto));
    }
}