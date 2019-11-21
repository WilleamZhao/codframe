/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.admin.service.impl;

import com.tlkj.cod.admin.service.CodAdminSystemDataSetService;
import com.tlkj.cod.data.model.dto.CodDataConfigDto;
import com.tlkj.cod.data.service.CodDataService;
import com.tlkj.cod.log.service.CodLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Desc 系统数据设置 service impl
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminSystemDataSetServiceImpl
 * @date 2019/8/22 6:26 PM
 */
@Service
public class CodAdminSystemDataSetServiceImpl implements CodAdminSystemDataSetService {

    @Autowired
    CodDataService codDataService;

    @Autowired
    CodLogService codLogService;

    @Override
    public List list(String key, String name, String page, String pageSize) {
        List list;
        list = codDataService.getConfig().entrySet().stream().map(e ->  {
            Map<String, String> map = new HashMap<>();
            map.put(e.getKey(), e.getValue());
            return map;
        }).collect(Collectors.toList());
        return list;
    }

    @Override
    public CodDataConfigDto get(String key) {
        CodDataConfigDto codDataConfigDto = codDataService.getData(key);
        return codDataConfigDto;
    }

    @Override
    public boolean delete(String key) {
        try {
            codDataService.delete(key);
            return true;
        } catch (Exception e){
            codLogService.error("删除核心数据失败", e);
        }
        return false;
    }

    @Override
    public boolean save(String id, String key, String value, String name, String sort) {
        try {
            codDataService.setData(key, value, name, sort);
            return true;
        } catch (Exception e){
            codLogService.error("保存核心数据失败", e);
        }
        return false;
    }
}
