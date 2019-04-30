package com.tlkj.cod.gwgz.model.entity;

/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 */

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;


/**
 * codFrame auto create
 */
@Getter
@Setter
public class GwgzChapterDo implements Serializable,Cloneable{
    /**
     * 表名
     */
    public static final String TABLE_NAME = "gwgz_chapter";

    /**
     * 主键
     */
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 作者
     */
    private String content;

    /**
     * 封面
     */
    private String cover_url;

    /**
     * 价格
     */
    private Double price;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建时间
     */
    private String create_time;

    /**
     * 更新时间
     */
    private String update_time;

}