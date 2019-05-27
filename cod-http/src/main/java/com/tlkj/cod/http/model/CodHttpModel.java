package com.tlkj.cod.http.model;

import java.util.Map;
import java.util.List;

/**
 * Desc Http 请求封装 Model
 *
 * @author sourcod
 * @version 1.0
 * @className CodHttpModel
 * @date 2019/4/30 6:57 PM
 */
public class CodHttpModel {

    private String url;
    private String type;
    private String Accept;
    private Map<String, String> header;
    private List<CodHttpRequestParamsModelBase> codHttpRequestParamsModelList;



}
