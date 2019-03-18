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
 * Desc 上传附件 Dto
 *
 * @author sourcod
 * @version 1.0
 * @className CodFrameUploadFileDto
 * @date 2018/11/20 7:01 PM
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CodFrameUploadFileDto {

    private String id;
    private String url;
    private String uploadTime;
    private String fullUrl;
    private String ip;
    private String fileSize;
    private String extName;
    private String unit;



}
