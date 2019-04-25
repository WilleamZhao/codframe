/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.log.service.impl;

import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.log.service.LogService;
import com.tlkj.cod.model.system.core.SystemModel;
import com.tlkj.cod.model.system.entity.CodFrameSetDo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Desc 日志服务
 *
 * @author sourcod
 * @version 1.0
 * @className LogServiceImpl
 * @date 2019/2/13 10:24 AM
 */
@Primary
@Service
public class LogServiceImpl implements LogService {
    private static SystemModel model = SystemModel.getInstance();

    @Autowired
    @Lazy
    List<LogService> logServices;

    @Autowired
    Finder finder;

    private static String setValue = "";


    /**
     * 获取日志设置
     * 日志支持的类型 clog(默认), slf4j, logback, aliyunLog
     * 1. 默认获取配置文件设置
     * 2. 配置文件没有获取数据库设置
     * 3. 数据库没有默认采用clog
     * @return LogService 日志服务
     */
    public LogService getLog() {
        if (model.getLog() != null && StringUtils.isNotBlank(model.getLog().getType())){
            setValue = model.getLog().getType();
        } else {
            setValue = getSetValue("clog");
        }
        for (LogService f : logServices){
            if (f.getSupportType().equals(setValue)){
                return f;
            }
        }
        System.err.println("获取日志service错误");
        return null;
    }

    public LogServiceImpl(){

    }

    public LogServiceImpl(String name){
        setValue = name;
    }

    /**
     * 获取设置Value
     * @param setCode 设置代码
     * @return 设置值
     */
    private String getSetValue(String setCode) {
        CodFrameSetDo setDo = finder.from(CodFrameSetDo.TABLE_NAME).where("set_code", setCode).first(CodFrameSetDo.class);
        return setDo.getSet_value();
    }

    @Override
    public void trace(String msg, Object... objects) {
        getLog().trace(msg, objects);
    }

    @Override
    public void debug(String msg, Object... objects) {
        getLog().debug(msg, objects);
    }

    @Override
    public void info(String msg, Object... objects) {
        getLog().info(msg, objects);
    }

    @Override
    public void error(String msg, Object... objects) {
        getLog().error(msg, objects);
    }

    @Override
    public void warn(String msg, Object... objects) {
        getLog().warn(msg, objects);
    }
}
