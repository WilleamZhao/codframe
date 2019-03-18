/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.service.system;

import com.tlkj.cod.model.system.dto.CodFramePermissionListDto;

import java.util.List;

/**
 * Desc 资源Service
 *
 * @author sourcod
 * @version 1.0
 * @className ResourceService
 * @date 2018/11/7 下午10:30
 */
public interface ResourceService {

    List<CodFramePermissionListDto> listResource();
}
