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

import com.tlkj.cod.service.system.FileService;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * Desc 七牛云 附件Service Impl
 *
 * @author sourcod
 * @version 1.0
 * @className UploadQiNiuServiceImpl
 * @date 2018/11/20 6:12 PM
 */
@Service("codFileQiNiuImpl")
public class FileQiNiuServiceImpl implements FileService {

    @Override
    public boolean uploadFile(String url, String fileName, InputStream inputStream) {
        return false;
    }

    @Override
    public boolean deleteFile(String url, String fileName) {
        return false;
    }

    @Override
    public InputStream readFile(String url, String fileName) {
        return null;
    }

    @Override
    public String getSupportType(){
        return "qiniu";
    }
}
