/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.core.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * Desc 权限Dto
 *
 * @author sourcod
 * @version 1.0
 * @className CodCorePermissionItemDto
 * @date 2018/12/13 9:06 PM
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CodCorePermissionItemDto {

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

}
