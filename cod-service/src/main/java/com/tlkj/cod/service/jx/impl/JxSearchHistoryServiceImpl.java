/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.service.jx.impl;

import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Updater;
import com.tlkj.cod.model.business.jx.dto.JxSearchHistoryDto;
import com.tlkj.cod.model.business.jx.entity.JxSearchHistoryDo;
import com.tlkj.cod.model.common.SystemResponse;
import com.tlkj.cod.service.jx.JxSearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className JxSearchHistoryServiceImpl
 * @date 2019/3/26 11:53 AM
 */
@Service
public class JxSearchHistoryServiceImpl implements JxSearchHistoryService {

    @Autowired
    Finder finder;

    @Autowired
    Updater updater;

    @Override
    public SystemResponse all() {
        SystemResponse response = new SystemResponse();
        List<JxSearchHistoryDo> jxSearchHistoryDos = finder.from(JxSearchHistoryDo.TABLE_NAME).all(JxSearchHistoryDo.class);
        if (jxSearchHistoryDos == null){
            return response;
        }
        List<JxSearchHistoryDto> list = new ArrayList<>();
        jxSearchHistoryDos.forEach(item -> list.add(item.toDto(JxSearchHistoryDto.class)));
        return response.success(list);
    }

    @Override
    public SystemResponse save(String id, String content, String userId) {

        return null;
    }
}
