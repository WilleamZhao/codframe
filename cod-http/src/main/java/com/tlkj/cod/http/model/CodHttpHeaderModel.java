package com.tlkj.cod.http.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Desc Http 请求头
 *
 * @author sourcod
 * @version 1.0
 * @className CodHttpHeaderModel
 * @date 2019/4/30 6:59 PM
 */
@Getter
@Setter
public abstract class CodHttpHeaderModel {

    private String userAgent;
    private String referer;
    private String accessToken;

}
