package com.tlkj.cod.http.service;

import com.tlkj.cod.http.model.CodHttpHeaderModel;
import com.tlkj.cod.http.model.CodHttpRequestParamsModel;
import com.tlkj.cod.http.model.CodHttpResponseModel;
import org.apache.http.NameValuePair;

import java.util.List;

/**
 * Desc Http 请求 Service
 *
 * @author sourcod
 * @version 1.0
 * @className CodHttpSerivce
 * @date 2019/5/21 12:03 PM
 */
public interface CodHttpSerivce {

    CodHttpResponseModel httpGet(String url);

    CodHttpResponseModel httpGet(String url, String charset);

    CodHttpResponseModel httpGet(String url, CodHttpRequestParamsModel nvps);

    CodHttpResponseModel httpGet(String url, List<NameValuePair> nvps);

    CodHttpResponseModel httpGet(String url, List<NameValuePair> nvps, CodHttpHeaderModel headerModel);

    CodHttpResponseModel httpGet(String url, CodHttpRequestParamsModel nvps, CodHttpHeaderModel headerModel);

    CodHttpResponseModel httpGet(String url, CodHttpRequestParamsModel nvps, CodHttpHeaderModel headerModel, String charset);

    CodHttpResponseModel httpPost(String url);

    CodHttpResponseModel httpPost(String url, CodHttpRequestParamsModel nvps);

    CodHttpResponseModel httpPost(String url, List<NameValuePair> nvps);

    CodHttpResponseModel httpPost(String url, List<NameValuePair> nvps, CodHttpHeaderModel headerModel);

    /**
     * https GET
     */
    CodHttpResponseModel httpsGet(String url);

    CodHttpResponseModel httpsGet(String url, String charset);

    CodHttpResponseModel httpsGet(String url, CodHttpRequestParamsModel nvps);

    CodHttpResponseModel httpsGet(String url, List<NameValuePair> nvps);

    CodHttpResponseModel httpsGet(String url, CodHttpRequestParamsModel nvps, CodHttpHeaderModel headers);

    CodHttpResponseModel httpsGet(String url, CodHttpRequestParamsModel nvps, CodHttpHeaderModel headers, String charset);

    /**
     * https POST
     */
    CodHttpResponseModel httpsPost(String url);

    CodHttpResponseModel httpsPost(String url, String charset);

    CodHttpResponseModel httpsPost(String url, CodHttpRequestParamsModel nvps);

    CodHttpResponseModel httpsPost(String url, List<NameValuePair> nvps, CodHttpHeaderModel headers);

    CodHttpResponseModel httpsPost(String url, CodHttpRequestParamsModel nvps, CodHttpHeaderModel headers);


}
