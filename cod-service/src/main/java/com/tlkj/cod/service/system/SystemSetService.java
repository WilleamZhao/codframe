/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.service.system;

import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.model.enums.StatusCode;
import com.tlkj.cod.model.system.dto.CodFrameSetDto;

import java.util.List;

/**
 * Desc 系统设置Service
 *
 * @author sourcod
 * @version 1.0
 * @className SystemSetService
 * @date 2018/11/27 7:56 PM
 */
public interface SystemSetService {


    /**
     * 保存系统设置
     * @param id       主键
     * @param setName  设置名称
     * @param setCode  设置代码
     * @param setValue 设置值
     * @param userId   操作用户id
     * @return
     */
    StatusCode saveSet(String id, String setName, String setCode, String setValue, String userId);

    /**
     * 获取系统设置
     * @param id       主键
     */
    CodFrameSetDto getSet(String id);

    /**
     * 获取设置值
     * @param setCode 设置代码
     * @return 设置值
     */
    String getSetValue(String setCode);

    /**
     * 获取全部设置
     */
    Page<List<CodFrameSetDto>> listSet();

    /**
     * 获取日志设置
     * @return
     */
    LogService getLog();
}
