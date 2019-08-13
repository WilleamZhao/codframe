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
 * Desc 角色列表Dto
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminRoleListDto
 * @date 2018/10/30 下午2:26
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CodAdminRoleListDto {

    private String id;
    private String roleName;
    private String roleDesc;
    private String roleRemark;
    private String status;
    private String sort;
    private String createTime;
    private String updateTime;

}
