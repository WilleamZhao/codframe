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
public class GwgzOpinionDo implements Serializable,Cloneable{
    /**
     * 表名
     */
    public static final String TABLE_NAME = "gwgz_opinion";

    /**
     * 主键id
     */
    private String id;

    /**
     * 用户id
     */
    private String user_id;

    /**
     * 反馈内容
     */
    private String content;

    /**
     * 联系方式
     */
    private String contact;

    /**
     * 创建时间
     */
    private String create_time;

    /**
     * 更新时间
     */
    private String update_time;

}