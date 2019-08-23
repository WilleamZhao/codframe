/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.admin.service;

import com.tlkj.cod.data.model.dto.CodDataConfigDto;

import java.util.List;

/**
 * Desc 系统数据设置
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminSystemDataSetService
 * @date 2019/8/22 6:17 PM
 */
public interface CodAdminSystemDataSetService {

    /**
     * 系统数据列表
     * @param key      key
     * @param name     名称
     * @param page     第几页
     * @param pageSize 每页几条
     * @return
     */
    List list(String key, String name, String page, String pageSize);

    /**
     * 获取系统数据详情
     * @param id 主键
     * @return
     */
    CodDataConfigDto get(String id);

    /**
     * 删除系统数据
     * @param id 主键
     * @return 是否删除成功
     */
    boolean delete(String id);

    /**
     * 保存系统数据
     * @param id    主键
     * @param key   key
     * @param value value
     * @param name  名称
     * @param sort  序号
     * @return 是否保存成功
     */
    boolean save(String id, String key, String value, String name, String sort);


}
