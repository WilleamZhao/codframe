/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.admin.service.impl;

import com.tlkj.cod.admin.model.dto.CodAdminApiDto;
import com.tlkj.cod.admin.model.entity.CodAdminApiDo;
import com.tlkj.cod.admin.service.CodAdminApiService;
import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Updater;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Desc 接口管理 service
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminApiServiceImpl
 * @date 2019/10/12 5:57 PM
 */
@Service
public class CodAdminApiServiceImpl implements CodAdminApiService {

    private Finder finder;
    private Updater updater;

    @Autowired
    public CodAdminApiServiceImpl(Finder finder, Updater updater){
        this.finder = finder;
        this.updater = updater;
    }

    @Override
    public Page<List<CodAdminApiDto>> list(String name, String type, String page, String pageSize) {
        Finder.Query query = finder.from(CodAdminApiDo.TABLE_NAME);
        if (StringUtils.isNotBlank(name)){
            query.like("name", "%" + name + "%");
        }

        if (StringUtils.isNotBlank(type)){
            query.where("type", type);
        }
        return null;
    }

    public boolean save(String id, CodAdminApiDto codAdminApiDto){

        return false;
    }
}
