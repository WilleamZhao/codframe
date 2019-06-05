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
 * Desc 系统日志Dto
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminSystemLogDto
 * @date 2018/12/7 12:51 PM
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CodAdminSystemLogDto {

    private String id;
    private String username;
    private String methodName;
    private String operationName;
    private String operationType;
    private String content;
    private String ip;
    private String params;
    private String results;
    private String createTime;


}
