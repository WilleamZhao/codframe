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
import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Pagination;
import com.tlkj.cod.log.service.LogService;
import com.tlkj.cod.model.business.jx.dto.JxShareDto;
import com.tlkj.cod.model.business.jx.entity.JxShareDo;
import com.tlkj.cod.model.common.SystemResponse;
import com.tlkj.cod.model.enums.StatusCode;
import com.tlkj.cod.service.jx.JxShareService;
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
 * @className JxShareServiceImpl
 * @date 2019/3/24 4:56 PM
 */
@Service
public class JxShareServiceImpl implements JxShareService {

    @Autowired
    LogService logService;

    @Autowired
    Finder finder;

    @Log(name = "获取分享列表")
    @Override
    public SystemResponse list(String page, String pageSize) {
        SystemResponse response = new SystemResponse();
        Page<List<JxShareDto>> listPage = new Page<>();
        Finder.Query query = finder.from(JxShareDo.TABLE_NAME);
        Pagination<JxShareDo> jxShareDoPagination;
        try {
            jxShareDoPagination = query.paginate(JxShareDo.class, page, pageSize);
        } catch (Exception e) {
            logService.error("sql查询错误:={}", e.getMessage());
            return null;
        }

        if (jxShareDoPagination == null) {
            return response.success(listPage);
        }

        List<JxShareDto> list = new ArrayList<>();
        jxShareDoPagination.getData().forEach(item -> {
            list.add(item.toDto(JxShareDto.class));
        });
        return response.success(new Page(list, jxShareDoPagination));
    }

    @Log(name = "获取分享详情")
    @Override
    public SystemResponse get(String id) {
        SystemResponse response = new SystemResponse();
        JxShareDo jxShareDo = finder.from(JxShareDo.TABLE_NAME).where("id", id).first(JxShareDo.class);
        return jxShareDo == null ? response.success(StatusCode.DATA_NULL_CODE) : response.success(jxShareDo.toDto(JxShareDto.class));
    }

    @Log(name = "下载")
    @Override
    public SystemResponse download(String id, String userId) {
        SystemResponse response = new SystemResponse();

        if (StringUtils.isEmpty(userId)){
            return response.fail(StatusCode.LOGIN_FAIL_CODE);
        }

        // TODO 记录下载次数, 或限制
        JxShareDo jxShareDo = finder.from(JxShareDo.TABLE_NAME).where("id", id).first(JxShareDo.class);
        if (jxShareDo == null){
            return response.success(StatusCode.DATA_NULL_CODE);
        }
        JxShareDto dto = jxShareDo.toDto(JxShareDto.class);
        String url = dto.getDownloadUrl();
        return StringUtils.isEmpty(url) ? response.success(StatusCode.DATA_NULL_CODE) : response.success(url);
    }

    @Log(name = "预览")
    @Override
    public SystemResponse preview(String id, String userId) {

        return null;
    }
}
