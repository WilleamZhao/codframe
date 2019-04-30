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
public class GwgzChapterDto implements Serializable,Cloneable{

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
    private String coverUrl;

    /**
     * 价格
     */
    private String price;

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