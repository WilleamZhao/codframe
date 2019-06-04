/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.admin.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Desc 附件类型Dto
 *
 * @author sourcod
 * @version 1.0
 * @className CodFrameAttachmentTypeDto
 * @date 2018/11/30 7:00 PM
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CodFrameAttachmentTypeDto {

    /**
     * 主键
     */
    private String id;

    /**
     * 附件名称
     */
    private String name;

    /**
     * 附件代码
     */
    private String code;

    /**
     * 附件值
     */
    private String value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
