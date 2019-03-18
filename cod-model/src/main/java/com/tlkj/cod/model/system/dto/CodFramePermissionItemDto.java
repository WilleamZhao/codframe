/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.model.system.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Desc 权限Dto
 *
 * @author sourcod
 * @version 1.0
 * @className CodFramePermissionItemDto
 * @date 2018/12/13 9:06 PM
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CodFramePermissionItemDto {

    /**
     * 权限代码
     */
    private String code;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限数
     */
    private int num;

    /**
     * 是否有
     */
    private boolean state;

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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
