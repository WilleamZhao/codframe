/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.admin.facade;

import com.tlkj.cod.admin.model.dto.CodAdminPermissionTreeDto;

import java.util.List;

/**
 * Desc 权限facade
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminPermissionFacadeImpl
 * @date 2018/12/13 11:53 AM
 */
public interface CodAdminPermissionFacade {

    /**
     * 根据userId获取权限
     * @param userId 用户id
     * @return 权限列表
     */
    List<CodAdminPermissionTreeDto> getPermissionTreeByUserId(String userId);

    /**
     * 获取资源
     * @return 资源列表
     */
    CodAdminPermissionTreeDto getResource();
}
