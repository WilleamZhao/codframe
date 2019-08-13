/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.model.enums;

/**
 * Desc 返回状态码
 *
 * @author sourcod
 * @version 1.0
 * @className StatusCode
 * @date 2018/7/28 下午1:47
 */
public enum StatusCode {

    /**
     * 操作成功
     */
    SUCCESS_CODE("1111", "操作成功", "操作成功"),

    /**
     * 操作失败
     */
    FAIL_CODE("0000", "操作失败", "操作失败"),

    /**
     * 登录失败
     */
    LOGIN_FAIL_CODE("1001", "登录失败", "暂未登陆"),

    /**
     * 没有权限
     */
    NO_PERMISSION_CODE("1002", "权限不足", "权限不足"),

    /**
     * ip禁用
     */
    IP_DISABLE_CODE("1003", "IP禁用", "当前IP禁止访问"),

    /**
     * 参数错误
     */
    PARAM_ERROR_CODE("1014", "参数错误", "参数错误"),

    /**
     * 没有数据
     */
    DATA_NULL_CODE("1015", "没有数据", "没有数据"),

    /**
     * 参数openid 为空
     */
    PARAM_OPENID_NULL_CODE("0001", "参数错误", "参数openid为空"),

    /**
     * 参数openid 为空
     */
    PARAM_UNIONID_NULL_CODE("0001", "参数错误", "参数unionid为空"),
    ;



    /**
     * 状态码
     */
    private String statusCode;

    /**
     * 错误名称
     */
    private String statusName;

    /**
     * 错误描述
     */
    private String statusDesc;

    StatusCode(String statusCode, String statusName, String statusDesc) {
        this.statusCode = statusCode;
        this.statusName = statusName;
        this.statusDesc = statusDesc;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public String getStatusDesc() {
        return statusDesc;
    }


    /**
     * 验证状态码是否返回成功
     * @param statusCode 状态码Enum
     * @return 是否成功
     */
    public static boolean verifyStatusCode(StatusCode statusCode){
        if (statusCode == null){
            return false;
        }
        if (StatusCode.SUCCESS_CODE.getStatusCode().equals(statusCode.getStatusCode())){
            return true;
        }
        return false;
    }
}
