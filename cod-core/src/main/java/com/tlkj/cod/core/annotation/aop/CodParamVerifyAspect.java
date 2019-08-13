/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.core.annotation.aop;

import com.tlkj.cod.common.CodCommonJson;
import com.tlkj.cod.core.annotation.CodParamVerify;
import com.tlkj.cod.log.service.CodLogService;
import com.tlkj.cod.model.enums.StatusCode;
import com.tlkj.cod.model.common.GeneralResponse;
import com.tlkj.cod.model.system.core.SystemModel;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 判断参数不为空切面
 * boolean false
 * int 0;
 * @author sourcod
 */
@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class CodParamVerifyAspect extends GeneralResponse{

	@Autowired
	CodLogService codLogService;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;


	/**
	 * 参数是否为空统一校验 Controller
	 * @param joinPoint
	 * @param codParamVerify
	 * @return
	 * @throws Throwable
	 */
	@Around("@annotation(com.tlkj.cod.core.annotation.CodParamVerify) && @annotation(codParamVerify) && !execution(int *.* (..)) && (execution(* com.tlkj.cod.action..* (..)) || execution(* com.tlkj.cod.api..* (..)))")
	public Object advice(ProceedingJoinPoint joinPoint, CodParamVerify codParamVerify) throws Throwable{
		String[] paraName = codParamVerify.parameter().split(",");
		String parameter = "";

		for (String para : paraName) {
			Object o = request.getAttribute(para.trim());
			parameter = o != null ? o.toString() : "";

			if (StringUtils.isEmpty(parameter)){
				parameter = request.getParameter(para.trim());
			}

			if ("debug".equals(SystemModel.getInstance().getLog().getLevel())){
				// System.out.println("参数:" + para + " = " + (StringUtils.isEmpty(parameter) ?  "null" : parameter));
				codLogService.info("参数: {} = {}", para, (StringUtils.isEmpty(parameter) ?  "null" : parameter));
			}

			if (StringUtils.isEmpty(parameter)) {
				setResponse();
				return null;
			}
		}
		return joinPoint.proceed();
	}

	/**
	 * 参数是否为空统一校验 Service
	 * @param joinPoint
	 * @param codParamVerify
	 * @return
	 * @throws Throwable
	 */
	@Around("@annotation(com.tlkj.cod.core.annotation.CodParamVerify) && @annotation(codParamVerify) && !execution(int *.* (..)) && execution(* com.tlkj.cod.service..* (..))")
	public Object advice1(ProceedingJoinPoint joinPoint, CodParamVerify codParamVerify) throws Throwable{
		String[] paraName = codParamVerify.parameter().split(",");

		Signature signature = joinPoint.getSignature();
		MethodSignature methodSignature = (MethodSignature)signature;

		String[] params = methodSignature.getParameterNames();
		Object[] args = joinPoint.getArgs();

		for (String para : paraName) {
			String parameter = para.trim();
			for (int i = 0; i < params.length; i++){
				String param = params[i];
				if (parameter.equals(param)) {
					Object obj = args[i];
					if (obj == null){
						setResponse();
						return null;
					}
					Class zlass = obj.getClass();
					switch (zlass.getName()){
						case "java.lang.Boolean":

							break;
						case "java.lang.Character":

							break;
						case "java.lang.Byte":

							break;
						case "java.lang.Short":

							break;
						case "java.lang.Integer":

							break;
						case "java.lang.Long":

							break;
						case "java.lang.Float":

							break;
						case "java.lang.Double":

							break;
						case "java.lava.Void":

							break;
						case "java.lang.String":
							if (StringUtils.isBlank((String.valueOf(obj)))){
								setResponse();
								return null;
							}
							break;
						default:

							break;
					}
				}
			}
		}
		return joinPoint.proceed();
	}

	private void setResponse() throws IOException {
		String result = CodCommonJson.dump(super.fail(StatusCode.PARAM_ERROR_CODE));
		response.setHeader("content-type", "text/html;charset=UTF-8");
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(result.getBytes("utf-8"));
		outputStream.flush();
		outputStream.close();
	}

}
