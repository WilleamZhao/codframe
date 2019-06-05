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
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Desc 权限dto
 *
 * @author sourcod
 * @version 1.0
 * @className CodFrameRoleTreeDto
 * @date 2018/12/9 5:36 PM
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CodAdminPermissionTreeDto {

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
    private List<CodAdminPermissionItemDto> permission;

}
