/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.service.system.impl;

import com.tlkj.cod.common.CodCommonIO;
import com.tlkj.cod.core.annotation.Log;
import com.tlkj.cod.service.system.FileService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Desc 本地文件 Service Impl
 *
 * @author sourcod
 * @version 1.0
 * @className AttachmentServiceImpl
 * @date 2018/11/7 下午10:37
 */
@Primary
@Service("codFileLocalImpl")
public class FileServiceImpl implements FileService {

    private static Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Log(name = "上传本地文件")
    @Override
    public boolean uploadFile(String url, String fileName, InputStream inputStream) {
        try {
            CodCommonIO.outputFile(url.endsWith(File.separator) ? url : url + File.separator, fileName, inputStream);
        } catch (IOException e) {
            logger.error("上传文件失败, {}", e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 删除文件
     * @param fileName    文件名
     * @return
     */
    @Log(name = "删除文件")
    @Override
    public boolean deleteFile(String url, String fileName) {
        try {
            FileUtils.forceDelete(new File(url + fileName));
        } catch (IOException e) {
            logger.error("删除文件失败, {}", e.getMessage());
            return false;
        }
        return true;
    }

    @Log(name = "读取文件")
    @Override
    public InputStream readFile(String url, String fileName){
        InputStream inputStream;
        try {
            inputStream = FileUtils.openInputStream(new File(url + fileName));
        } catch (IOException e) {
            logger.error("读取输入流, {}", e.getMessage());
            e.printStackTrace();
            return null;
        }
        return inputStream;
    }

    @Override
    public String getSupportType(){
        return "local";
    }
}
