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
 * Desc 文件路径Dto
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminFileUrlDto
 * @date 2018/12/20 8:55 PM
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CodAdminFileUrlDto {

    /**
     * 类型
     */
    private String type;

    /**
     * 路径
     */
    private String url;

}
