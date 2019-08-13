/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.admin.service;

import com.tlkj.cod.admin.model.dto.CodAdminPermissionListDto;

import java.util.List;

/**
 * Desc 资源Service
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminResourceService
 * @date 2018/11/7 下午10:30
 */
public interface CodAdminResourceService {

    List<CodAdminPermissionListDto> listResource();
}
