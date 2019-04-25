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
public class JxAdDto {

    /**
     * 主键
     */
    private String id;

    /**
     * 图片地址
     */
    private String imgUrl;

    /**
     * 广告名称
     */
    private String name;

    /**
     * 广告位置;1:右侧;2:banner;
     */
    private String location;

    /**
     * 广告顺序
     */
    private String sort;

    /**
     * url
     */
    private String url;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 提示
     */
    private String tip;

    /**
     * 状态;0:禁用;1:启用;
     */
    private String state;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;
}