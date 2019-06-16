/*
 * Copyright (c) 2019.
 * sourcod.com
 * All rights reserved
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.file.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 文件model
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
     * 上传后文件相对路径
     */
    private String url;

    /**
     * 扩展名
     */
    private String extName;

    /**
     * 文件大小
     */
    private String fileSize;

    /**
     * 文件大小单位
     */
    private String fileUnit;

    /**
     * 文件类型
     */
    private String fileType;
}
