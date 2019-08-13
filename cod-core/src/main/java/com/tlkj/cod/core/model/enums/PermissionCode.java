/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.core.model.enums;

/**
 * Desc 权限Code
 *
 * @author sourcod
 * @version 1.0
 * @className PermissionCode
 * @date 2018/12/13 9:53 AM
 */
public enum PermissionCode {

    /**
     * 基础权限
     */
    SELECT((int) Math.pow(2, 0), "查询", 0),
    INSERT((int)Math.pow(2, 1), "新增", 0),
    UPDATE((int)Math.pow(2, 2), "修改", 0),
    DELETE((int)Math.pow(2, 3), "删除", 0),
    IMPORT((int)Math.pow(2, 4), "导入", 0),
    EXPORT((int)Math.pow(2, 5), "导出", 0),
    UPLOAD((int)Math.pow(2, 6), "上传", 0),
    DOWNLOAD((int)Math.pow(2, 7), "下载", 0),
    PREVIEW((int)Math.pow(2, 8), "预览", 0),

    /**
     * 扩展权限
     */
    SEL(SELECT.permissionNum, "查询", 1),
    QUERY(SELECT.permissionNum, "查询", 1),
    ADD(INSERT.permissionNum, "新增", 1),
    SAVE(INSERT.permissionNum + UPDATE.permissionNum, "保存", 1)
    ;

    /**
     * 权限数
     */
    private int permissionNum;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 权限类型
     * 0: 基础权限
     * 1: 扩展权限
     */
    private int permissionType;

    PermissionCode(int permissionNum, String permissionName, int permissionType) {
        this.permissionNum = permissionNum;
        this.permissionName = permissionName;
        this.permissionType = permissionType;
    }

    public int getPermissionNum() {
        return permissionNum;
    }

    public String getPermissionName(){
        return permissionName;
    }

    public int getPermissionType() {
        return permissionType;
    }

    public static void main(String[] args) {
        System.out.println(PermissionCode.valueOf("SAVE1").permissionNum);
    }
}
