/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.admin.service;

import com.tlkj.cod.admin.model.dto.CodAdminSystemLogDto;
import com.tlkj.cod.dao.bean.Page;

import java.util.List;

/**
 * Desc 系统日志service
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminSystemLogService
 * @date 2018/12/7 12:40 PM
 */
public interface CodAdminSystemLogService {

    /**
     * 日志列表
     * @param ip        ip
     * @param username  用户名
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return 日志列表
     */
    Page<List<CodAdminSystemLogDto>> listLog(String ip, String username, String startDate, String endDate, String page, String pageSize);


    /**
     * 获取日志详情
     * @param id
     * @return
     */
    CodAdminSystemLogDto getLog(String id);
}
