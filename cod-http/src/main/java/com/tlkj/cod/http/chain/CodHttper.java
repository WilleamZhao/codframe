/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.http.chain;

import com.tlkj.cod.common.CodCommonHttpClient;
import com.tlkj.cod.http.enums.CodHttpRequestTypeEnum;
import com.tlkj.cod.http.model.CodHttpResponseModel;
import org.apache.commons.collections.map.HashedMap;
import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Desc http 客户端链式封装
 * 基于httpclient
 *
 * @author sourcod
 * @version 1.0
 * @className CodHttper
 * @date 2019/8/29 11:18 PM
 */
@Component
public class CodHttper {

    private String url = "";

    public CodHttper(){

    }

    public CodHttper(String url) {
        this.url = url;
    }

    public Request visit(String url){
        return new Request(url);
    }

    /**
     * 请求
     */
    public static class Request {
        private boolean dev = false;
        private String url;
        private CodHttpRequestTypeEnum typeEnum;
        private Header[] headers;
        private StringEntity entity;
        private Map<String, Object> params = new HashMap<>();

        public Request(){

        }

        public Request(String url){
            this.url = url;
        }

        public Request setUrl(String url){
            this.url = url;
            return this;
        }

        public Request setType(CodHttpRequestTypeEnum type){
            this.typeEnum = type;
            return this;
        }

        /**
         * 设置参数
         * @param name  参数名
         * @param value 参数值
         */
        public Request set(String name, String value){
            params.put(name, value);
            return this;
        }


        /**
         * 开始请求
         * @param zlass 返回pojo
         */
        public <T> T get(Class<T> zlass){
            return (T) "";
        }

        /**
         * 获取主体
         */
        public <T> T getBody(Class<T> zlass){
            return (T) "";
        }

        /**
         * 默认返回
         */
        public CodHttpResponseModel getForObject(){
            CodHttpResponseModel codHttpResponseModel = new CodHttpResponseModel();
            return codHttpResponseModel;
        }

        public <T> List<T> getAll(Class<T> zlass){
            List<T> list = new ArrayList<>();
            return list;
        }

        public List<CodHttpResponseModel> getAll(){
            List<CodHttpResponseModel> list = new ArrayList<>();
            return list;
        }

        /**
         * 测试接口
         * @return 接口数据
         */
        public String test(){
            return "";
        }

    }

}
