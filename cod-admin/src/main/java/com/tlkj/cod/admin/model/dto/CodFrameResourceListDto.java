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

/**
 * Desc 资源列表Dto
 *
 * @author sourcod
 * @version 1.0
 * @className CodFrameResourceListDto
 * @date 2018/12/13 9:05 PM
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CodFrameResourceListDto {

    /**
     * 主键
     */
    private String id;
}
