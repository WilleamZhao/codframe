/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 */

package com.tlkj.cod.model.business.jx.entity;

import com.tlkj.cod.common.CodCommonModelConvert;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * codFrame auto create
 */
@Getter
@Setter
public class JxShareDo extends CodCommonModelConvert implements Serializable,Cloneable{

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
    private String price;

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

}