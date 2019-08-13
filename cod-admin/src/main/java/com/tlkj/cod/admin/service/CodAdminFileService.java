/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.admin.service;

import java.io.InputStream;

/**
 * Desc 上传文件Service
 *
 * @author sourcod
 * @version 1.0
 * @className UploadFileService
 * @date 2018/11/20 6:54 PM
 */
public interface CodAdminFileService {

    /**
     * 上传文件接口
     * @param url    文件名称前缀
     * @param fileName    文件名称
     * @param inputStream 文件流
     * @return
     */
    boolean uploadFile(String url, String fileName, InputStream inputStream);

    /**
     * 删除文件接口
     * @param fileName    文件名
     * @return
     */
    boolean deleteFile(String url, String fileName);

    /**
     * 读取文件
     * @param fileName
     * @return
     */
    InputStream readFile(String url, String fileName);

    /**
     * 获取上传文件的支持
     * @return 支持类型
     */
    default String getSupportType(){
        return "local";
    }

}
