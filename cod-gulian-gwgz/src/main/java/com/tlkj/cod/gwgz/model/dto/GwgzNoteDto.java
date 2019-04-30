package com.tlkj.cod.gwgz.model.dto;

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
 * Dto
 * @author sourcod
 */
@Getter
@Setter
public class GwgzNoteDto implements Serializable,Cloneable{

    /**
     * 主键
     */
    private String id;

    /**
     * 章节id
     */
    private String chapterId;

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
    private String sort;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

}