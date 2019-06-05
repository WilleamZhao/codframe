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

import com.tlkj.cod.log.service.CodLogService;
import org.springframework.stereotype.Service;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className CodLogbackServiceImpl
 * @date 2018/11/6 下午2:14
 */
@Service("logbackImpl")
public class CodLogbackServiceImpl implements CodLogService {

    @Override
    public String getSupportType() {
        return "logback";
    }

    @Override
    public void trace(String msg, Object... objects) {

    }

    @Override
    public void debug(String msg, Object... objects) {

    }

    @Override
    public void info(String msg, Object... objects) {

    }

    @Override
    public void error(String msg, Object... objects) {

    }

    @Override
    public void warn(String msg, Object... objects) {

    }
}
