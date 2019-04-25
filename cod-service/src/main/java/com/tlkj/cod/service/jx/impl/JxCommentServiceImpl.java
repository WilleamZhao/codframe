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
import com.tlkj.cod.log.service.LogService;
import com.tlkj.cod.model.business.jx.dto.JxCommentDto;
import com.tlkj.cod.model.business.jx.entity.JxCommentDo;
import com.tlkj.cod.model.common.SystemResponse;
import com.tlkj.cod.model.enums.StatusCode;
import com.tlkj.cod.service.jx.JxCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className JxCommentServiceImpl
 * @date 2019/3/25 7:46 PM
 */
@Service
public class JxCommentServiceImpl implements JxCommentService {

    @Autowired
    Finder finder;

    @Autowired
    Updater updater;

    @Autowired
    LogService logService;

    @Log(name = "保存评论")
    @Override
    public SystemResponse save(String id, String content, String userId, String pId) {

        return null;
    }

    @Log(name = "获取评论详情")
    @Override
    public SystemResponse get(String id) {
        SystemResponse response = new SystemResponse();
        JxCommentDo jxCommentDo = finder.from(JxCommentDo.TABLE_NAME).where("id", id).first(JxCommentDo.class);
        if (jxCommentDo == null){
            return response;
        }
        return response.success(jxCommentDo.toDto(JxCommentDto.class));
    }

    @Log(name = "获取评论列表")
    @Override
    public SystemResponse list(String page, String pageSize, String username, String content, String startDate, String endDate) {
        SystemResponse response = new SystemResponse();

        Finder.Query query = finder.from(JxCommentDo.TABLE_NAME);
        List<JxCommentDo> list = null;

        Pagination pagination = null;
        try {
            pagination = query.paginate(JxCommentDo.class, page, pageSize);
        } catch (Exception e){
            logService.error("获取评论列表错误", e);
            return response.fail();
        }
        if (pagination.isNull()){
            return response.success(StatusCode.DATA_NULL_CODE);
        }
        List<JxCommentDto> jxCommentDtos = new ArrayList<>();
        list.forEach(item -> jxCommentDtos.add(item.toDto(JxCommentDto.class)));
        return response.success(new Page(jxCommentDtos, pagination));
    }

    @Log(name = "删除评论")
    @Override
    public SystemResponse del(String ids) {
        SystemResponse response = new SystemResponse();
        int i = updater.delete(JxCommentDo.TABLE_NAME).in("id", ids.split(",")).update();
        if (i > 0){
            return response.success();
        }
        return response.fail();
    }

    @Log(name = "审核评论")
    @Override
    public SystemResponse audit(String id, String state) {
        SystemResponse response = new SystemResponse();
        int i = updater.update(JxCommentDo.TABLE_NAME).set("state", state).where("id", id).update();
        if (i == 1){
            return response;
        }
        return response.fail();
    }
}
