package com.tlkj.cod.file.service.impl;

import com.tlkj.cod.common.CodCommonIO;
import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.file.common.CodFileCommon;
import com.tlkj.cod.file.model.CodFileInfo;
import com.tlkj.cod.file.model.CodFileModel;
import com.tlkj.cod.file.model.config.CodFileLocalConfig;
import com.tlkj.cod.file.model.enums.CodFileTypeEnum;
import com.tlkj.cod.file.service.CodFileLocalService;
import com.tlkj.cod.log.service.CodLogService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Desc cod-file 本地服务
 *
 * @author sourcod
 * @version 1.0
 * @className CodFileLocalServiceImpl
 * @date 2019/6/18 8:55 AM
 */
@Service
public class CodFileLocalServiceImpl extends CodFileCommon implements CodFileLocalService {

    private static Logger logger = LoggerFactory.getLogger(CodFileLocalServiceImpl.class);

    private CodLogService codLogService;
    private CodFileLocalConfig codFileLocalConfig;
    private String url;

    public CodFileLocalServiceImpl(){

    }

    @Autowired
    public CodFileLocalServiceImpl(CodLogService codLogService, CodFileLocalConfig codFileLocalConfig){
        this.codLogService = codLogService;
        this.codFileLocalConfig = codFileLocalConfig;
        this.url = codFileLocalConfig.getUrl();
    }

    @Override
    public CodFileTypeEnum support() {
        return CodFileTypeEnum.LOCAL;
    }

    /**
     * 上传文件到本地
     * 1. 获取本地路径
     * 2. 拼接文件夹路径
     * 3. 上传文件
     * @param inputStream 文件
     * @param fileName    文件名称
     * @param prefix      前缀; 统一放到attachment路径下的前缀,可传多个自动建立文件夹
     * @return
     */
    @Override
    public CodFileModel uploadFile(InputStream inputStream, String fileName, String... prefix) {
        try {
            url = url.endsWith(File.separator) ? url : url + File.separator;
            url += getFileHref(fileName, prefix);
            CodCommonIO.outputFile(url, fileName, inputStream);
        } catch (IOException e) {
            codLogService.error("上传文件失败, {}", e.getMessage());
            return null;
        }
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public InputStream readFile(String url, String fileName) {
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
    public Page<List<CodFileInfo>> listFile(CodFileTypeEnum typeEnum) {
        return null;
    }

    @Override
    public boolean delete(String url, String fileName) {
        try {
            FileUtils.forceDelete(new File(url + fileName));
        } catch (IOException e) {
            logger.error("删除文件失败, {}", e.getMessage());
            return false;
        }
        return true;
    }
}
