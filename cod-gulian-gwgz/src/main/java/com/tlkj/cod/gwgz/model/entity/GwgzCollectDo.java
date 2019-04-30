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
public class GwgzCollectDo implements Serializable,Cloneable{
    /**
     * 表名
     */
    public static final String TABLE_NAME = "gwgz_collect";

    /**
     * 主键
     */
    private String id;

    /**
     * 词语
     */
    private String word;

    /**
     * 注释
     */
    private String annotation;

    /**
     * 来源
     */
    private String source;

    /**
     * 原文
     */
    private String original;

    /**
     * 序号
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