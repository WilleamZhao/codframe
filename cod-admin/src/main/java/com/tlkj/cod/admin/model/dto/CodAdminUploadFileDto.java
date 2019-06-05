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
 * Desc 上传附件 Dto
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminUploadFileDto
 * @date 2018/11/20 7:01 PM
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CodAdminUploadFileDto {

    private String id;
    private String url;
    private String uploadTime;
    private String fullUrl;
    private String ip;
    private String fileSize;
    private String extName;
    private String unit;



}
