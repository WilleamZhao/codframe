/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.admin.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * Desc 附件类型Dto
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminAttachmentTypeDto
 * @date 2018/11/30 7:00 PM
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CodAdminAttachmentTypeDto {

    /**
     * 主键
     */
    private String id;

    /**
     * 附件名称
     */
    private String name;

    /**
     * 附件代码
     */
    private String code;

    /**
     * 附件值
     */
    private String value;

}
