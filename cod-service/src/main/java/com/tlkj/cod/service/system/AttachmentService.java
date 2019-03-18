/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.service.system;

import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.model.system.dto.CodFrameAttachmentListDto;
import com.tlkj.cod.model.enums.StatusCode;
import com.tlkj.cod.model.system.dto.CodFrameAttachmentTypeDto;

import java.util.List;

/**
 * Desc 附件Service
 *
 * @author sourcod
 * @version 1.0
 * @className AttachmentService
 * @date 2018/11/7 下午10:36
 */
public interface AttachmentService {

    /**
     * 附件列表接口
     * @param fileName 文件名
     * @param userName 用户名
     * @param extName  扩展名
     * @param size     文件大小
     * @param ip       上传ip
     * @param page     第几页
     * @param pageSize 每页显示几条
     * @return 附件列表
     */
    Page<List<CodFrameAttachmentListDto>> listAttachment(String fileName, String userName, String extName, String size, String ip, String page, String pageSize);



    /**
     * 删除附件
     * @param id     主键
     * @param userId 用户id
     * @param status 状态
     * @return
     */
    StatusCode deleteFile(String id, String userId, String status);


    /**
     * 保存文件
     * @param id       主键
     * @param fileName 文件名称
     * @param userId   用户Id
     * @param ip       用户ip
     * @param extName  扩展名
     * @param sort     排序
     * @return
     */
    StatusCode saveFile(String id, String fileName, String code, String fileType, String fileSize, String fileUnit, String userId, String ip, String extName, String sort, String url);

    /**
     * 在字典表里获取附件分类
     * @return
     */
    List<CodFrameAttachmentTypeDto> getType();

}
