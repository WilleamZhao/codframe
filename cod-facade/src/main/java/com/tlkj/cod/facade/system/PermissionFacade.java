/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.facade.system;

import com.tlkj.cod.model.system.dto.CodFramePermissionTreeDto;

import java.util.List;

/**
 * Desc 权限facade
 *
 * @author sourcod
 * @version 1.0
 * @className PermissionFacadeImpl
 * @date 2018/12/13 11:53 AM
 */
public interface PermissionFacade {

    /**
     * 根据userId获取权限
     * @param userId 用户id
     * @return 权限列表
     */
    List<CodFramePermissionTreeDto> getPermissionTreeByUserId(String userId);

    /**
     * 获取资源
     * @return 资源列表
     */
    CodFramePermissionTreeDto getResource();
}
