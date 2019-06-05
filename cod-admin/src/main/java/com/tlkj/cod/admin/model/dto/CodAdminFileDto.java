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
import lombok.Getter;
import lombok.Setter;

/**
 * Desc 文件Dto
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminFileDto
 * @date 2018/12/2 1:09 AM
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CodAdminFileDto {

    public CodAdminFileDto() {

    }

    public CodAdminFileDto(String fileName, String url) {
        this.fileName = fileName;
        this.url = url;
    }

    /**
     * 原文件名
     */
    private String fileName;

    /**
     * 上传后文件相对路径
     */
    private String url;

    /**
     * 扩展名
     */
    private String extName;

    /**
     * 文件大小
     */
    private String fileSize;

    /**
     * 文件大小单位
     */
    private String fileUnit;

}
