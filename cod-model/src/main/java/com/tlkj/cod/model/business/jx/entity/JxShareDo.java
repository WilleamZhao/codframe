/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 */

package com.tlkj.cod.model.business.jx.entity;

import java.io.Serializable;

/**
 * codFrame auto create
 */
public class JxShareDo implements Serializable,Cloneable{

    /**
     * 表名
     */
    public static final String TABLE_NAME = "jx_share";

    /**
     * 主键
     */
    private String id;

    /**
     * 图片
     */
    private String img;

    /**
     * 标题
     */
    private String title;

    /**
     * 简介
     */
    private String intro;

    /**
     * 内容
     */
    private String content;

    /**
     * 预览地址
     */
    private String preview_url;

    /**
     * 下载地址
     */
    private String download_url;

    /**
     * 是否精华
     */
    private String fine;

    /**
     * 作者
     */
    private String author;

    /**
     * 点赞数量
     */
    private Integer zan_num;

    /**
     * 评论数量
     */
    private Integer comment_num;

    /**
     * 预览数量
     */
    private Integer preview_num;

    /**
     * 价格
     */
    private Double price;

    /**
     * 是否置顶
     */
    private String top;

    /**
     * 排序
     */
    private String sort;

    /**
     * 是否推荐
     */
    private String recommend;

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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPreview_url() {
        return preview_url;
    }

    public void setPreview_url(String preview_url) {
        this.preview_url = preview_url;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public String getFine() {
        return fine;
    }

    public void setFine(String fine) {
        this.fine = fine;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getZan_num() {
        return zan_num;
    }

    public void setZan_num(Integer zan_num) {
        this.zan_num = zan_num;
    }

    public Integer getComment_num() {
        return comment_num;
    }

    public void setComment_num(Integer comment_num) {
        this.comment_num = comment_num;
    }

    public Integer getPreview_num() {
        return preview_num;
    }

    public void setPreview_num(Integer preview_num) {
        this.preview_num = preview_num;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
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