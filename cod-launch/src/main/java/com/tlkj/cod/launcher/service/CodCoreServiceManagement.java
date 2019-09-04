package com.tlkj.cod.launcher.service;

/**
 * Desc codframe service 管理
 *
 * @author sourcod
 * @version 1.0
 * @className CodCoreServiceManagement
 * @date 2019/6/18 11:16 AM
 */
public interface CodCoreServiceManagement {

    default String support(String type){
        return "codServerJetty";
    }
}
