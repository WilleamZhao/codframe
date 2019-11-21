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

import com.tlkj.cod.launcher.model.CodModuleServerModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;


/**
 * Desc cod 启动 model
 *
 * @author sourcod
 * @version 1.0
 * @className CodServerModel
 * @date 2019/4/9 7:21 PM
 */
@Getter
@Setter
@Component
public class CodServerConfig extends CodModuleServerModel implements Serializable {

    private static final long serialVersionUID = 2970098358306956758L;

    /*private static volatile CodServerModel instance;

    *//**
     * 线程安全
     *//*
    public static CodServerModel getInstance() {
        if (instance == null) {
            synchronized (CodServerModel.class) {
                if (instance == null) {
                    instance = new CodServerModel();
                }
            }
        }
        return instance;
    }
    */

    /**
     * 端口号
     */
    @Value("${cod.server.port:9999}")
    private int port;

    /**
     * ip地址
     */
    @Value("${cod.server.ip:9999}")
    private String ip;

    /**
     * host
     */
    @Value("${cod.server.host:localhost}")
    private String host;

    /**
     * 环境
     * 0: 开发环境
     * 1: 测试环境
     * 2: 正式环境
     */
    @Value("${cod.server.env:0}")
    private String env = "0";

    /**
     * 编码
     */
    @Value("${cod.server.encode:UTF-8}")
    private String encode = "UTF-8";

    /**
     * 服务
     * 1: codServerJetty
     * 2: codServerTomcat
     * 3: codServerResin
     */
    @Value("${cod.server.server:codServerJetty}")
    private String server = "codServerJetty";

    /**
     * 项目名称
     */
    @Value("${cod.server.projectName:codframe}")
    private String projectName;

    /**
     * tomcat存储自身信息的目录，比如日志等信息，根目录
     * 根目录
     */
    private String baseDir = ".";

}
