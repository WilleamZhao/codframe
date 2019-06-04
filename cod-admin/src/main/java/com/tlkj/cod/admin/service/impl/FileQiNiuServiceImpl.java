/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.admin.service.impl;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.tlkj.cod.admin.service.FileService;
import com.tlkj.cod.common.CodCommonDate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Random;

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


    /**
     * 上传到七牛接口
     *
     * @param url         上传目录路径
     * @param fileName    文件名称
     * @param inputStream 文件流
     * @return
     */
    @Override
    public boolean uploadFile(String url, String fileName, InputStream inputStream) {
        String accessKey = "dutDJ6pDm0uCoPZuWHM_qaE1HFuaLrKV_igb3eZW";
        String secretKey = "6sK5bouPYuEdDmHCSUnbIAletrIVqKEwLyt1SPXs";
        String bucket = "codframe";
        Configuration cfg = new Configuration(Zone.zone0());
        UploadManager uploadManager = new UploadManager(cfg);

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        //文件名 (存入七牛云文件的最终名字)
        if (StringUtils.isEmpty(fileName)) {
            fileName = CodCommonDate.getDate("yyyyMMdd") + "/" + CodCommonDate.getDate("yyyyMMddHHmmssSSSS")
                    + new Random().nextInt(1000) + ".jpg";
        }
        try {
            Response response = uploadManager.put(inputStream, fileName, upToken,null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
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
    public String getSupportType() {
        return "qiniu";
    }
}
