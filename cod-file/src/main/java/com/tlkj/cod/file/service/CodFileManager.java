/*
 * Copyright (c) 2019.
 * sourcod.com
 * All rights reserved
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.file.service;

import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.file.model.CodFileInfo;
import com.tlkj.cod.file.model.CodFileModel;
import com.tlkj.cod.file.model.enums.CodFileTypeEnum;

import java.io.InputStream;
import java.util.List;

/**
 * cod-file service
 * @author sourcod
 */
public interface CodFileManager {

    /**
     * 支持类型
     * @return
     */
    CodFileTypeEnum support();

    /**
     * 上传文件
     * @param inputStream 文件
     * @param prefix      前缀; 统一放到attachment路径下的前缀,可传多个自动建立文件夹
     * @param fileName    文件名称
     * @return
     */
    CodFileModel uploadFile(InputStream inputStream, String fileName, String... prefix);

    /**
     * 删除文件
     * @param id 主键
     * @return
     */
    boolean delete(String id);

    /**
     * 读取文件
     * @param fileName
     * @return
     */
    InputStream readFile(String url, String fileName);

    /**
     * 文件列表
     * @return
     */
    Page<List<CodFileInfo>> listFile(CodFileTypeEnum typeEnum);
}
