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

import com.tlkj.cod.admin.model.dto.CodAdminSetDto;
import com.tlkj.cod.admin.model.entity.CodAdminSetDo;
import com.tlkj.cod.admin.service.CodAdminSystemSetService;
import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Pagination;
import com.tlkj.cod.dao.jdbc.Updater;
import com.tlkj.cod.log.service.CodLogService;
import com.tlkj.cod.model.enums.StatusCode;
import com.tlkj.cod.model.system.core.SystemModel;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc 系统设置ServiceImpl
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminSystemSetServiceImpl
 * @date 2018/11/27 8:08 PM
 */
@Service
public class CodAdminSystemSetServiceImpl implements CodAdminSystemSetService {

    private static Logger logger = LoggerFactory.getLogger(CodAdminSystemSetServiceImpl.class);

    @Autowired
    Finder finder;

    @Autowired
    Updater updater;

    @Autowired
    CodLogService codLogServices;

    /**
     * 保存设置
     * @param id       主键
     * @param setName  设置名称
     * @param setCode  设置代码
     * @param setValue 设置值
     * @param userId   操作用户id
     * @return
     */
    @Override
    public StatusCode saveSet(String id, String setName, String setCode, String setValue, String userId) {
        int num = finder.from(CodAdminSetDo.TABLE_NAME).where("id", id).select("count(*)").firstForObject(Integer.class);
        Updater.Update update = num == 0 ? updater.insert(CodAdminSetDo.TABLE_NAME) : updater.update(CodAdminSetDo.TABLE_NAME).where("id", id);

        if (StringUtils.isNotBlank(setName)){
            update.set("set_name", setName);
        }

        if (StringUtils.isNotBlank(setCode)){
            update.set("set_code", setCode);
        }

        if (StringUtils.isNotBlank(setCode)){
            update.set("set_value", setValue);
        }

        if (StringUtils.isNotBlank(userId)){
            update.set("user_id", userId);
        }
        return update.update() == 1 ? StatusCode.SUCCESS_CODE : StatusCode.FAIL_CODE;
    }

    /**
     * 获取设置
     * @param id       主键
     * @return
     */
    @Override
    public CodAdminSetDto getSet(String id) {
        CodAdminSetDo codAdminSetDo = finder.from(CodAdminSetDo.TABLE_NAME).where("id", id).first(CodAdminSetDo.class);
        return convert(codAdminSetDo);
    }

    /**
     * 获取设置Value
     * @param setCode 设置代码
     * @return 设置值
     */
    @Override
    public String getSetValue(String setCode) {
        CodAdminSetDo setDo = finder.from(CodAdminSetDo.TABLE_NAME).where("set_code", setCode).first(CodAdminSetDo.class);
        return setDo.getSet_value();
    }

    @Override
    public Page<List<CodAdminSetDto>> listSet() {
        Pagination<CodAdminSetDo> pagination = finder.from(CodAdminSetDo.TABLE_NAME).paginate(CodAdminSetDo.class, 1, 100);
        List<CodAdminSetDto> list = new ArrayList<>();
        pagination.getData().forEach(item -> {
            list.add(convert(item));
        });
        return new Page<>(list, pagination);
    }

    /**
     * 获取日志设置
     * 日志支持的类型 clog(默认), slf4j, logback, aliyunLog
     * 1. 默认获取配置文件设置
     * 2. 配置文件没有获取数据库设置
     * 3. 数据库没有默认采用clog
     * @return CodLogService 日志服务
     */
    @Override
    public CodLogService getLog() {
        return codLogServices;
    }

    /**
     * 转换Do to Dto
     */
    private CodAdminSetDto convert(CodAdminSetDo codAdminSetDo){
        CodAdminSetDto dto = new CodAdminSetDto();
        dto.setId(codAdminSetDo.getId());
        dto.setSetCode(codAdminSetDo.getSet_code());
        dto.setSetName(codAdminSetDo.getSet_name());
        dto.setSetValue(codAdminSetDo.getSet_value());
        dto.setSort(codAdminSetDo.getSort());
        dto.setUserId(codAdminSetDo.getUser_id());
        dto.setUpdateTime(codAdminSetDo.getUpdate_time());
        return dto;
    }
}
