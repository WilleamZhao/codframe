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

import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Pagination;
import com.tlkj.cod.dao.jdbc.Updater;
import com.tlkj.cod.model.business.jx.dto.JxAdDto;
import com.tlkj.cod.model.business.jx.entity.JxAdDo;
import com.tlkj.cod.model.common.SystemResponse;
import com.tlkj.cod.model.enums.StatusCode;
import com.tlkj.cod.service.jx.JxAdService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className JxAdServiceImpl
 * @date 2019/3/25 9:49 PM
 */
@Service
public class JxAdServiceImpl implements JxAdService {

    @Autowired
    Finder finder;

    @Autowired
    Updater updater;

    @Override
    public SystemResponse save(String id, String title, String url, String startDate, String endDate) {
        Updater.Update update = StringUtils.isEmpty(id) ? updater.insert(JxAdDo.TABLE_NAME).setId() : updater.update(JxAdDo.TABLE_NAME).where("id", id);
        if (StringUtils.isNotEmpty(title)){
            update.set("title", title);
        }
        if (StringUtils.isNotEmpty(url)){
            update.set("url", url);
        }
        if (StringUtils.isNotEmpty(title)){
            update.set("title", title);
        }

        return null;
    }

    @Override
    public SystemResponse list(String page, String pageSize, String title) {
        SystemResponse response = new SystemResponse();
        Pagination<JxAdDo> pagination = finder.from(JxAdDo.TABLE_NAME).paginate(JxAdDo.class, page, pageSize);
        if (pagination.isNull()){
            return response.success(StatusCode.DATA_NULL_CODE);
        }
        List<JxAdDto> jxAdDtoList = new ArrayList<>();
        pagination.getData().forEach(item -> jxAdDtoList.add(item.toDto(JxAdDto.class)));
        return response.success(new Page(jxAdDtoList, pagination));
    }

    @Override
    public SystemResponse get(String id) {
        SystemResponse response = new SystemResponse();
        JxAdDo jxAdDo = finder.from(JxAdDo.TABLE_NAME).where("id", id).first(JxAdDo.class);
        if (jxAdDo == null){
            return response.success(StatusCode.DATA_NULL_CODE);
        }
        return response.success(jxAdDo.toDto(JxAdDto.class));
    }

    @Override
    public SystemResponse delete(String ids) {
        SystemResponse response = new SystemResponse();
        int i = updater.delete(JxAdDo.TABLE_NAME).in("id", ids.split(",")).update();
        if (i > 0){
            return response;
        }
        return response.fail();
    }
}
