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

import com.tlkj.cod.common.CodCommonJson;
import com.tlkj.cod.log.service.CodLogAspectService;
import com.tlkj.cod.model.common.SystemResponse;
import com.tlkj.cod.model.enums.StatusCode;
import com.tlkj.cod.log.service.CodLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Desc Log4jService impl
 *
 * @author sourcod
 * @version 1.0
 * @className CodLog4JServiceImpl
 * @date 2018/11/6 下午2:13
 */
@Service("aspectLogServiceImpl")
public class CodLogAspectServiceImpl implements CodLogAspectService {

    @Autowired
    private List<CodLogService> codLogServices;

    private CodLogService getLog(String type){
        for (CodLogService f : codLogServices){
            if (f.getSupportType().equals(type)){
                return f;
            }
        }
        return null;
    }

    /**
     * 注解打印方法开始日志
     * @param name     接口名称
     * @param params   参数名称
     * @param values   参数值
     * @return
     */
    @Override
    public StatusCode saveLog(String type, String name, Object[] params, Object[] values) {
        CodLogService codLogService = getLog(type);
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(covert(params, values));
        if (codLogService == null){
            System.out.println(sb.toString());
            return StatusCode.FAIL_CODE;
        }
        codLogService.debug(sb.toString());
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
        CodLogService codLogService = getLog(type);
        String str = o != null ? o.getClass().isPrimitive() ? o.toString() : CodCommonJson.dump(o) : "";
        if (codLogService == null){
            System.out.println(str);
            return StatusCode.FAIL_CODE;
        }
        codLogService.info(str);
        return StatusCode.SUCCESS_CODE;
    }

    /**
     * 打印异常日志
     * @param type    打印日志类型(clog log4j aliyun logback)
     * @param name    接口名称
     * @param e       异常信息
     * @return
     */
    @Override
    public SystemResponse saveError(String type, String name, Throwable e) {
        CodLogService codLogService = getLog(type);
        String content = name + "方法异常，";
        if (codLogService == null){
            System.out.println(content);
            return new SystemResponse().fail();
        }
        codLogService.error(name + "方法异常，", e);
        return new SystemResponse().success();
    }

    /**
     * 参数值转换方法
     * @param params 参数
     * @param values 值
     * @return 转换后字符串
     */
    private String covert(Object[] params, Object[] values){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < params.length; i++){
            sb.append(params[i]).append("=").append(values[i]).append("; ");
        }
        return sb.toString();
    }

}
