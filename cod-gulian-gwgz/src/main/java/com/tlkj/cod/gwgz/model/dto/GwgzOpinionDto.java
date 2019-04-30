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
public class GwgzOpinionDto implements Serializable,Cloneable{

    /**
     * 主键id
     */
    private String id;

    /**
     * 用户id
     */
    private String userId;

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
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

}