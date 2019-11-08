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
import com.tlkj.cod.admin.model.dto.CodAdminFileDto;
import com.tlkj.cod.admin.model.dto.CodAdminFileUrlDto;
import com.tlkj.cod.admin.model.entity.CodAdminTempFileDo;
import com.tlkj.cod.admin.model.enums.SystemCodeSet;
import com.tlkj.cod.admin.service.CodAdminDictService;
import com.tlkj.cod.admin.service.CodAdminSystemSetService;
import com.tlkj.cod.common.CodCommonFile;
import com.tlkj.cod.common.CodCommonFileSizeConverter;
import com.tlkj.cod.dao.jdbc.Updater;
import com.tlkj.cod.file.common.CodFileCommon;
import com.tlkj.cod.file.model.CodFileModel;
import com.tlkj.cod.file.model.config.CodFileConfig;
import com.tlkj.cod.file.service.CodFileManager;
import com.tlkj.cod.log.service.CodLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

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
     * 上传文件 重置文件名
     * @param prefix      前缀; 统一放到attachment路径下的前缀,可传多个自动建立文件夹
     * @param file        文件
     * @param type        指定上传方式; 2019-03-07 add
     * @return
     */
    @Override
    public CodAdminFileDto upload(MultipartFile file, String type, String... prefix){
        return upload(file, type, true, prefix);
    }

    /**
     * 上传文件
     * @param prefix      前缀; 统一放到attachment路径下的前缀,可传多个自动建立文件夹
     * @param file        文件
     * @param type        指定上传方式; 2019-03-07 add
     * @param isResetName 是否重置文件名
     * @return
     */
    @Override
    public CodAdminFileDto upload(MultipartFile file, String type, boolean isResetName, String... prefix){
        InputStream io;
        try {
            io = file.getInputStream();
        } catch (IOException e) {
            codLogService.error("获取文件失败, {}", e.getMessage());
            return null;
        }

        if (io == null){
            return null;
        }

        // 获取前缀
        String fileName = CodCommonFile.getPrefix(file.getOriginalFilename());

        // 获取后缀
        String ext = CodCommonFile.getSuffix(file.getOriginalFilename());

        fileName = CodFileCommon.getFileHref(isResetName ? "" : fileName, prefix)+ "." + ext;

        CodFileModel codFileModel = codFileManager.uploadFile(io, fileName, prefix);

        if (codFileModel != null){
            CodAdminFileDto codAdminFileDto = new CodAdminFileDto();
            codAdminFileDto.setExtName(ext);

            String mbAll = CodCommonFileSizeConverter.BTrim.convert(file.getSize());
            // 获取文件大型
            String mb = CodCommonFileSizeConverter.ArbitraryTrim.convert(file.getSize());
            // 获取单位
            String unit = mbAll.substring(mb.length());
            codAdminFileDto.setFileName(fileName);
            codAdminFileDto.setFileSize(mb);
            codAdminFileDto.setUrl(fileName);
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
