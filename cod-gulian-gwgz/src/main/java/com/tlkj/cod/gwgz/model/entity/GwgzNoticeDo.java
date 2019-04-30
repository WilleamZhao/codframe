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
public class GwgzNoticeDo implements Serializable,Cloneable{
    /**
     * 表名
     */
    public static final String TABLE_NAME = "gwgz_notice";

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
    private Integer sort;

    /**
     * 状态;0: 未读; 1: 已读;
     */
    private String state;

    /**
     * 创建时间
     */
    private String create_time;

    /**
     * 更新时间
     */
    private String update_time;

}