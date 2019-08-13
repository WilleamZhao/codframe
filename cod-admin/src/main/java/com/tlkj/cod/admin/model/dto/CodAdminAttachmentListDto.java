/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.admin.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * Desc 附件列表Dto
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminAttachmentListDto
 * @date 2018/11/20 3:33 PM
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CodAdminAttachmentListDto {

    /**
     * 附件Id
     */
    private String id;

    /**
     * 附件url
     */
    private String url;

    /**
     * 附件大小
     */
    private String size;

    /**
     * 大小单位
     */
    private String unit;

    /**
     * 附件名称
     */
    private String fileName;

    /**
     * 附件类型
     */
    private String fileType;

    /**
     * 附件状态
     */
    private String fileStatus;

    /**
     * 扩展名
     */
    private String extName;

    /**
     * 上传ip
     */
    private String ip;

    /**
     * 上传时间
     */
    private String uploadDate;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

}
