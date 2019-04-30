/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 */

package com.tlkj.cod.gwgz.model.entity;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;


/**
 * codFrame auto create
 */
@Getter
@Setter
public class GwgzNoteDo implements Serializable,Cloneable{
    /**
     * 表名
     */
    public static final String TABLE_NAME = "gwgz_note";

    /**
     * 主键
     */
    private String id;

    /**
     * 章节id
     */
    private String chapter_id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

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