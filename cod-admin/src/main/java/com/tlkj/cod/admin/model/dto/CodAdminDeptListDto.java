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
 * Desc 部门列表Dto
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminDeptListDto
 * @date 2018/11/7 下午11:05
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CodAdminDeptListDto {
    private String id;
    private String deptName;
    private String deptNo;
    private String deptLevel;
    private String deptAdmin;
    private String pId;
    private String companyId;
    private String companyName;
    private String status;
    private String sort;

}
