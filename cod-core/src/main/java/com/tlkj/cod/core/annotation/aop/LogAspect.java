/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.core.annotation.aop;

import com.tlkj.cod.core.annotation.Log;
import com.tlkj.cod.core.service.AspectLogService;
import com.tlkj.cod.core.spring.SpringContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;

/**
 * 日志切面
 */
@Aspect
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Component
public class LogAspect {

    private static String BeanType = "clog";

    /**
     * 切 service和facade层
     */
    @Pointcut("@annotation(com.tlkj.cod.core.annotation.Log) && (execution(* com.tlkj.cod.service..* (..)) || execution(* com.tlkj.cod.facade..* (..)))")
    public void pointCut() {}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;

        Log log = methodSignature.getMethod().getAnnotation(Log.class);

        // 获取参数
        String operationName = log.name();
        String operationType = StringUtils.isBlank(log.type()) ? BeanType : log.type();

        AspectLogService logService =  (AspectLogService) SpringContextUtil.getBean( "aspectLogServiceImpl");
        //格式化开始时间
        String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());

        String[] params = methodSignature.getParameterNames();
        Object[] args = joinPoint.getArgs();
        logService.saveLog(operationType,"进入" + operationName + "方法, 开始时间: " + startTime + "; 参数: ", params, args);

        Object o = joinPoint.proceed();

        /*
         * DONE 解决返回值过多问题
         */
        if (log.isBack()){
            //格式化结束时间
            String endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
            logService.saveLog(operationType, "退出" + operationName + "方法, 结束时间: " + endTime + "; 返回信息: ", o);
        }
        return o;


    }

    /**
     * 发放调用前触发
     * @param joinPoint
     */
    @Before("pointCut()")
    public void before(JoinPoint joinPoint){

    }

    /**
     * 方法调用后触发
     * @param joinPoint
     */
    @After("pointCut()")
    public void after(JoinPoint joinPoint) {

    }

    /**
     * @Description: 获取request
     * @author fei.lei
     * @date 2016年11月23日 下午5:10
     * @param
     * @return HttpServletRequest
     */
    public HttpServletRequest getHttpServletRequest(){
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes)ra;
        return sra.getRequest();
    }
}
