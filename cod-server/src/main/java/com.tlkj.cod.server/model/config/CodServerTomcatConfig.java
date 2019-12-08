/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.server.model.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Desc cod server tomcat 配置
 *
 * @author sourcod
 * @version 1.0
 * @className CodServerTomcatModel
 * @date 2019/4/10 5:04 PM
 */
@Getter
@Setter
@Component("codServerTomcatModel")
public class CodServerTomcatConfig extends CodServerConfig {

    private static final long serialVersionUID = -3459632559913123565L;


    /**
     * tomcat 工作模式
     * apr  org.apache.coyote.http11.Http11AprProtocol
     * nio  org.apache.coyote.http11.Http11NioProtocol
     * nio2 org.apache.coyote.http11.Http11Nio2Protocol
     * bio  org.apache.coyote.http11.Http11Protocol
     */
    private String protocol = "org.apache.coyote.http11.Http11NioProtocol";

    /**
     * tomcat存储自身信息的目录，比如日志等信息，根目录
     * 根目录
     */
    private String baseDir = ".";

    /**
     * 项目文件名
     */
    private String contextPath = "";

}
