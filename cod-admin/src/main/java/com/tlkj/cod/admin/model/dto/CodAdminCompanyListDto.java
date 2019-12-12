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
 * Desc 公司列表Dto
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminCompanyListDto
 * @date 2018/10/30 下午4:16
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CodAdminCompanyListDto {

    private String id;
    private String companyName;
    private String companyNickName;
    private String companyNo;
    private String companyContact;
    private String companyFax;
    private String companyPhone;
    private String companyEin;
    private String companyAddress;
    private String sort;
    private String state;

}
