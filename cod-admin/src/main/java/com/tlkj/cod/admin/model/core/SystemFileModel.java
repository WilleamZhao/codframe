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
 * Desc 文件设置
 *
 * @author sourcod
 * @version 1.0
 * @className SystemFileModel
 * @date 2019/3/7 7:35 PM
 */
public class SystemFileModel {

    /**
     * 是否保持文件名
     */
    private boolean keepName;

    /**
     * 类型
     */
    private String type;

    /**
     * 本地
     */
    private Local local;

    /**
     * 阿里云OSS
     */
    private Oss oss;

    /**
     * 七牛
     */
    private Qiniu qiniu;

    /**
     * 又拍云
     */
    private Upyun upyun;

    public boolean isKeepName() {
        return keepName;
    }

    public void setKeepName(boolean keepName) {
        this.keepName = keepName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public Oss getOss() {
        return oss;
    }

    public void setOss(Oss oss) {
        this.oss = oss;
    }

    public Qiniu getQiniu() {
        return qiniu;
    }

    public void setQiniu(Qiniu qiniu) {
        this.qiniu = qiniu;
    }

    public Upyun getUpyun() {
        return upyun;
    }

    public void setUpyun(Upyun upyun) {
        this.upyun = upyun;
    }

    /**
     * 本地
     */
    public static class Local{
        private String path;
        private String headUrl;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getHeadUrl() {
            return headUrl;
        }

        public void setHeadUrl(String headUrl) {
            this.headUrl = headUrl;
        }
    }

    /**
     * OSS
     */
    public static class Oss{

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

    /**
     * 七牛
     */
    public static class Qiniu{
        private String key;
        private String value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    /**
     * 又拍云
     */
    public static class Upyun{
        private String key;
        private String value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
