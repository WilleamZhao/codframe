/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.facade.system.impl;

import com.tlkj.cod.dao.jdbc.Updater;
import com.tlkj.cod.facade.system.FileFacade;
import com.tlkj.cod.log.service.LogService;
import com.tlkj.cod.model.system.bo.CodFrameDictItemBo;
import com.tlkj.cod.model.system.core.SystemModel;
import com.tlkj.cod.model.system.dto.CodFrameFileDto;
import com.tlkj.cod.model.system.dto.CodFrameFileUrlDto;
import com.tlkj.cod.model.system.entity.CodFrameTempFileDo;
import com.tlkj.cod.model.system.enums.SystemCodeSet;
import com.tlkj.cod.service.system.DictService;
import com.tlkj.cod.service.system.FileService;
import com.tlkj.cod.service.system.SystemSetService;
import com.tlkj.cod.common.CodCommonDate;
import com.tlkj.cod.common.CodCommonFile;
import com.tlkj.cod.common.CodCommonFileSizeConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @className FileFacadeImpl
 * @date 2018/12/1 4:34 PM
 */
@Service
public class FileFacadeImpl implements FileFacade {

    SystemModel systemModel = SystemModel.getInstance();
    /**
     * 附件常量
     */
    private final static String SYSTEM_ATTACHMENT_SET = SystemCodeSet.attachment.getCode();

    /**
     * 日志服务
     */
    @Autowired
    LogService logService;

    /**
     * 文件服务
     */
    @Autowired
    private List<FileService> fileServices;

    /**
     * 字典服务
     */
    @Autowired
    DictService dictService;

    /**
     * 系统设置服务
     */
    @Autowired
    SystemSetService systemSetService;

    @Autowired
    Updater updater;

    /**
     * 动态获取系统设置,文件服务
     */
    private FileService getSystemSet(String type){
        for (FileService f : fileServices){
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
            type = systemSetService.getSetValue(SYSTEM_ATTACHMENT_SET);
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
    public CodFrameFileDto upload(MultipartFile file, String type, String... prefix){

        type = getType(type);

        FileService fileService = getSystemSet(type);

        if (fileService == null){
            logService.warn("找不到上传文件方式");
            return null;
        }
        InputStream io;
        CodFrameDictItemBo bo = dictService.getItem(SYSTEM_ATTACHMENT_SET + ":" +type);
        try {
            io = file.getInputStream();
        } catch (IOException e) {
            logService.error("获取文件失败, {}", e.getMessage());
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
        boolean isok = fileService.uploadFile(url, fileName, io);
        CodFrameFileDto codFrameFileDto = new CodFrameFileDto();
        if (isok){
            codFrameFileDto.setExtName(ext);
            String mbAll = CodCommonFileSizeConverter.BTrim.convert(file.getSize());
            String mb = CodCommonFileSizeConverter.ArbitraryTrim.convert(file.getSize());
            String unit = mbAll.substring(mb.length());
            codFrameFileDto.setFileName(CodCommonFile.getPrefix(file.getOriginalFilename()));
            codFrameFileDto.setFileSize(mb);
            codFrameFileDto.setUrl(path + fileName);
            codFrameFileDto.setFileUnit(unit);
            return codFrameFileDto;
        }
        return null;
    }

    @Override
    public boolean delete(String id) {
        int i = updater.update(CodFrameTempFileDo.TABLE_NAME).set("status", "-1").where("id", id).update();
        if (i == 1){
            return true;
        }
        return false;
    }


    /**
     * 获取文件路径
     */
    @Override
    public CodFrameFileUrlDto getFileUrl(String type) {
        if (StringUtils.isBlank(type)){
            type = systemSetService.getSetValue(SYSTEM_ATTACHMENT_SET);
        }
        String url = dictService.getItem(SYSTEM_ATTACHMENT_SET + ":" +type+"-url").getValue();
        CodFrameFileUrlDto dto = new CodFrameFileUrlDto();
        dto.setType(type);
        dto.setUrl(url);
        return dto;
    }
}
