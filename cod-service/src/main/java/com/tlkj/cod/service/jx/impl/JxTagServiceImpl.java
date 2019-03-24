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

import com.tlkj.cod.core.annotation.Log;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.model.business.jx.entity.JxTagsDo;
import com.tlkj.cod.model.common.SystemResponse;
import com.tlkj.cod.service.jx.JxTagService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Desc 精享标签service
 *
 * @author sourcod
 * @version 1.0
 * @className JxTagServiceImpl
 * @date 2019/3/24 6:22 PM
 */
@Service
public class JxTagServiceImpl implements JxTagService {

    @Autowired
    Finder finder;

    @Log(name = "热门标签")
    @Override
    public SystemResponse hotTag() {
        return null;
    }

    /**
     * 获取标签列表接口
     * TODO 未完成
     * @param shareId
     * @return
     */
    @Log(name = "获取分享标签列表")
    @Override
    public SystemResponse list(String shareId) {
        Finder.Query query = finder.from(JxTagsDo.TABLE_NAME);
        if (StringUtils.isEmpty(shareId)){
            List<JxTagsDo> doList = query.all(JxTagsDo.class);
        } else {
            query.where("id", shareId).first(JxTagsDo.class);
        }



        return null;
    }

    @Log(name = "保存标签")
    @Override
    public SystemResponse save(String shareId, String name) {
        return null;
    }
}
