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

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.tlkj.cod.core.annotation.Log;
import com.tlkj.cod.model.system.core.SystemFileModel;
import com.tlkj.cod.model.system.core.SystemModel;
import com.tlkj.cod.service.system.DictService;
import com.tlkj.cod.service.system.FileService;
import com.tlkj.cod.common.CodCommonDate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Random;

/**
 * Desc 阿里云OSS 附件Service Impl
 *
 * @author sourcod
 * @version 1.0
 * @className UploadOSSServiceImpl
 * @date 2018/11/20 6:03 PM
 */
@Service("codFileOssImpl")
public class FileOssServiceImpl implements FileService {

    SystemFileModel.Oss oss = SystemModel.getInstance().getFile().getOss();

    private ClientConfiguration conf = null;

    @Autowired
    DictService dictService;

    FileOssServiceImpl(){
        // 上传到远程图片服务器
        conf = new ClientConfiguration();
        // 设置OSSClient使用的最大连接数，默认1024
        conf.setMaxConnections(oss.getMaxConnections());
        // 设置请求超时时间，默认50秒
        conf.setSocketTimeout(oss.getSocketTimeout());
        // 设置失败请求重试次数，默认3次
        conf.setMaxErrorRetry(oss.getMaxErrorRetry());
    }

    /**
     * 上传到oss接口
     * @param url         上传目录路径
     * @param fileName    文件名称
     * @param inputStream 文件流
     * @return
     */
    @Log(name = "上传OSS")
    @Override
    public boolean uploadFile(String url, String fileName, InputStream inputStream) {

        // 创建OSSClient实例
        OSSClient client = new OSSClient(oss.getEndPoint(), oss.getAccessKeyId(), oss.getAccessKeySecret(), conf);
        // 使用访问OSS
        if (StringUtils.isEmpty(fileName)) {
            fileName = CodCommonDate.getDate("yyyyMMdd") + "/" + CodCommonDate.getDate("yyyyMMddHHmmssSSSS")
                    + new Random().nextInt(1000) + ".jpg";
        }
        // 上传
        client.putObject(oss.getBucketName(), oss.getProjectName() + "/" + fileName, inputStream);
        // 关闭client
        client.shutdown();
        return true;
    }

    @Log(name = "删除文件")
    @Override
    public boolean deleteFile(String url, String fileName) {
        return false;
    }

    @Log(name = "读取文件")
    @Override
    public InputStream readFile(String url, String fileName) {
        return null;
    }

    @Override
    public String getSupportType(){
        return "oss";
    }

}
