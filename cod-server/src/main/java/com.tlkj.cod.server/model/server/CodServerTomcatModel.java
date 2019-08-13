/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.server.model.server;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className CodServerTomcatModel
 * @date 2019/4/10 5:04 PM
 */
public class CodServerTomcatModel extends CodServerModel {

    private String protocol = "org.apache.coyote.http11.Http11NioProtocol";

    private String baseDir = ".";

    private String contextPath = "";

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    @Override
    public String getBaseDir() {
        return baseDir;
    }

    @Override
    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }
}
