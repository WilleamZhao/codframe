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
 * Desc 公司Dto
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminCompanyDto
 * @date 2018/10/30 下午4:15
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CodAdminCompanyDto {

    /**
     * 公司主键
     */
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
    private String createTime;
    private String updateTime;

}
