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
 * Desc 附件列表Dto
 *
 * @author sourcod
 * @version 1.0
 * @className CodFrameAttachmentListDto
 * @date 2018/11/20 3:33 PM
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CodFrameAttachmentListDto {

    /**
     * 附件Id
     */
    private String id;

    /**
     * 附件url
     */
    private String url;

    /**
     * 附件大小
     */
    private String size;

    /**
     * 大小单位
     */
    private String unit;

    /**
     * 附件名称
     */
    private String fileName;

    /**
     * 附件类型
     */
    private String fileType;

    /**
     * 附件状态
     */
    private String fileStatus;

    /**
     * 扩展名
     */
    private String extName;

    /**
     * 上传ip
     */
    private String ip;

    /**
     * 上传时间
     */
    private String uploadDate;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getExtName() {
        return extName;
    }

    public void setExtName(String extName) {
        this.extName = extName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(String fileStatus) {
        this.fileStatus = fileStatus;
    }
}
