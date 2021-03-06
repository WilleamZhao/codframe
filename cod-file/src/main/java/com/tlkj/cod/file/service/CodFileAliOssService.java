/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.file.service;

import com.tlkj.cod.file.model.CodFileModel;

import java.io.InputStream;

/**
 * Desc cod-file 阿里云 OSS 文件管理
 *
 * @author sourcod
 * @version 1.0
 * @className CodFileAliOssService
 * @date 2019/11/4 2:50 PM
 */
public interface CodFileAliOssService extends CodFileManager {

    CodFileModel upload(InputStream inputStream, String fileName, String... prefix);
}
