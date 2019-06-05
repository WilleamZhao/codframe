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
 * Desc 文件路径Dto
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminFileUrlDto
 * @date 2018/12/20 8:55 PM
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CodAdminFileUrlDto {

    /**
     * 类型
     */
    private String type;

    /**
     * 路径
     */
    private String url;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
