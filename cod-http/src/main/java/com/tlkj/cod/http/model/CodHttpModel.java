package com.tlkj.cod.http.model;

import lombok.Getter;
import lombok.Setter;

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
@Getter
@Setter
public class CodHttpModel {

    private String url;
    private String type;
    private String accept;
    private Map<String, String> header;
    private List<CodHttpRequestParamsModelBase> codHttpRequestParamsModelList;



}
