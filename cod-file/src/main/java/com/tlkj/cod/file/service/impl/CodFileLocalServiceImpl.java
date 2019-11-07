package com.tlkj.cod.file.service.impl;


import com.tlkj.cod.common.CodCommonIO;
import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.file.model.CodFileInfo;
import com.tlkj.cod.file.model.CodFileModel;
import com.tlkj.cod.file.model.enums.CodFileTypeEnum;
import com.tlkj.cod.file.service.CodFileLocalService;
import com.tlkj.cod.log.service.CodLogService;
import org.apache.commons.lang3.StringUtils;
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
public class CodFileLocalServiceImpl implements CodFileLocalService {

    @Autowired
    CodLogService codLogService;

    @Override
    public CodFileTypeEnum support() {
        return CodFileTypeEnum.LOCAL;
    }

    @Override
    public CodFileModel uploadFile(InputStream inputStream, String fileName, String... prefix) {
        try {
            String path = "";
            // 拼接文件夹
            if (prefix != null && prefix.length > 0){
                path = StringUtils.join(prefix.clone(), File.separator) + File.separator;
            }
            CodCommonIO.outputFile(path, fileName, inputStream);
        } catch (IOException e) {
            codLogService.error("上传文件失败, {}", e.getMessage());
            return new CodFileModel();
        }
        return null;
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
