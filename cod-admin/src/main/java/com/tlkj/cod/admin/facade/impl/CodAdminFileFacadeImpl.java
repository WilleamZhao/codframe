/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.admin.facade.impl;

import com.tlkj.cod.admin.facade.CodAdminFileFacade;
import com.tlkj.cod.admin.model.bo.CodAdminDictItemBo;
import com.tlkj.cod.admin.model.dto.CodAdminFileDto;
import com.tlkj.cod.admin.model.dto.CodAdminFileUrlDto;
import com.tlkj.cod.admin.model.entity.CodAdminTempFileDo;
import com.tlkj.cod.admin.model.enums.SystemCodeSet;
import com.tlkj.cod.admin.service.CodAdminDictService;
import com.tlkj.cod.admin.service.CodAdminFileService;
import com.tlkj.cod.admin.service.CodAdminSystemSetService;
import com.tlkj.cod.common.CodCommonDate;
import com.tlkj.cod.common.CodCommonFile;
import com.tlkj.cod.common.CodCommonFileSizeConverter;
import com.tlkj.cod.dao.jdbc.Updater;
import com.tlkj.cod.file.model.CodFileModel;
import com.tlkj.cod.file.model.config.CodFileConfig;
import com.tlkj.cod.file.model.enums.CodFileTypeEnum;
import com.tlkj.cod.file.service.CodFileManager;
import com.tlkj.cod.log.service.CodLogService;
import com.tlkj.cod.model.system.core.SystemModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * Desc 上传文件facade
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminFileFacadeImpl
 * @date 2018/12/1 4:34 PM
 */
@Service
public class CodAdminFileFacadeImpl implements CodAdminFileFacade {

    SystemModel systemModel = SystemModel.getInstance();
    /**
     * 附件常量
     */
    private final static String SYSTEM_ATTACHMENT_SET = SystemCodeSet.attachment.getCode();

    /**
     * 日志服务
     */
    @Autowired
    CodLogService codLogService;

    /**
     * 文件服务
     */
    @Autowired
    private List<CodAdminFileService> codAdminFileServices;

    @Autowired
    @Qualifier("codFileManagerImpl")
    private CodFileManager codFileManager;

    /**
     * 字典服务
     */
    @Autowired
    CodAdminDictService codAdminDictService;

    @Autowired
    CodFileConfig codFileConfig;

    /**
     * 系统设置服务
     */
    @Autowired
    CodAdminSystemSetService codAdminSystemSetService;

    @Autowired
    Updater updater;

    /**
     * 动态获取系统设置,文件服务
     */
    private CodAdminFileService getSystemSet(String type){
        for (CodAdminFileService f : codAdminFileServices){
            if (f.getSupportType().equals(type)){
                return f;
            }
        }
        return null;
    }

    /**
     * 获取Type
     * 优先级
     * 1: 传入
     * 2: 配置文件
     * 3: 数据库
     */
    private String getType(String type){
        if (StringUtils.isBlank(type)){
            if (systemModel.getFile() != null){
                type = systemModel.getFile().getType();
                // type=空, 设置为默认
                if (StringUtils.isBlank(type)){
                    type = "local";
                }
                return type;
            }
            type = codAdminSystemSetService.getSetValue(SYSTEM_ATTACHMENT_SET);
        }
        return type;
    }

    /**
     * 上传文件
     * @param prefix 前缀; 统一放到attachment路径下的前缀,可传多个自动建立文件夹
     * @param file   文件
     * @param type   指定上传方式; 2019-03-07 add
     * @return
     */
    @Override
    public CodAdminFileDto upload(MultipartFile file, String type, String... prefix){

        if (StringUtils.isBlank(type)){
            type = codFileConfig.getType();
        }

        InputStream io;
        CodAdminDictItemBo bo = codAdminDictService.getItem(SYSTEM_ATTACHMENT_SET + ":" +type);
        try {
            io = file.getInputStream();
        } catch (IOException e) {
            codLogService.error("获取文件失败, {}", e.getMessage());
            return null;
        }

        if (io == null || bo == null){
            return null;
        }

        // TODO 存文件名有可能冲突导致不同人上传覆盖
        // String fileName = file.getOriginalFilename();

        String ext = CodCommonFile.getSuffix(file.getOriginalFilename());
        String fileName = CodCommonDate.getDate("yyyyMMddHHmmss") + "-" + UUID.randomUUID().toString().replaceAll("-", "") + "." + ext;
        String url = bo.getValue();
        url = url.endsWith(File.separator) ? url : url + File.separator;
        String path;
        if (prefix != null && prefix.length > 0){
            path = String.join(File.separator,prefix.clone()) + File.separator;
        } else {
            path = CodCommonDate.getDate() + File.separator;
        }
        url += path;
        // 上传到本地
        // boolean isok = codAdminFileService.uploadFile(url, fileName, io);


        CodFileModel codFileModel = codFileManager.uploadFile(io, fileName, prefix);
        boolean isok = codFileModel != null;

        CodAdminFileDto codAdminFileDto = new CodAdminFileDto();
        if (isok){
            codAdminFileDto.setExtName(ext);
            String mbAll = CodCommonFileSizeConverter.BTrim.convert(file.getSize());
            String mb = CodCommonFileSizeConverter.ArbitraryTrim.convert(file.getSize());
            String unit = mbAll.substring(mb.length());
            codAdminFileDto.setFileName(CodCommonFile.getPrefix(file.getOriginalFilename()));
            codAdminFileDto.setFileSize(mb);
            codAdminFileDto.setUrl(path + fileName);
            codAdminFileDto.setFileUnit(unit);
            return codAdminFileDto;
        }
        return null;
    }

    @Override
    public boolean delete(String id) {
        int i = updater.update(CodAdminTempFileDo.TABLE_NAME).set("status", "-1").where("id", id).update();
        if (i == 1){
            return true;
        }
        return false;
    }


    /**
     * 获取文件路径
     */
    @Override
    public CodAdminFileUrlDto getFileUrl(String type) {
        if (StringUtils.isBlank(type)){
            type = codAdminSystemSetService.getSetValue(SYSTEM_ATTACHMENT_SET);
        }
        String url = codAdminDictService.getItem(SYSTEM_ATTACHMENT_SET + ":" +type+"-url").getValue();
        CodAdminFileUrlDto dto = new CodAdminFileUrlDto();
        dto.setType(type);
        dto.setUrl(url);
        return dto;
    }
}
