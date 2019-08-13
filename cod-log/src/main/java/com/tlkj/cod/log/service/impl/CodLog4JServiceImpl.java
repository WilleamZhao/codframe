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

import com.tlkj.cod.common.CodCommonDate;
import com.tlkj.cod.common.CodCommonJson;
import com.tlkj.cod.common.CodCommonUUID;
import com.tlkj.cod.log.service.CodLogService;
import com.tlkj.cod.log.service.model.CodLogMessageModel;
import org.apache.log4j.Logger;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.stereotype.Service;


/**
 * Desc Log4jService impl
 * 默认info 级别
 * @author sourcod
 * @version 1.0
 * @className CodLog4JServiceImpl
 * @date 2018/11/6 下午2:13
 */
@Service("log4jImpl")
public class CodLog4JServiceImpl implements CodLogService {

    @Override
    public String getSupportType() {
        return "log4j";
    }

    @Override
    public void trace(String msg, Object... objects) {
        output("trace", msg, objects);
    }

    @Override
    public void debug(String msg, Object... objects) {
        output("debug", msg, objects);
    }

    @Override
    public void info(String msg, Object... objects) {
        output("info", msg, objects);
    }

    @Override
    public void error(String msg, Object... objects) {
        output("error", msg, objects);
    }

    @Override
    public void warn(String msg, Object... objects) {
        output("warn", msg, objects);
    }

    /**
     * 输出
     */
    private void output(String level, String msg, Object... objects){
        if (objects.length != 0){
            msg = MessageFormatter.arrayFormat(msg, objects.clone()).getMessage();
        }
        StackTraceElement[] elements = new Throwable().getStackTrace();
        Logger logger = Logger.getLogger(elements[2].getClassName());

        // 日志消息体
        CodLogMessageModel model = new CodLogMessageModel(CodCommonUUID.getUUID(),
                elements[2].getFileName(), elements[2].getClassName(),
                elements[2].getMethodName(), elements[2].getLineNumber(), level,
                CodCommonDate.getDate("yyyy-MM-dd HH:mm:ss,SSS"), msg);
        String log = CodCommonJson.dump(model);
        logger.info(log);
    }
}
