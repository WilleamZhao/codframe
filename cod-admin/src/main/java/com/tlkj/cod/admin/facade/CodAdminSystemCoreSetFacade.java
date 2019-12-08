/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.admin.facade;

import com.tlkj.cod.admin.model.dto.CodAdminSystemCoreSetDto;

import java.util.List;

/**
 * Desc 系统核心配置 facade
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminSystemCoreSetFacade
 * @date 2019/8/23 11:01 AM
 */
public interface CodAdminSystemCoreSetFacade {

    /**
     * 核心配置列表
     * @param key      key
     * @param name     配置名称
     * @param page     第几页
     * @param pageSize 每页大小
     * @return 列表
     */
    List<CodAdminSystemCoreSetDto> list(String key, String name, String page, String pageSize);
}
