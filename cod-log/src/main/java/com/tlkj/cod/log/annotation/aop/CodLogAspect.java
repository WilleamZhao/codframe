/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.log.annotation.aop;

import com.tlkj.cod.log.annotation.CodLog;
import com.tlkj.cod.log.service.CodLogAspectService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
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
public class CodLogAspect {

    private static String BeanType = "clog";

    /*@Autowired
    @Lazy
    CodLogAspectService logService;*/

    @Autowired
    ApplicationContext applicationContext;

    // service和facade层
    // @Pointcut("@annotation(com.tlkj.cod.log.annotation.CodLog) && (execution(* com.tlkj.cod.service..* (..)) || execution(* com.tlkj.cod.facade..* (..)))")

    /**
     * 切点
     */
    @Pointcut("@annotation(com.tlkj.cod.log.annotation.CodLog)")
    public void pointCut() {}

    /**
     * 环绕触发
     */
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;

        CodLog codLog = methodSignature.getMethod().getAnnotation(CodLog.class);

        // 获取参数
        String operationName = codLog.name();
        String operationType = StringUtils.isBlank(codLog.type()) ? BeanType : codLog.type();

        // TODO
        WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();

        CodLogAspectService logService =  (CodLogAspectService) applicationContext.getBean( "aspectLogServiceImpl");

        //格式化开始时间
        String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());

        String[] params = methodSignature.getParameterNames();
        Object[] args = joinPoint.getArgs();
        logService.saveLog(operationType,"进入" + operationName + "方法, 开始时间: " + startTime + "; 参数: ", params, args);
        Object o = null;
        try {
            o = joinPoint.proceed();
        } catch (Throwable e){
            System.err.println(operationName + "方法异常");
            logService.saveError(operationType, operationName, e);
        }

        /*
         * DONE 解决返回值过多问题
         */
        if (codLog.isBack()){
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
