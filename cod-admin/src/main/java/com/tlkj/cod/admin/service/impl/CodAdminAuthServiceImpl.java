/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.admin.service.impl;

import com.tlkj.cod.admin.model.entity.CodAdminApiDo;
import com.tlkj.cod.admin.service.CodAdminAuthService;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Updater;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Desc 权限验证 service impl
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminAuthServiceImpl
 * @date 2019/11/9 12:40 PM
 */
@Service
public class CodAdminAuthServiceImpl implements CodAdminAuthService {

    private Finder finder;

    @Autowired
    public CodAdminAuthServiceImpl(Finder finder, Updater updater){
        this.finder = finder;
    }


    @Override
    public String getToken(String username, String password) {
        if (StringUtils.isBlank(username)){
            return "";
        }

        if (StringUtils.isBlank(password)){
            return "";
        }

        Finder.Query query = finder.from(CodAdminApiDo.TABLE_NAME);
        query.where("username", username);
        query.where("password", password);
        CodAdminApiDo codAdminApiDo = query.first(CodAdminApiDo.class);
        return null;
    }

    @Override
    public String verify() {
        return null;
    }
}
