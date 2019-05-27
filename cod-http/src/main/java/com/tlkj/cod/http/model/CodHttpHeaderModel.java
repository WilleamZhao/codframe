package com.tlkj.cod.http.model;

/**
 * Desc Http 请求头
 *
 * @author sourcod
 * @version 1.0
 * @className CodHttpHeaderModel
 * @date 2019/4/30 6:59 PM
 */
public abstract class CodHttpHeaderModel {

    private String userAgent;
    private String Referer;
    private String AccessToken;

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getReferer() {
        return Referer;
    }

    public void setReferer(String referer) {
        Referer = referer;
    }

    public String getAccessToken() {
        return AccessToken;
    }

    public void setAccessToken(String accessToken) {
        AccessToken = accessToken;
    }
}
