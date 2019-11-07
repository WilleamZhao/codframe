package com.tlkj.cod.file.service.impl;

import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Updater;
import com.tlkj.cod.file.model.CodFileInfo;
import com.tlkj.cod.file.model.CodFileModel;
import com.tlkj.cod.file.model.config.CodFileDatabaseConfig;
import com.tlkj.cod.file.model.enums.CodFileTypeEnum;
import com.tlkj.cod.file.service.CodFileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

/**
 * Desc cod-file database 服务
 *
 * @author sourcod
 * @version 1.0
 * @className CodFileDatabaseServiceImpl
 * @date 2019/6/18 8:56 AM
 */
@Service
public class CodFileDatabaseServiceImpl implements CodFileManager {

    private Updater updater;

    private Finder finder;

    private CodFileDatabaseConfig codFileDatabaseConfig;

    @Autowired
    public CodFileDatabaseServiceImpl(Updater updater, Finder finder, CodFileDatabaseConfig codFileDatabaseConfig){
        this.updater = updater;
        this.finder = finder;
        this.codFileDatabaseConfig = codFileDatabaseConfig;
    }

    @Override
    public CodFileTypeEnum support() {
        return CodFileTypeEnum.DATABASE;
    }

    @Override
    public CodFileModel uploadFile(InputStream inputStream, String fileName, String... prefix) {
        // updater.insert();
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
