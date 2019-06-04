package com.tlkj.cod.admin.model.enums;

/**
 * Desc 系统设置Code
 *
 * @author sourcod
 * @version 1.0
 * @className SystemCodeSet
 * @date 2019/3/7 6:28 PM
 */
public enum SystemCodeSet {

    attachment("attachment", "附件设置"),
    log("log", "日志设置"),
    cache("cache", "缓存设置"),
    allow("allow_disable", "是否启用黑白名称"),
    ip("allow_ip", "允许登录ip设置"),
    webFrontUrl("web_front_url", "网站前端地址"),
    webName("web_name", "前端网站名称"),
    webAdminUrl("web_admin_url", "网站后台地址"),
    webAdminName("web_admin_name", "网站后台名称");

    private String code;
    private String name;

    SystemCodeSet(String code, String name){
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
