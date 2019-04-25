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

import com.tlkj.cod.log.annotation.Log;
import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Pagination;
import com.tlkj.cod.dao.jdbc.Updater;
import com.tlkj.cod.model.business.jx.dto.JxBannerDto;
import com.tlkj.cod.model.business.jx.entity.JxBannerDo;
import com.tlkj.cod.model.common.SystemResponse;
import com.tlkj.cod.model.enums.StatusCode;
import com.tlkj.cod.service.jx.JxBannerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className JxBannerServiceImpl
 * @date 2019/3/25 2:37 PM
 */
@Service
public class JxBannerServiceImpl implements JxBannerService {

    @Autowired
    Finder finder;

    @Autowired
    Updater updater;

    @Log(name = "获取banner列表")
    @Override
    public SystemResponse list(String page, String pageSize) {
        SystemResponse response = new SystemResponse();
        Finder.Query query = finder.from(JxBannerDo.TABLE_NAME);
        Pagination<JxBannerDo> pagination = query.paginate(JxBannerDo.class, page, pageSize);
        if (pagination == null){
            return response.success(StatusCode.DATA_NULL_CODE);
        }
        List<JxBannerDo> jxBannerDos = pagination.getData();
        List<JxBannerDto> dtos = new ArrayList<>();
        jxBannerDos.forEach(item -> dtos.add(item.toDto(JxBannerDto.class)));
        return response.success(new Page<>(dtos, pagination));
    }

    @Log(name = "保存banner接口")
    @Override
    public SystemResponse save(String id, String img, String url, String type, String sort, String state) {
        SystemResponse response = new SystemResponse();
        Updater.Update update = StringUtils.isBlank(id) ? updater.insert(JxBannerDo.TABLE_NAME).setId() : updater.update(JxBannerDo.TABLE_NAME).where("id", id);
        if (StringUtils.isEmpty(img)){
            update.set("img", img);
        }
        if (StringUtils.isEmpty(url)){
            update.set("url", url);
        }
        if (StringUtils.isEmpty(type)){
            update.set("type", type);
        }
        if (StringUtils.isEmpty(sort)){
            update.set("sort", sort);
        }
        if (StringUtils.isEmpty(state)){
            update.set("state", state);
        }
        int i = update.update();
        if (i == 1){
            return response.success();
        }
        return response.success(StatusCode.DATA_NULL_CODE);
    }

    @Log(name = "获取banner详情接口")
    @Override
    public SystemResponse get(String id) {
        SystemResponse response = new SystemResponse();
        JxBannerDo jxBannerDo = finder.from(JxBannerDo.TABLE_NAME).where("id", id).first(JxBannerDo.class);

        if (jxBannerDo == null){
            return response.success(StatusCode.DATA_NULL_CODE);
        }

        jxBannerDo.toDto(JxBannerDto.class);
        return response.success(jxBannerDo.toDto(JxBannerDto.class));
    }

    @Log(name = "删除banner接口")
    @Override
    public SystemResponse del(String id) {
        SystemResponse response = new SystemResponse();
        int i = updater.delete(JxBannerDo.TABLE_NAME).where("id", id).update();
        if (i == 1){
            return response;
        }
        return response.fail();
    }
}
