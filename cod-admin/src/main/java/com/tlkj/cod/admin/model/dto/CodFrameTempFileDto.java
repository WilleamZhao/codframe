/*
 *  Copyright (c) 2018-2019.
 *  Beijing sky blue technology co., LTD.
 *  All rights reserved
 *
 *  author: sourcod
 *  github: https://github.com/WilleamZhao
 *  site：http://codframe.com
 */

package com.tlkj.cod.admin.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Desc 通过FileAction上传的文件Do
 *
 * @author sourcod
 * @version 1.0
 * @className CodFrameTempFileDto
 * @date 2019/3/7 6:12 PM
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CodFrameTempFileDto {

    /**
     * 主键
     */
    private String id;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件相对路径
     */
    private String fileUrl;

    /**
     * 文件大小
     */
    private String fileSize;

    /**
     * 文件大小单位
     */
    private String fileUnit;

    /**
     * 扩展名
     */
    private String extName;

    /**
     * 文件地址代码
     */
    private String fileItemCode;

    /**
     * 上传时间
     */
    private String uploadTime;

    /**
     * 文件状态
     */
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
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

    public String getExtName() {
        return extName;
    }

    public void setExtName(String extName) {
        this.extName = extName;
    }

    public String getFileItemCode() {
        return fileItemCode;
    }

    public void setFileItemCode(String fileItemCode) {
        this.fileItemCode = fileItemCode;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
