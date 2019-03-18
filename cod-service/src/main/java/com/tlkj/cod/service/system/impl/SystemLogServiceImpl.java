/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.service.system.impl;

import com.tlkj.cod.common.CodCommonDate;
import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Pagination;
import com.tlkj.cod.model.system.dto.CodFrameSystemLogDto;
import com.tlkj.cod.model.system.entity.CodFrameLogDo;
import com.tlkj.cod.service.system.SystemLogService;
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
 * @className SystemLogServiceImpl
 * @date 2018/12/7 12:43 PM
 */
@Service
public class SystemLogServiceImpl implements SystemLogService {

    @Autowired
    Finder finder;

    /**
     * 日志列表
     * @param ip        ip
     * @param username  用户名
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @param page      第几页
     * @param pageSize  每页显示几条
     * @return
     */
    @Override
    public Page<List<CodFrameSystemLogDto>> listLog(String ip, String username, String startDate, String endDate, String page, String pageSize) {
        Finder.Query query = finder.from(CodFrameLogDo.TABLE_NAME);
        if (StringUtils.isNotBlank(ip)){
            query.like("ip", "%" + ip + "%");
        }

        if (StringUtils.isNotBlank(username)){
            query.like("username", "%" + username + "%");
        }

        if (StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)){
            query.between("create_time", startDate, endDate);
        }

        int currentPage = StringUtils.isNotBlank(page) ? Integer.parseInt(page) : 1;
        int perPage = StringUtils.isNotBlank(pageSize) ? Integer.parseInt(pageSize) : Pagination.DEFAULT_PER_PAGE;

        Pagination<CodFrameLogDo> pagination = query.paginate(CodFrameLogDo.class, currentPage, perPage);
        List<CodFrameLogDo> codFrameLogDos = null;
        if (pagination != null){
            codFrameLogDos = pagination.getData();
        }
        if (codFrameLogDos == null){
            return null;
        }
        List<CodFrameSystemLogDto> logDtos = new ArrayList<>();
        codFrameLogDos.forEach(item -> {
            CodFrameSystemLogDto dto = new CodFrameSystemLogDto();
            dto.setContent(item.getContent());
            dto.setCreateTime(CodCommonDate.formatDate(item.getCreate_time()));
            dto.setId(item.getId());
            dto.setIp(item.getIp());
            dto.setMethodName(item.getMethod_name());
            dto.setOperationName(item.getOperation_name());
            dto.setOperationType(item.getOperation_type());
            dto.setParams(item.getParams());
            dto.setResults(item.getResults());
            dto.setUsername(item.getUsername());
            logDtos.add(dto);
        });

        return new Page<>(logDtos, pagination);
    }

    /**
     * 获取日志详情
     * @param id 日志id
     * @return
     */
    @Override
    public CodFrameSystemLogDto getLog(String id) {
        CodFrameLogDo codFrameLogDo = finder.from(CodFrameLogDo.TABLE_NAME).where("id", id).first(CodFrameLogDo.class);
        CodFrameSystemLogDto dto = new CodFrameSystemLogDto();
        dto.setId(codFrameLogDo.getId());
        dto.setContent(codFrameLogDo.getContent());
        dto.setParams(codFrameLogDo.getParams());
        dto.setOperationType(codFrameLogDo.getOperation_type());
        dto.setOperationName(codFrameLogDo.getOperation_name());
        dto.setMethodName(codFrameLogDo.getMethod_name());
        dto.setIp(codFrameLogDo.getIp());
        dto.setResults(codFrameLogDo.getResults());
        dto.setCreateTime(codFrameLogDo.getCreate_time());
        dto.setUsername(codFrameLogDo.getUsername());
        return dto;
    }
}
