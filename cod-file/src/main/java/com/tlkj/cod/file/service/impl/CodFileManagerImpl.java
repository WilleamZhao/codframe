/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.file.service.impl;

import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.data.service.CodDataService;
import com.tlkj.cod.file.common.CodFileCommon;
import com.tlkj.cod.file.model.CodFileInfo;
import com.tlkj.cod.file.model.CodFileModel;
import com.tlkj.cod.file.model.enums.CodFileTypeEnum;
import com.tlkj.cod.file.service.CodFileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

/**
 * Desc 文件管理 manager
 *
 * @author sourcod
 * @version 1.0
 * @className CodFileManagerImpl
 * @date 2019/11/7 11:45 AM
 */
@Primary
@Service
public class CodFileManagerImpl extends CodFileCommon implements CodFileManager {

    private List<CodFileManager> codFileManagers;
    private CodDataService codDataService;
    private CodFileManager codFileManage;
    private long refreshTime = 0;

    @Autowired
    public CodFileManagerImpl(List<CodFileManager> codFileManagers, CodDataService codDataService){

        this.codFileManagers = codFileManagers;
        // 文件类型
        this.codDataService = codDataService;
    }

    /**
     * 动态获取文件类型
     * @return
     */
    private CodFileManager getCodFileManager(){
        // 未刷新直接返回
        if (refreshTime != 0 && refreshTime == codDataService.getRefreshTime()){
            return this.codFileManage;
        }
        // 获取 上传类型
        String type = codDataService.getDataValue("cod.file.config.type");
        // 设置 上传类型 service
        for (CodFileManager codFileManager : codFileManagers){
            if (type.equals(codFileManager.support().getSupport())) {
                refreshTime = codDataService.getRefreshTime();
                this.codFileManage = codFileManager;
                return codFileManager;
            }
        }
        // 如果为空默认本地上传方法
        return new CodFileLocalServiceImpl();
    }

    @Override
    public CodFileTypeEnum support() {
        return CodFileTypeEnum.LOCAL;
    }

    @Override
    public CodFileModel uploadFile(InputStream inputStream, String fileName, String... prefix) {
        return getCodFileManager().uploadFile(inputStream, fileName, prefix);
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
