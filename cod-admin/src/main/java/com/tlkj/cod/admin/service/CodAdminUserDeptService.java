/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.admin.service;

import com.tlkj.cod.model.system.dto.CodFrameUserDeptListDto;

import java.util.List;

/**
 * Desc 用户部门Service
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminUserDeptService
 * @date 2019/1/7 11:49 PM
 */
public interface CodAdminUserDeptService {

    /**
     * 根据用户id获取部门列表
     * @param userId 用户id
     */
    List<CodFrameUserDeptListDto> listDeptByUserId(String userId);
}
