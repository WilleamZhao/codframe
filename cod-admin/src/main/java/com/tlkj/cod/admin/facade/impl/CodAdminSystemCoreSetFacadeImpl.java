/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.admin.facade.impl;

import com.tlkj.cod.admin.facade.CodAdminSystemCoreSetFacade;
import com.tlkj.cod.admin.model.dto.CodAdminSystemCoreSetDto;
import com.tlkj.cod.data.model.dto.CodDataConfigDto;
import com.tlkj.cod.data.service.CodDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Desc 系统核心设置
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminSystemCoreSetFacadeImpl
 * @date 2019/8/23 11:02 AM
 */
@Service
public class CodAdminSystemCoreSetFacadeImpl implements CodAdminSystemCoreSetFacade {

    @Autowired
    CodDataService codDataService;

    /**
     * 模块列表
     * @param key      key
     * @param name     配置名称
     * @param page     第几页
     * @param pageSize 每页大小
     * @return
     */
    @Override
    public List<CodAdminSystemCoreSetDto> list(String key, String name, String page, String pageSize) {
        CodDataConfigDto codDataConfigDto = codDataService.getData(key);
        return null;
    }
}
