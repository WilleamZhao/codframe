/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.admin.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Desc 权限dto
 *
 * @author sourcod
 * @version 1.0
 * @className CodFrameRoleTreeDto
 * @date 2018/12/9 5:36 PM
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CodFramePermissionTreeDto {

    /**
     * 权限id
     */
    private String id;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限code
     */
    private String code;

    /**
     * 权限描述
     */
    private String desc;

    /**
     * 状态
     */
    private String state;

    /**
     * 权限数
     */
    private int num;

    /**
     * 权限
     */
    private List<CodFramePermissionItemDto> permission;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<CodFramePermissionItemDto> getPermission() {
        return permission;
    }

    public void setPermission(List<CodFramePermissionItemDto> permission) {
        this.permission = permission;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
