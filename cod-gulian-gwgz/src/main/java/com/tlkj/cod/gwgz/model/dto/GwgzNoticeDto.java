/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 */

package com.tlkj.cod.gwgz.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


/**
 * codFrame auto create
 * Dto
 * @author sourcod
 */
@Getter
@Setter
public class GwgzNoticeDto implements Serializable, Cloneable {

    /**
     * 主键
     */
    private String id;

    /**
     * 用户id
     */
    private String userid;

    /**
     * 序号
     */
    private String sort;

    /**
     * 状态;0: 未读; 1: 已读;
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