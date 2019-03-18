/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.service.system.impl;

import com.tlkj.cod.service.system.LogService;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

/**
 * Desc 阿里云LogService impl
 *
 * @author sourcod
 * @version 1.0
 * @className AliyunLogServiceImpl
 * @date 2018/11/6 下午2:14
 */
@Service("aliyunLogImpl")
public class AliyunLogServiceImpl implements LogService {

    @Override
    public String getSupportType() {
        return "aliyunLog";
    }

    @Override
    public void trace(String msg, Object... objects) {

        String str = MessageFormat.format(msg, objects);
    }

    @Override
    public void debug(String msg, Object... objects) {
        String str = MessageFormat.format(msg, objects);
    }

    @Override
    public void info(String msg, Object... objects) {
        String str = MessageFormat.format(msg, objects);
    }

    @Override
    public void error(String msg, Object... objects) {
        String str = MessageFormat.format(msg, objects);
    }

    @Override
    public void warn(String msg, Object... objects) {
        String str = MessageFormat.format(msg, objects);
    }
}
