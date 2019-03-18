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

import com.tlkj.cod.common.CodCommonJson;
import com.tlkj.cod.core.service.AspectLogService;
import com.tlkj.cod.dao.jdbc.Updater;
import com.tlkj.cod.model.enums.StatusCode;
import com.tlkj.cod.model.system.entity.CodFrameLogDo;
import com.tlkj.cod.service.system.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Desc Log4jService impl
 *
 * @author sourcod
 * @version 1.0
 * @className Log4jServiceImpl
 * @date 2018/11/6 下午2:13
 */
@Service("aspectLogServiceImpl")
public class AspectLogServiceImpl implements AspectLogService {

    @Autowired
    private List<LogService> logServices;

    private LogService getLog(String type){
        for (LogService f : logServices){
            if (f.getSupportType().equals(type)){
                return f;
            }
        }
        return null;
    }

    @Autowired
    Updater updater;

    /**
     * 注解打印方法开始日志
     * @param name     接口名称
     * @param params   参数名称
     * @param values   参数值
     * @return
     */
    @Override
    public StatusCode saveLog(String type, String name, Object[] params, Object[] values) {
        LogService logService = getLog(type);
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(covert(params, values));
        assert logService != null;
        logService.debug(sb.toString());
        return StatusCode.SUCCESS_CODE;
    }

    /**
     * 打印方法结束日志
     * @param type    打印日志类型(clog log4j aliyun logback)
     * @param content 内容
     * @param o       返回对象
     * @return
     */
    @Override
    public StatusCode saveLog(String type, String content, Object o) {
        LogService logService = getLog(type);
        String str = o != null ? o.getClass().isPrimitive() ? o.toString() : CodCommonJson.dump(o) : "";
        assert logService != null;
        logService.info(str);
        return StatusCode.SUCCESS_CODE;
    }

    /**
     * 保存系统日志到数据库
     */
    @Override
    public StatusCode saveSystemLog(String methodName, String content, String ip, String username, String operationName, String operationType, Object[] params, Object[] values, Object o) {
        Updater.Update update = updater.insert(CodFrameLogDo.TABLE_NAME).setId();
        update.set("method_name", methodName);
        update.set("content", content);
        update.set("ip", ip);
        update.set("username", username);
        update.set("operation_name", operationName);
        update.set("operation_type", operationType);
        update.set("params", covert(params, values));
        update.set("results", CodCommonJson.dump(o));
        if (update.update() == 1){
            return StatusCode.SUCCESS_CODE;
        }
        return StatusCode.FAIL_CODE;
    }

    private String covert(Object[] params, Object[] values){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < params.length; i++){
            sb.append(params[i]).append("=").append(values[i]).append("; ");
        }
        return sb.toString();
    }

}
