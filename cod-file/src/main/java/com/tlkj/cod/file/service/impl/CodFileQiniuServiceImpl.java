package com.tlkj.cod.file.service.impl;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.tlkj.cod.common.CodCommonDate;
import com.tlkj.cod.common.CodCommonJson;
import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.file.common.CodFileCommon;
import com.tlkj.cod.file.model.CodFileInfo;
import com.tlkj.cod.file.model.CodFileModel;
import com.tlkj.cod.file.model.config.CodFileQiniuConfig;
import com.tlkj.cod.file.model.enums.CodFileTypeEnum;
import com.tlkj.cod.file.service.CodFileManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Random;

/**
 * Desc cod-file 七牛服务
 *
 * @author sourcod
 * @version 1.0
 * @className CodFileQiniuServiceImpl
 * @date 2019/6/18 8:55 AM
 */
@Service
public class CodFileQiniuServiceImpl extends CodFileCommon implements CodFileManager {

    private CodFileQiniuConfig codFileQiniuConfig;

    @Autowired
    public CodFileQiniuServiceImpl(CodFileQiniuConfig codFileQiniuConfig){
        this.codFileQiniuConfig = codFileQiniuConfig;
    }


    @Override
    public CodFileTypeEnum support() {
        return CodFileTypeEnum.QINIU;
    }

    @Override
    public CodFileModel uploadFile(InputStream inputStream, String fileName, String... prefix) {
        CodFileModel codFileModel = new CodFileModel();
        Configuration cfg = new Configuration(Zone.zone0());
        UploadManager uploadManager = new UploadManager(cfg);

        String accessKey = codFileQiniuConfig.getAccessKey();
        String secretKey = codFileQiniuConfig.getSecretKey();
        String bucket = codFileQiniuConfig.getBucket();

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        //文件名 (存入七牛云文件的最终名字)
        fileName = getFileHref(fileName, prefix);

        try {
            Response response = uploadManager.put(inputStream, fileName, upToken,null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = CodCommonJson.load(response.bodyString(), DefaultPutRet.class);
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
        codFileModel.setFileName(fileName);
        codFileModel.setFileType(CodFileTypeEnum.QINIU);
        return codFileModel;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public InputStream readFile(String url, String fileName) {
        return null;
    }

    @Override
    public Page<List<CodFileInfo>> listFile(CodFileTypeEnum typeEnum) {
        return null;
    }
}
