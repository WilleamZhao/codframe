/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.admin.facade.impl;

import com.tlkj.cod.admin.facade.AttachmentFacade;
import com.tlkj.cod.admin.facade.FileFacade;
import com.tlkj.cod.admin.service.AttachmentService;
import com.tlkj.cod.admin.service.DictService;
import com.tlkj.cod.admin.service.SystemSetService;
import com.tlkj.cod.common.CodCommonNetWork;
import com.tlkj.cod.core.annotation.ParamNotNull;
import com.tlkj.cod.log.annotation.Log;
import com.tlkj.cod.model.enums.StatusCode;
import com.tlkj.cod.model.system.bo.CodFrameDictItemBo;
import com.tlkj.cod.model.system.dto.CodFrameFileDto;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

/**
 * Desc 附件管理Facade Impl
 *
 * @author sourcod
 * @version 1.0
 * @className AttachmentFacadeImpl
 * @date 2018/11/28 12:52 PM
 */
@Service
public class AttachmentFacadeImpl implements AttachmentFacade {

    private final static String systemSet = "attachment";

    @Autowired
    AttachmentService attachmentService;

    @Autowired
    DictService dictService;

    @Autowired
    SystemSetService systemSetService;

    @Autowired
    FileFacade fileFacade;

    /**
     * 保存附件
     * @param id
     * @param userId      上传用户Id
     * @return
     */
    @Log(name = "上传附件facade")
    @ParamNotNull(parameter = "code")
    @Override
    public StatusCode save(String id, String userId, String fileName, String fileType, String extName, String fileSize, String fileUnit, String url, HttpServletRequest request) {
        String set = systemSetService.getSetValue(systemSet);
        InputStream io = null;
        String ip = "";
        CodFrameDictItemBo bo = dictService.getItem(systemSet + ":" + set);
        try {
            ip = CodCommonNetWork.getIpAddress(request);
        } catch (IOException e) {
            systemSetService.getLog().error("获取文件&ip失败, {}", e.getMessage());
            return null;
        }

        if (bo == null){
            return null;
        }

        return attachmentService.saveFile(id, fileName, bo.getCode(), fileType, fileSize, fileUnit, userId, ip, extName, "0", url);
    }

    /**
     * 上传附件
     * @param id
     * @param file
     * @param userId
     * @param fileType
     * @param request
     * @return
     */
    @Log(name = "上传附件")
    @Override
    public StatusCode uploadFile(String id, MultipartFile file, String userId, String fileName, String fileType, HttpServletRequest request) {
        String set = systemSetService.getSetValue(systemSet);
        InputStream io = null;
        String ip = "";
        CodFrameDictItemBo bo = dictService.getItem(systemSet + ":" + set);
        try {
            io = file.getInputStream();
            ip = CodCommonNetWork.getIpAddress(request);
        } catch (IOException e) {
            systemSetService.getLog().error("获取文件&ip失败, {}", e.getMessage());
            return null;
        }

        if (bo == null){
            return null;
        }

        CodFrameFileDto codFrameFileDto = fileFacade.upload(file, "");
        if (codFrameFileDto == null){
            return StatusCode.FAIL_CODE;
        }

        fileName = StringUtils.isNotBlank(fileName) ? fileName : codFrameFileDto.getFileName();
        String fileSize = codFrameFileDto.getFileSize();
        String fileUnit = codFrameFileDto.getFileUnit();
        String extName = codFrameFileDto.getExtName();
        String url = codFrameFileDto.getUrl();
        return attachmentService.saveFile(id, fileName, bo.getCode(), fileType, fileSize, fileUnit, userId, ip, extName, "0", url);
    }
}
