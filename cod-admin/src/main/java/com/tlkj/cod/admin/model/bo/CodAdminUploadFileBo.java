/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.admin.model.bo;

/**
 * Desc 上传文件Bo
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminUploadFileBo
 * @date 2018/11/20 7:10 PM
 */
public class CodAdminUploadFileBo {

    /**
     * url
     */
    private String url;

    /**
     * 全路径
     */
    private String fullUrl;

    /**
     * 上传时间
     */
    private String uploadTime;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }
}
