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
import com.tlkj.cod.model.system.dto.CodFrameSystemLogDto;

import java.util.List;

/**
 * Desc 系统日志service
 *
 * @author sourcod
 * @version 1.0
 * @className SystemLogService
 * @date 2018/12/7 12:40 PM
 */
public interface SystemLogService {

    /**
     * 日志列表
     * @param ip        ip
     * @param username  用户名
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return 日志列表
     */
    Page<List<CodFrameSystemLogDto>> listLog(String ip, String username, String startDate, String endDate, String page, String pageSize);


    /**
     * 获取日志详情
     * @param id
     * @return
     */
    CodFrameSystemLogDto getLog(String id);
}
