/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

/**
 * JSON工具类
 * @author sourcod
 * @date 2018-10-29 19:11:49
 */
public final class CodCommonJson {

	private static ObjectMapper objectMapper = new ObjectMapper();

	@SuppressWarnings("serial")
	public static class CodecException extends RuntimeException {
		public CodecException(Throwable cause) {
			super(cause);
		}
	}

	private CodCommonJson() {

	}

	public static String dump(Object object){
		return dump(object, JsonInclude.Include.ALWAYS);
	}

	public static String dump(Object obj, JsonInclude.Include include) throws CodecException {
		StringWriter writer = new StringWriter();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			objectMapper.setSerializationInclusion(include);
			objectMapper.writeValue(writer, obj);
		} catch (Exception e) {
			throw new CodecException(e);
		}
		return writer.toString();
	}

	public static <T> T load(String json, Class<T> type) throws CodecException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			return mapper.readValue(json, type);
		} catch (Exception e) {
			throw new CodecException(e);
		}
	}

	public static <T> T load(String json, TypeReference type) throws CodecException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			return mapper.readValue(json, type);
		} catch (Exception e) {
			throw new CodecException(e);
		}
	}

	public static <T> T StringToJson(String json, Class<T> type){
		Gson gson = new Gson();
		return gson.fromJson(json,type);
	}

	public static List  loadArray(String json) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		List jsonArray = mapper.readValue(json, List.class);
		return jsonArray;
	}




}
