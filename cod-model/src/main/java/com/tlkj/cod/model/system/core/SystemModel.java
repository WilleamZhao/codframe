/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.model.system.core;


/**
 * Desc 系统设置
 *
 * @author sourcod
 * @version 1.0
 * @className System
 * @date 2018/11/3 上午12:26
 */
public class SystemModel {

    private static volatile SystemModel instance;

    /**
     * 初始化默认设置
     */
    private SystemModel() {
        codframe = new SystemSetModel();
        log = new SystemSetLog();
        allowDisable = new SystemSetAllowDisable();
        cache = new SystemSetCacheModel();
        file = new SystemFileModel();
    }

    public void setInstance(SystemModel instance) {
        SystemModel.instance = instance;
    }

    /**
     * 线程安全
     */
    public static SystemModel getInstance() {
        if (instance == null) {
            synchronized (SystemModel.class) {
                if (instance == null) {
                    instance = new SystemModel();
                }
            }
        }
        return instance;
    }

    /**
     * 启动设置
     */
    private SystemSetModel codframe;

    /**
     * 日志设置
     */
    private SystemSetLog log;

    /**
     * 黑白名单设置
     */
    private SystemSetAllowDisable allowDisable;

    /**
     * 缓存设置
     */
    private SystemSetCacheModel cache;

    /**
     * 文件设置
     */
    private SystemFileModel file;

    public SystemSetModel getCodframe() {
        return codframe;
    }

    public void setCodframe(SystemSetModel codframe) {
        this.codframe = codframe;
    }

    public SystemSetLog getLog() {
        return log;
    }

    public void setLog(SystemSetLog log) {
        this.log = log;
    }

    public SystemSetAllowDisable getAllowDisable() {
        return allowDisable;
    }

    public void setAllowDisable(SystemSetAllowDisable allowDisable) {
        this.allowDisable = allowDisable;
    }

    public SystemSetCacheModel getCache() {
        return cache;
    }

    public void setCache(SystemSetCacheModel cache) {
        this.cache = cache;
    }

    public SystemFileModel getFile() {
        return file;
    }

    public void setFile(SystemFileModel file) {
        this.file = file;
    }
}
