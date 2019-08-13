/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.core.service.impl;

import com.tlkj.cod.common.CodCommonJson;
import com.tlkj.cod.core.model.entity.CodCoreLogDo;
import com.tlkj.cod.core.service.AspectSystemLogService;
import com.tlkj.cod.dao.jdbc.Updater;
import com.tlkj.cod.model.enums.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Desc 系统日志实现
 *
 * @author sourcod
 * @version 1.0
 * @className AspectSystemLogServiceImpl
 * @date 2019/4/9 4:41 PM
 */
@Service
public class AspectSystemLogServiceImpl implements AspectSystemLogService {

    @Autowired
    Updater updater;

    /**
     * 保存系统日志到数据库
     */
    @Override
    public StatusCode saveSystemLog(String methodName, String content, String ip, String username, String operationName, String operationType, Object[] params, Object[] values, Object o) {
        Updater.Update update = updater.insert(CodCoreLogDo.TABLE_NAME).setId();
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
