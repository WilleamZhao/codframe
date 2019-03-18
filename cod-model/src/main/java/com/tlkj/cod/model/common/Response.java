/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.model.common;

import com.tlkj.cod.model.enums.StatusCode;

import java.io.Serializable;

/**
 * 公共返回类
 * @author zcj
 * @time 2016-10-20 09:53:10
 *
 * @param <T>
 */
public class Response<T> implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 6693762206950954399L;

	/**
	 * 详细状态码
	 */
	private String code;

	/**
	 * 状态描述
	 */
	private String msg;

	/**
	 * 状态名称
	 */
	private String name;

	/**
	 * 返回数据
	 */
	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Response(){

	}

	public Response(StatusCode statusCode){
		this.code = statusCode.getStatusCode();
		this.msg = statusCode.getStatusDesc();
		this.name = statusCode.getStatusName();
	}

	@Override
	public String toString() {
		return "{\"code\":\"" + code + "\", \"msg\": \"" + msg + "\", \"name\":\"" + name + "\"}";
	}
}