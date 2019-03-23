/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 */

package com.tlkj.cod.model.business.jx.entity;

import com.tlkj.cod.common.CodCommonJson;
import com.tlkj.cod.common.CodCommonModelConvert;
import com.tlkj.cod.model.business.jx.dto.JxBannerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

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

    private static Logger logger = LoggerFactory.getLogger(JxBannerDo.class);
    public static void main(String[] args) {
        logger.info("test");
        JxBannerDo bannerDo = new JxBannerDo();
        bannerDo.setId("123");
        bannerDo.setSort("1");
        bannerDo.setImg("asdasda");
        bannerDo.setType("2");
        bannerDo.setCreate_time("asdasda");

        System.out.println(CodCommonJson.dump(bannerDo));
        JxBannerDto jxBannerDto = bannerDo.toDto(JxBannerDto.class);
        System.out.println(CodCommonJson.dump(jxBannerDto));

    }
}