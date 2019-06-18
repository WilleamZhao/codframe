package com.tlkj.cod.file.service.impl;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.tlkj.cod.common.CodCommonDate;
import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.file.model.CodFileInfo;
import com.tlkj.cod.file.model.CodFileModel;
import com.tlkj.cod.file.model.config.CodFileAliOssConfig;
import com.tlkj.cod.file.model.enums.CodFileTypeEnum;
import com.tlkj.cod.file.service.CodFileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

/**
 * Desc cod-file 阿里云 oss 服务
 *
 * @author sourcod
 * @version 1.0
 * @className CodFileAliOssServiceImpl
 * @date 2019/6/18 8:48 AM
 */
@Service
public class CodFileAliOssServiceImpl implements CodFileService {

    /**
     * 注入 ali oss 配置
     */
    @Autowired
    CodFileAliOssConfig codFileConfig;

    private ClientConfiguration conf = null;

    public CodFileAliOssServiceImpl(){
        // 上传到远程图片服务器
        conf = new ClientConfiguration();
        // 设置OSSClient使用的最大连接数，默认1024
        conf.setMaxConnections(codFileConfig.getMaxConnect());
        // 设置请求超时时间，默认50秒
        conf.setSocketTimeout(codFileConfig.getSocketTimeout());
        // 设置失败请求重试次数，默认3次
        conf.setMaxErrorRetry(codFileConfig.getMaxErrorRetry());
    }

    @Override
    public CodFileTypeEnum support() {
        return CodFileTypeEnum.ALIOSS;
    }

    /**
     * 上传文件
     * @param inputStream 文件
     * @param type        指定上传类型
     * @param fileName    文件名称
     * @param prefix      前缀; 统一放到attachment路径下的前缀,可传多个自动建立文件夹
     * @return
     */
    @Override
    public CodFileModel uploadFile(InputStream inputStream, CodFileTypeEnum type, String fileName, String... prefix) {
        String endpoint = codFileConfig.getEndpoint();
        String accessKeyId = codFileConfig.getAccessKeyId();
        String accessKeySecret = codFileConfig.getAccessKeySecret();
        String bucketName = codFileConfig.getBucketName();

        // 创建OSSClient实例
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret, conf);

        // 文件名不存在自动创建, 当前时间+随机数
        if (StringUtils.isEmpty(fileName)) {
            fileName = CodCommonDate.getDate("yyyyMMddHHmmssSSSS") + new Random().nextInt(1000);
        }

        // 拼接文件夹
        if (prefix != null && prefix.length > 0){
            fileName = StringUtils.join(prefix.clone(), File.separator) + File.separator + fileName;
        }

        // 上传
        client.putObject(bucketName, fileName, inputStream);
        // 关闭client
        client.shutdown();
        return new CodFileModel();
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
