/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.log.service.impl;

import com.tlkj.cod.log.service.CodLogService;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

/**
 * Desc 阿里云LogService impl
 *
 * @author sourcod
 * @version 1.0
 * @className CodLogAliServiceImpl
 * @date 2018/11/6 下午2:14
 */
@Service("aliyunLogImpl")
public class CodLogAliServiceImpl implements CodLogService {

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
