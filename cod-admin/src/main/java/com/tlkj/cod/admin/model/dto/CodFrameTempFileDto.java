/*
 *  Copyright (c) 2018-2019.
 *  Beijing sky blue technology co., LTD.
 *  All rights reserved
 *
 *  author: sourcod
 *  github: https://github.com/WilleamZhao
 *  site：http://codframe.sourcod.com
 */

package com.tlkj.cod.admin.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * Desc 通过FileAction上传的文件Do
 *
 * @author sourcod
 * @version 1.0
 * @className CodFrameTempFileDto
 * @date 2019/3/7 6:12 PM
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CodFrameTempFileDto {

    /**
     * 主键
     */
    private String id;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件相对路径
     */
    private String fileUrl;

    /**
     * 文件大小
     */
    private String fileSize;

    /**
     * 文件大小单位
     */
    private String fileUnit;

    /**
     * 扩展名
     */
    private String extName;

    /**
     * 文件地址代码
     */
    private String fileItemCode;

    /**
     * 上传时间
     */
    private String uploadTime;

    /**
     * 文件状态
     */
    private String status;

}
