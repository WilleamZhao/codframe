package com.tlkj.cod.http.service.impl;

import com.tlkj.cod.http.model.CodHttpHeaderModel;
import com.tlkj.cod.http.model.CodHttpRequestParamsModelBase;
import com.tlkj.cod.http.model.CodHttpResponseModel;
import com.tlkj.cod.http.service.CodHttpSerivce;
import org.apache.http.NameValuePair;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Desc CodHttpService
 *
 * @author sourcod
 * @version 1.0
 * @className CodHttpServiceImpl
 * @date 2019/5/21 12:04 PM
 */
@Service
public class CodHttpServiceImpl implements CodHttpSerivce {

    @Override
    public CodHttpResponseModel httpGet(String url) {
        return null;
    }

    @Override
    public CodHttpResponseModel httpGet(String url, String charset) {
        return null;
    }

    @Override
    public CodHttpResponseModel httpGet(String url, CodHttpRequestParamsModelBase nvps) {
        return null;
    }

    @Override
    public CodHttpResponseModel httpGet(String url, List<NameValuePair> nvps) {
        return null;
    }

    @Override
    public CodHttpResponseModel httpGet(String url, List<NameValuePair> nvps, CodHttpHeaderModel headerModel) {
        return null;
    }

    @Override
    public CodHttpResponseModel httpGet(String url, CodHttpRequestParamsModelBase nvps, CodHttpHeaderModel headerModel) {
        return null;
    }

    @Override
    public CodHttpResponseModel httpGet(String url, CodHttpRequestParamsModelBase nvps, CodHttpHeaderModel headerModel, String charset) {
        return null;
    }

    @Override
    public CodHttpResponseModel httpPost(String url) {
        return null;
    }

    @Override
    public CodHttpResponseModel httpPost(String url, CodHttpRequestParamsModelBase nvps) {
        return null;
    }

    @Override
    public CodHttpResponseModel httpPost(String url, List<NameValuePair> nvps) {
        return null;
    }

    @Override
    public CodHttpResponseModel httpPost(String url, List<NameValuePair> nvps, CodHttpHeaderModel headerModel) {
        return null;
    }

    @Override
    public CodHttpResponseModel httpsGet(String url) {
        return null;
    }

    @Override
    public CodHttpResponseModel httpsGet(String url, String charset) {
        return null;
    }

    @Override
    public CodHttpResponseModel httpsGet(String url, CodHttpRequestParamsModelBase nvps) {
        return null;
    }

    @Override
    public CodHttpResponseModel httpsGet(String url, List<NameValuePair> nvps) {
        return null;
    }

    @Override
    public CodHttpResponseModel httpsGet(String url, CodHttpRequestParamsModelBase nvps, CodHttpHeaderModel headers) {
        return null;
    }

    @Override
    public CodHttpResponseModel httpsGet(String url, CodHttpRequestParamsModelBase nvps, CodHttpHeaderModel headers, String charset) {
        return null;
    }

    @Override
    public CodHttpResponseModel httpsPost(String url) {
        return null;
    }

    @Override
    public CodHttpResponseModel httpsPost(String url, String charset) {
        return null;
    }

    @Override
    public CodHttpResponseModel httpsPost(String url, CodHttpRequestParamsModelBase nvps) {
        return null;
    }

    @Override
    public CodHttpResponseModel httpsPost(String url, List<NameValuePair> nvps, CodHttpHeaderModel headers) {
        return null;
    }

    @Override
    public CodHttpResponseModel httpsPost(String url, CodHttpRequestParamsModelBase nvps, CodHttpHeaderModel headers) {
        return null;
    }
}
