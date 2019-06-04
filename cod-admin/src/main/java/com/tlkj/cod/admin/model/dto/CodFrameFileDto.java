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
 * Desc 文件Dto
 *
 * @author sourcod
 * @version 1.0
 * @className CodFrameFileDto
 * @date 2018/12/2 1:09 AM
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CodFrameFileDto {

    public CodFrameFileDto() {

    }

    public CodFrameFileDto(String fileName, String url) {
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExtName() {
        return extName;
    }

    public void setExtName(String extName) {
        this.extName = extName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileUnit() {
        return fileUnit;
    }

    public void setFileUnit(String fileUnit) {
        this.fileUnit = fileUnit;
    }
}
