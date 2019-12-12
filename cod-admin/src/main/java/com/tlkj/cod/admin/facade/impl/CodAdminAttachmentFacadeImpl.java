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

import com.tlkj.cod.admin.facade.CodAdminAttachmentFacade;
import com.tlkj.cod.admin.facade.CodAdminFileFacade;
import com.tlkj.cod.admin.model.bo.CodAdminDictItemBo;
import com.tlkj.cod.admin.model.dto.CodAdminFileDto;
import com.tlkj.cod.admin.service.CodAdminAttachmentService;
import com.tlkj.cod.admin.service.CodAdminDictService;
import com.tlkj.cod.admin.service.CodAdminSystemSetService;
import com.tlkj.cod.common.CodCommonNetWork;
import com.tlkj.cod.core.annotation.CodParamVerify;
import com.tlkj.cod.log.annotation.CodLog;
import com.tlkj.cod.log.service.CodLogService;
import com.tlkj.cod.model.enums.StatusCode;

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
 * @className CodAdminAttachmentFacadeImpl
 * @date 2018/11/28 12:52 PM
 */
@Service
public class CodAdminAttachmentFacadeImpl implements CodAdminAttachmentFacade {

    private final static String systemSet = "attachment";

    @Autowired
    CodAdminAttachmentService codAdminAttachmentService;

    @Autowired
    CodAdminDictService codAdminDictService;

    @Autowired
    CodAdminSystemSetService codAdminSystemSetService;

    @Autowired
    CodAdminFileFacade codAdminFileFacade;

    @Autowired
    CodLogService codLogService;

    /**
     * 保存附件
     * @param id
     * @param userId      上传用户Id
     * @return
     */
    @CodLog(name = "上传附件facade")
    @CodParamVerify(parameter = "code")
    @Override
    public StatusCode save(String id, String userId, String fileName, String fileType, String extName, String fileSize, String fileUnit, String url, HttpServletRequest request) {
        String set = codAdminSystemSetService.getSetValue(systemSet);
        InputStream io = null;
        String ip = "";
        CodAdminDictItemBo bo = codAdminDictService.getItem(systemSet + ":" + set);
        try {
            ip = CodCommonNetWork.getIpAddress(request);
        } catch (IOException e) {
            codLogService.error("获取文件&ip失败, {}", e.getMessage());
            return null;
        }

        if (bo == null){
            return null;
        }

        return codAdminAttachmentService.saveFile(id, fileName, bo.getCode(), fileType, fileSize, fileUnit, userId, ip, extName, "0", url);
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
    @CodLog(name = "上传附件")
    @Override
    public StatusCode uploadFile(String id, MultipartFile file, String userId, String fileName, String fileType, HttpServletRequest request) {
        String set = codAdminSystemSetService.getSetValue(systemSet);
        InputStream io = null;
        String ip = "";
        CodAdminDictItemBo bo = codAdminDictService.getItem(systemSet + ":" + set);
        try {
            io = file.getInputStream();
            ip = CodCommonNetWork.getIpAddress(request);
        } catch (IOException e) {
            codLogService.error("获取文件&ip失败, {}", e.getMessage());
            return null;
        }

        if (bo == null){
            return null;
        }

        CodAdminFileDto codAdminFileDto = codAdminFileFacade.upload(file, "");
        if (codAdminFileDto == null){
            return StatusCode.FAIL_CODE;
        }

        fileName = StringUtils.isNotBlank(fileName) ? fileName : codAdminFileDto.getFileName();
        String fileSize = codAdminFileDto.getFileSize();
        String fileUnit = codAdminFileDto.getFileUnit();
        String extName = codAdminFileDto.getExtName();
        String url = codAdminFileDto.getUrl();
        return codAdminAttachmentService.saveFile(id, fileName, bo.getCode(), fileType, fileSize, fileUnit, userId, ip, extName, "0", url);
    }
}
