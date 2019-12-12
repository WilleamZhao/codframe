/*
 *  Copyright (c) 2018-2019.
 *  Beijing sky blue technology co., LTD.
 *  All rights reserved
 *
 *  author: sourcod
 *  github: https://github.com/WilleamZhao
 *  site：http://codframe.sourcod.com
 */

package com.tlkj.cod.admin.model.core;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className SystemFileOssModel
 * @date 2019/3/7 9:57 PM
 */
public class SystemFileOssModel {

    private String endPoint;

    private String accessKeyId;

    private String accessKeySecret;

    private String bucketName = "codframe";

    private int maxConnections = 200;

    private int socketTimeout = 10000;

    private int maxErrorRetry = 3;

    private String projectName = "codframe";

    private String headUrl;

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public int getMaxConnections() {
        return maxConnections;
    }

    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public int getMaxErrorRetry() {
        return maxErrorRetry;
    }

    public void setMaxErrorRetry(int maxErrorRetry) {
        this.maxErrorRetry = maxErrorRetry;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }
}
