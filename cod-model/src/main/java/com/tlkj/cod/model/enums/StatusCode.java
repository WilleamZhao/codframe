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

import com.tlkj.cod.common.CodCommonDynamicEnum;

import java.util.Arrays;

/**
 * Desc 返回状态码
 *
 * 1xxx 接口调用成功, 后台处理失败
 * 0xxx 接口调用失败
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
     * 没有数据
     */
    DATA_NULL_CODE("1015", "没有数据", "没有数据"),

    /**
     * 字典代码重复
     */
    DICT_CODE_DUPLICATION("1016", "字典代码重复", "字典代码重复"),

    /**
     * 参数错误
     */
    PARAM_ERROR_CODE("0014", "参数错误", "参数错误"),

    /**
     * token 错误
     */
    TOKEN_ERROR_CODE("0017", "token错误", "token不能为空"),

    /**
     * JWT 错误
     */
    JWT_ERROR_CODE("0017", "参数错误", "参数错误")
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

    /**
     * 动态添加状态码
     * @param enumName   枚举名称
     * @param statusCode 状态码
     * @param statusName 状态名称
     * @param statusDesc 状态描述
     */
    public static void addStatusCode(String enumName, String statusCode, String statusName, String statusDesc){
        CodCommonDynamicEnum.addEnum(StatusCode.class, enumName, new Class<?>[]{String.class, String.class, String.class}, new Object[]{statusCode, statusName, statusDesc});
    }

    /**
     * 获取状态码 ( 可获取动态添加枚举 )
     * @param enumName 枚举名称
     * @return
     */
    public static StatusCode getStatusCode(String enumName){
        return CodCommonDynamicEnum.getStatusCode(enumName, StatusCode.class);
    }


    public static void main(String[] args) {
        // 动态添加枚举
        StatusCode.addStatusCode("ERROR_CODE", "a", "b", "c");
        StatusCode.addStatusCode("ERROR_CODE", "a", "b", "c");
        StatusCode.addStatusCode("ERROR_CODE", "a", "b", "c");

        System.out.println(Arrays.deepToString(StatusCode.values()));
        // 获取枚举
        System.out.println(StatusCode.getStatusCode("ERROR_CODE").statusCode);
        System.out.println(StatusCode.getStatusCode("ERROR_CODE").statusDesc);
        System.out.println(StatusCode.getStatusCode("ERROR_CODE").statusName);

    }
}
