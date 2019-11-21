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
     * 根目录
     */
    private String baseDir = ".";

    /**
     * 项目文件名
     */
    private String contextPath = "";

}
