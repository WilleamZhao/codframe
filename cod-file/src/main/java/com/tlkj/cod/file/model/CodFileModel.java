/*
 * Copyright (c) 2019.
 * sourcod.com
 * All rights reserved
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.file.model;

import com.tlkj.cod.file.model.enums.CodFileStatusCode;
import com.tlkj.cod.file.model.enums.CodFileTypeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 文件 model
 * @author sourcod
 * @date 2019年6月16日 15点40分
 */

@Getter
@Setter
public class CodFileModel {

    /**
     * 原文件名
     */
    private String fileName;

    /**
     * 上传后文件路径
     */
    private String path;

    /**
     * 文件大小
     */
    private long fileSize;

    /**
     * 上传文件类型
     */
    private CodFileTypeEnum fileType;

    /**
     * 状态码
     */
    private String code;

    /**
     * 第三方返回状态码
     */
    private CodFileStatusCode codFileStatusCode;
}
