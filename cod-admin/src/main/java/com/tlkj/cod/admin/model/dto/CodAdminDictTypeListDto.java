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
import com.tlkj.cod.common.CodCommonModelConvert;
import com.tlkj.cod.common.annotation.CodModelExclude;
import lombok.Getter;
import lombok.Setter;

/**
 * Desc 字典类型List Dto
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminDictTypeListDto
 * @date 2018/11/14 1:18 PM
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CodAdminDictTypeListDto extends CodCommonModelConvert {
    private String id;
    private String typeCode;
    private String typeName;
    private String englishName;
    private String allPin;
    @CodModelExclude
    private String typeStatus;
    private String type;
    private String remark;

}
