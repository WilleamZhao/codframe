/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.log.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Desc 日志消息封装model
 * 日志消息体
 * @author sourcod
 * @version 1.0
 * @className LogMessageModel
 * @date 2018/12/3 4:25 PM
 */
public class LogMessageModel implements Serializable {

    private static final long serialVersionUID = 8674434550747529862L;

    public LogMessageModel() {

    }

    public LogMessageModel(String id, String fileName, String className, String methodName, int line, String level, String time, String msg) {
        this.id = id;
        this.fileName = fileName;
        this.className = className;
        this.methodName = methodName;
        this.line = line;
        this.level = level;
        this.time = time;
        this.msg = msg;
    }

    /**
     * 消息id
     */
    @JsonProperty("id")
    private String id;

    /**
     * 文件名
     */
    @JsonProperty("fn")
    private String fileName;

    /**
     * 类名
     */
    @JsonProperty("cn")
    private String className;

    /**
     * 方法名
     */
    @JsonProperty("mn")
    private String methodName;

    /**
     * 行号
     */
    @JsonProperty("n")
    private int line;

    /**
     * 日志等级
     */
    @JsonProperty("l")
    private String level;

    /**
     * 时间
     */
    @JsonProperty("t")
    private String time;

    /**
     * 消息内容
     */
    @JsonProperty("m")
    private String msg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
