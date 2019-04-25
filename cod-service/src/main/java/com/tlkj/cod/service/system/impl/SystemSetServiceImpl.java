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

import com.tlkj.cod.core.main.Start;
import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Pagination;
import com.tlkj.cod.dao.jdbc.Updater;
import com.tlkj.cod.log.service.LogService;
import com.tlkj.cod.model.enums.StatusCode;
import com.tlkj.cod.model.system.core.SystemModel;
import com.tlkj.cod.model.system.dto.CodFrameSetDto;
import com.tlkj.cod.model.system.entity.CodFrameSetDo;
import com.tlkj.cod.service.system.SystemSetService;
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
 * @className SystemSetServiceImpl
 * @date 2018/11/27 8:08 PM
 */
@Service
public class SystemSetServiceImpl implements SystemSetService {

    private static SystemModel model = SystemModel.getInstance();

    private static Logger logger = LoggerFactory.getLogger(SystemSetServiceImpl.class);

    @Autowired
    Finder finder;

    @Autowired
    Updater updater;

    @Autowired
    LogService logServices;

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
        int num = finder.from(CodFrameSetDo.TABLE_NAME).where("id", id).select("count(*)").firstForObject(Integer.class);
        Updater.Update update = num == 0 ? updater.insert(CodFrameSetDo.TABLE_NAME) : updater.update(CodFrameSetDo.TABLE_NAME).where("id", id);

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
    public CodFrameSetDto getSet(String id) {
        CodFrameSetDo codFrameSetDo = finder.from(CodFrameSetDo.TABLE_NAME).where("id", id).first(CodFrameSetDo.class);
        return convert(codFrameSetDo);
    }

    /**
     * 获取设置Value
     * @param setCode 设置代码
     * @return 设置值
     */
    @Override
    public String getSetValue(String setCode) {
        CodFrameSetDo setDo = finder.from(CodFrameSetDo.TABLE_NAME).where("set_code", setCode).first(CodFrameSetDo.class);
        return setDo.getSet_value();
    }

    @Override
    public Page<List<CodFrameSetDto>> listSet() {
        Pagination<CodFrameSetDo> pagination = finder.from(CodFrameSetDo.TABLE_NAME).paginate(CodFrameSetDo.class, 1, 100);
        List<CodFrameSetDto> list = new ArrayList<>();
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
     * @return LogService 日志服务
     */
    @Override
    public LogService getLog() {
        return logServices;
    }

    /**
     * 转换Do to Dto
     */
    private CodFrameSetDto convert(CodFrameSetDo codFrameSetDo){
        CodFrameSetDto dto = new CodFrameSetDto();
        dto.setId(codFrameSetDo.getId());
        dto.setSetCode(codFrameSetDo.getSet_code());
        dto.setSetName(codFrameSetDo.getSet_name());
        dto.setSetValue(codFrameSetDo.getSet_value());
        dto.setSort(codFrameSetDo.getSort());
        dto.setUserId(codFrameSetDo.getUser_id());
        dto.setUpdateTime(codFrameSetDo.getUpdate_time());
        return dto;
    }
}
