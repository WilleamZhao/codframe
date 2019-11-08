/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.admin.service;

import com.tlkj.cod.admin.model.dto.CodAdminApiDto;
import com.tlkj.cod.dao.bean.Page;

import java.util.List;

/**
 * Desc 接口管理Service
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminApiService
 * @date 2019/10/10 11:09 AM
 */
public interface CodAdminApiService {

    /**
     * 接口列表
     * @param name     接口名称
     * @param type     接口类型
     * @param page     第几页
     * @param pageSize 每页显示条数
     * @return
     */
    Page<List<CodAdminApiDto>> list(String name, String type, String page, String pageSize);
}
