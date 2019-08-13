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
 * Desc 字典数据List Dto
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminDictItemListDto
 * @date 2018/11/14 1:18 PM
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CodAdminDictItemListDto {
    private String id;
    private String typeId;
    private String itemName;
    private String itemCode;
    private String itemValue;
    private String itemStatus;
    private String type;
    private String isFixed;
    private String englishName;
    private String allPin;
    private String simplePin;
    private String sort;
    private String remark;

}
