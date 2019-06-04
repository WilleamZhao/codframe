/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.admin.service.impl;

import com.tlkj.cod.admin.service.AttachmentService;
import com.tlkj.cod.admin.service.DictService;
import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Pagination;
import com.tlkj.cod.dao.jdbc.Updater;
import com.tlkj.cod.log.annotation.Log;
import com.tlkj.cod.model.enums.StatusCode;
import com.tlkj.cod.model.system.dto.CodFrameAttachmentListDto;
import com.tlkj.cod.model.system.dto.CodFrameAttachmentTypeDto;
import com.tlkj.cod.model.system.entity.CodFrameAttachmentDo;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc 附件Service Impl
 *
 * @author sourcod
 * @version 1.0
 * @className AttachmentServiceImpl
 * @date 2018/11/20 7:14 PM
 */
@Service
public class AttachmentServiceImpl implements AttachmentService {

    private static Logger logger = LoggerFactory.getLogger(AttachmentServiceImpl.class);

    @Autowired
    Finder finder;

    @Autowired
    Updater updater;

    @Autowired
    DictService dictService;

    /**
     * 查询附件列表接口
     * @param fileName 文件名
     * @param userName 用户名
     * @param extName  扩展名
     * @param size     文件大小
     * @param ip       上传ip
     * @param page     第几页
     * @param pageSize 每页显示几条
     * @return
     */
    @Log(name = "查询附件列表")
    @Override
    public Page<List<CodFrameAttachmentListDto>> listAttachment(String fileName, String userName, String extName, String size, String ip, String page, String pageSize) {
        Finder.Query query = finder.from(CodFrameAttachmentDo.TABLE_NAME);
        if (StringUtils.isNotBlank(fileName)){
            query.where("file_name", fileName);
        }

        if (StringUtils.isNotBlank(userName)){
            query.where("user_name", userName);
        }

        if (StringUtils.isNotBlank(extName)){
            query.where("file_extname", extName);
        }

        if (StringUtils.isNotBlank(size)){
            query.where("file_size < " + size);
        }

        if (StringUtils.isNotBlank(ip)){
            query.like("upload_ip", ip + "%");
        }

        int currentPage = StringUtils.isNotBlank(page) ? Integer.parseInt(page) : 1;
        int perPage = StringUtils.isNotBlank(pageSize) ? Integer.parseInt(pageSize) : Pagination.DEFAULT_PER_PAGE;

        Pagination<CodFrameAttachmentDo> pagination;
        try {
            pagination = query.paginate(CodFrameAttachmentDo.class, currentPage, perPage);
        } catch (Exception e) {
            logger.error("sql查询错误:={}", e.getMessage());
            return null;
        }

        if (pagination == null) {
            return new Page<>();
        }
        List<CodFrameAttachmentDo> attachmentDos = pagination.getData();
        List<CodFrameAttachmentListDto> dtoList = new ArrayList<>();

        attachmentDos.forEach(item -> {
            CodFrameAttachmentListDto attachmentListDto = new CodFrameAttachmentListDto();
            attachmentListDto.setId(item.getId());
            attachmentListDto.setCreateTime(item.getCreate_time());
            attachmentListDto.setExtName(item.getFile_extname());
            attachmentListDto.setFileName(item.getFile_name());
            attachmentListDto.setIp(item.getUpload_ip());
            attachmentListDto.setSize(item.getFile_size());
            attachmentListDto.setUnit(item.getFile_unit());
            attachmentListDto.setUrl(item.getUrl());
            attachmentListDto.setUserId(item.getUser_id());
            attachmentListDto.setUserName(item.getUser_name());
            attachmentListDto.setFileType(item.getFile_type());
            attachmentListDto.setFileStatus(item.getFile_status());
            dtoList.add(attachmentListDto);
        });
        return new Page<>(dtoList, pagination);
    }

    /**
     * 删除文件接口
     * @param id     主键
     * @param userId 用户id
     * @param status 状态 1: 正常; 0: 已删除; -1: 彻底删除;
     * @return
     */
    @Log(name = "删除文件")
    @Override
    public StatusCode deleteFile(String id, String userId, String status) {
        Updater.Update update = updater.update(CodFrameAttachmentDo.TABLE_NAME)
                .set("user_id", userId).set("update_time", "now()", Void.class);
        update = StringUtils.isBlank(status) ? update.set("status", "0") : "0".equals(status) ? update.set("status", "-1") : update;
        update.where("id", id);

        int i = update.update();
        if (i == 1){
            return StatusCode.SUCCESS_CODE;
        }

        return StatusCode.FAIL_CODE;
    }

    /**
     * 更新文件接口
     * @param id       主键
     * @param fileName 文件名称
     * @param userId   用户Id
     * @param ip       用户ip
     * @param extName  扩展名
     * @param sort     排序
     * @param url      路径
     * @return
     */
    @Override
    public StatusCode saveFile(String id, String fileName, String code, String fileType, String fileSize, String fileUnit, String userId, String ip, String extName, String sort, String url) {
        int num = finder.from(CodFrameAttachmentDo.TABLE_NAME).where("id", id).select("count(*)").firstForObject(Integer.class);
        Updater.Update update = num == 0 ? updater.insert(CodFrameAttachmentDo.TABLE_NAME).setId() : updater.update(CodFrameAttachmentDo.TABLE_NAME).where("id", id);

        if (StringUtils.isNotBlank(fileName)){
            update.set("file_name", fileName);
        }

        if (StringUtils.isNotBlank(fileName)){
            update.set("head_code", code);
        }

        if (StringUtils.isNotBlank(fileName)){
            update.set("file_type", fileType);
        }

        if (StringUtils.isNotBlank(fileSize)){
            update.set("file_size", fileSize);
        }

        if (StringUtils.isNotBlank(fileUnit)){
            update.set("file_unit", fileUnit);
        }

        if (StringUtils.isNotBlank(userId)){
            update.set("user_id", userId);
        }

        if (StringUtils.isNotBlank(ip)){
            update.set("upload_ip", ip);
            update.set("upload_date", "now()", Void.class);
        }

        if (StringUtils.isNotBlank(extName)){
            update.set("file_extname", extName);
        }

        if (StringUtils.isNotBlank(sort)){
            update.set("sort", sort);
        }

        if (StringUtils.isNotBlank(url)){
            update.set("url", url);
        }

        int i = update.update();

        if (i == 1){
            return StatusCode.SUCCESS_CODE;
        }
        return StatusCode.FAIL_CODE;
    }

    /**
     * 获取类型
     * @return
     */
    @Override
    public List<CodFrameAttachmentTypeDto> getType() {
        List<CodFrameAttachmentTypeDto> list = new ArrayList<>();

        dictService.getItemByType("attachment-type").forEach( item ->{
            CodFrameAttachmentTypeDto typeDto = new CodFrameAttachmentTypeDto();
            typeDto.setId(item.getId());
            typeDto.setCode(item.getCode());
            typeDto.setName(item.getName());
            typeDto.setValue(item.getValue());
            list.add(typeDto);
        });

        return list;
    }
}
