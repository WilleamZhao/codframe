/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 */
package com.tlkj.cod.model.business.jx.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * codFrame auto create
 * Dto
 */
@Getter
@Setter
public class JxBannerDto {

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
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

}