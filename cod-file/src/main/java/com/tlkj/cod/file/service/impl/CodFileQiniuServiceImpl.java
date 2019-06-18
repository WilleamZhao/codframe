package com.tlkj.cod.file.service.impl;

import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.file.model.CodFileInfo;
import com.tlkj.cod.file.model.CodFileModel;
import com.tlkj.cod.file.model.enums.CodFileTypeEnum;
import com.tlkj.cod.file.service.CodFileService;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

/**
 * Desc cod-file 七牛服务
 *
 * @author sourcod
 * @version 1.0
 * @className CodFileQiniuServiceImpl
 * @date 2019/6/18 8:55 AM
 */
@Service
public class CodFileQiniuServiceImpl implements CodFileService {



    @Override
    public CodFileTypeEnum support() {
        return CodFileTypeEnum.QINIU;
    }

    @Override
    public CodFileModel uploadFile(InputStream inputStream, CodFileTypeEnum type, String fileName, String... prefix) {
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
