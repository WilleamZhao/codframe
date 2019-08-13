/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.model.system.core;

/**
 * Desc 日志设置Model
 *
 * @author sourcod
 * @version 1.0
 * @className SystemSetLog
 * @date 2018/11/6 下午5:34
 */
public class SystemSetLog {

    /**
     * 日志支持的类型 clog(默认), slf4j, logback, aliyunLog
     */
    private String type = "clog";

    /**
     * 日志路径必须以 / 结尾
     */
    private String href;

    /**
     * 按日期拆文件夹 不设置默认 yyyy/MM/dd 格式
     */
    private String pattern = "yyyy/MM/dd";

    /**
     * 是否按日志级别拆分文件夹
     */
    private String split;

    /**
     * 文件大小
     */
    private String size;

    /**
     * 日志等级
     */
    private String level = "info";

    /**
     * 是否打印控制台日志
     * 默认不打印
     */
    private boolean console = false;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getSplit() {
        return split;
    }

    public void setSplit(String split) {
        this.split = split;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public boolean isConsole() {
        return console;
    }

    public void setConsole(boolean console) {
        this.console = console;
    }
}
