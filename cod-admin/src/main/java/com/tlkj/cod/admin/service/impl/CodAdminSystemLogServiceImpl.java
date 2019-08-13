/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.admin.service.impl;

import com.tlkj.cod.admin.model.dto.CodAdminSystemLogDto;
import com.tlkj.cod.admin.model.entity.CodAdminLogDo;
import com.tlkj.cod.admin.service.CodAdminSystemLogService;
import com.tlkj.cod.common.CodCommonDate;
import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Pagination;

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
 * @className CodAdminSystemLogServiceImpl
 * @date 2018/12/7 12:43 PM
 */
@Service
public class CodAdminSystemLogServiceImpl implements CodAdminSystemLogService {

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
    public Page<List<CodAdminSystemLogDto>> listLog(String ip, String username, String startDate, String endDate, String page, String pageSize) {
        Finder.Query query = finder.from(CodAdminLogDo.TABLE_NAME);
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

        Pagination<CodAdminLogDo> pagination = query.paginate(CodAdminLogDo.class, currentPage, perPage);
        List<CodAdminLogDo> codAdminLogDos = null;
        if (pagination != null){
            codAdminLogDos = pagination.getData();
        }
        if (codAdminLogDos == null){
            return null;
        }
        List<CodAdminSystemLogDto> logDtos = new ArrayList<>();
        codAdminLogDos.forEach(item -> {
            CodAdminSystemLogDto dto = new CodAdminSystemLogDto();
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
    public CodAdminSystemLogDto getLog(String id) {
        CodAdminLogDo codAdminLogDo = finder.from(CodAdminLogDo.TABLE_NAME).where("id", id).first(CodAdminLogDo.class);
        CodAdminSystemLogDto dto = new CodAdminSystemLogDto();
        dto.setId(codAdminLogDo.getId());
        dto.setContent(codAdminLogDo.getContent());
        dto.setParams(codAdminLogDo.getParams());
        dto.setOperationType(codAdminLogDo.getOperation_type());
        dto.setOperationName(codAdminLogDo.getOperation_name());
        dto.setMethodName(codAdminLogDo.getMethod_name());
        dto.setIp(codAdminLogDo.getIp());
        dto.setResults(codAdminLogDo.getResults());
        dto.setCreateTime(codAdminLogDo.getCreate_time());
        dto.setUsername(codAdminLogDo.getUsername());
        return dto;
    }
}
