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
public class GwgzCollectDto implements Serializable,Cloneable{

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