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

/**
 * Desc 系统设置Dto
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminSetDto
 * @date 2018/11/27 8:02 PM
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CodAdminSetDto {

    private String id;
    private String setName;
    private String setCode;
    private String setValue;
    private String sort;
    private String userId;
    private String updateTime;

}
