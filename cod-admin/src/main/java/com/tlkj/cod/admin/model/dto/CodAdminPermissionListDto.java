/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.admin.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * Desc 权限列表Dto
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminPermissionListDto
 * @date 2018/12/13 1:04 PM
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CodAdminPermissionListDto {

    private String id;
    private String name;
    private String desc;
    private String code;
    private int permission;

}
