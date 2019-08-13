/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.admin.model.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Desc 用户部门dto
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminUserDeptListDto
 * @date 2019/1/7 11:57 PM
 */
@Getter
@Setter
public class CodAdminUserDeptListDto {

    private String id;
    private String userId;
    private String deptId;
    private String userName;
    private String deptName;

}
