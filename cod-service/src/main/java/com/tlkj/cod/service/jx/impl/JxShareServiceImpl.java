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

    @Autowired
    Updater updater;

    @Override
    public SystemResponse save(String id, String img, String title, String intro, String content, String previewUrl, String downloadUrl, String fine, String author, String zanNum, String commentNum, String previewNum, String price, String top, String sort, String recommend) {
        SystemResponse response = new SystemResponse();
        Updater.Update update = StringUtils.isEmpty(id) ? updater.insert(JxShareDo.TABLE_NAME).setId() : updater.update(JxShareDo.TABLE_NAME).where("id", id);

        if (StringUtils.isNotEmpty(img)){
            update.set("img", img);
        }
        if (StringUtils.isNotEmpty(title)){
            update.set("title", title);
        }
        if (StringUtils.isNotEmpty(intro)){
            update.set("intro", intro);
        }
        if (StringUtils.isNotEmpty(content)){
            update.set("content", content);
        }
        if (StringUtils.isNotEmpty(previewUrl)){
            update.set("preview_url", previewUrl);
        }
        if (StringUtils.isNotEmpty(downloadUrl)){
            update.set("download_url", downloadUrl);
        }
        if (StringUtils.isNotEmpty(fine)){
            update.set("fine", fine);
        }
        if (StringUtils.isNotEmpty(author)){
            update.set("author", author);
        }
        if (StringUtils.isNotEmpty(zanNum)){
            update.set("zan_num", zanNum);
        }
        if (StringUtils.isNotEmpty(commentNum)){
            update.set("comment_num", commentNum);
        }
        if (StringUtils.isNotEmpty(previewNum)){
            update.set("preview_num", previewNum);
        }
        if (StringUtils.isNotEmpty(price)){
            update.set("price", price);
        }
        if (StringUtils.isNotEmpty(top)){
            update.set("top", top);
        }
        if (StringUtils.isNotEmpty(sort)){
            update.set("sort", sort);
        }
        if (StringUtils.isNotEmpty(recommend)){
            update.set("recommend", recommend);
        }
        int i = 0;
        try {
            i = update.update();
        } catch (Exception e){
            e.printStackTrace();
            return response.fail();
        }
        if (i == 1){
            return response.success(true);
        }
        return response.fail(false);
    }

    @Override
    public SystemResponse del(String ids) {
        SystemResponse response = new SystemResponse();
        if (StringUtils.isNotBlank(ids)){
            int i = updater.delete(JxShareDo.TABLE_NAME).in("id", ids.split(",")).update();
            if (i > 0){
                return response;
            }
        }
        return response.fail();
    }

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
        try {
            jxShareDoPagination.getData().forEach(item -> list.add(item.toDto(JxShareDto.class)));
        } catch (Exception e){
            e.printStackTrace();
            return response.fail();
        }
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
        SystemResponse response = new SystemResponse();
        if (StringUtils.isBlank(userId)){
            return response.fail(StatusCode.LOGIN_FAIL_CODE);
        }

        // TODO 记录预览次数, 或限制等
        JxShareDo jxShareDo = finder.from(JxShareDo.TABLE_NAME).where("id", id).first(JxShareDo.class);
        if (jxShareDo == null){
            return response.success(StatusCode.DATA_NULL_CODE);
        }

        JxShareDto dto = jxShareDo.toDto(JxShareDto.class);
        String url = dto.getPreviewUrl();
        return StringUtils.isEmpty(url) ? response.success(StatusCode.DATA_NULL_CODE) : response.success(url);
    }
}
