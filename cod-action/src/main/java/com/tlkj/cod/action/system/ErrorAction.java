/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.action.system;

import com.tlkj.cod.model.common.Response;
import com.tlkj.cod.service.system.LogService;
import com.tlkj.cod.model.common.GeneralResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Desc 异常测试类
 *
 * @author sourcod
 * @version 1.0
 * @className ErrorAction
 * @date 2018/12/25 1:14 PM
 */
@RestController
@RequestMapping("error")
public class ErrorAction extends GeneralResponse {
    private static Logger logger = LoggerFactory.getLogger(ErrorAction.class);

    @Autowired
    @Qualifier("clogImpl")
    LogService logService;

    @RequestMapping(value = "nullPointer", method = {RequestMethod.GET})
    public Response nullPointer(HttpServletRequest request){
        try {
            throw new NullPointerException();
        } catch (NullPointerException e){
            e.printStackTrace();
            logService.error("错误{}", e.getMessage());
            // return super.fail();
        }
        return super.success();
    }

    @RequestMapping(value = "numberFormat", method = {RequestMethod.GET})
    public Response numberFormat(HttpServletRequest request){
        try {
            throw new NumberFormatException();
        } catch (NumberFormatException e){
            e.printStackTrace();
            logService.error("错误{}", e.getMessage());
            // return super.fail();
        }
        return super.success();
    }

    @RequestMapping(value = "classNotFound", method = {RequestMethod.GET})
    public Response classNotFound(HttpServletRequest request) {
        try {
            throw new ClassNotFoundException();
        } catch (ClassNotFoundException e){
            //e.printStackTrace();
            logService.error("错误{}", e.getMessage(), e);
            // return super.fail();
        }
        return super.success();
    }

    public static void main(String[] args) {
        logger.info("asdasd");
    }
}
