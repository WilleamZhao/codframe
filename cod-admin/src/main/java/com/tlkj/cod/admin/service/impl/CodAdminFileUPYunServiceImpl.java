/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.admin.service.impl;

import com.tlkj.cod.admin.service.CodAdminFileService;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * Desc 又拍云 文件Service Impl
 *
 * @author sourcod
 * @version 1.0
 * @className AttachmentUPyunServiceImpl
 * @date 2018/11/20 6:13 PM
 */
@Service("codFileUPYunImpl")
public class CodAdminFileUPYunServiceImpl implements CodAdminFileService {

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
        return "upyun";
    }
}
