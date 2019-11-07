package com.tlkj.cod.file.facade.impl;

import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.file.facade.CodFileFacade;
import com.tlkj.cod.file.model.CodFileInfo;
import com.tlkj.cod.file.model.CodFileModel;
import com.tlkj.cod.file.model.enums.CodFileTypeEnum;
import com.tlkj.cod.file.service.CodFileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

/**
 * Desc cod-file facade
 *
 * @author sourcod
 * @version 1.0
 * @className CodFileFacadeImpl
 * @date 2019/6/18 2:17 PM
 */
@Service
public class CodFileFacadeImpl implements CodFileFacade {

    @Autowired
    List<CodFileManager> codFileServices;

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
        CodFileManager codFileService = getCodFileService(type);
        if (codFileService == null){
            return null;
        }

        return codFileService.uploadFile(inputStream, fileName, prefix);
    }

    /**
     * 删除文件
     * @param id 主键
     * @return
     */
    @Override
    public boolean delete(String id) {
        return false;
    }

    /**
     * 读取文件
     * @param url
     * @param fileName
     * @return
     */
    @Override
    public InputStream readFile(String url, String fileName) {
        return null;
    }

    /**
     * 获取文件列表
     * @param typeEnum
     * @return
     */
    @Override
    public Page<List<CodFileInfo>> listFile(CodFileTypeEnum typeEnum) {
        return null;
    }

    /**
     * 获取 CodFileManager
     * @return codService
     */
    private CodFileManager getCodFileService(CodFileTypeEnum type){
        if (codFileServices.isEmpty()){
            return null;
        }
        for (CodFileManager codFileService : codFileServices){
            if (codFileService.support().equals(type)){
                return codFileService;
            }
        }
        return null;
    }
}
