/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.service.jx;

import com.tlkj.cod.model.common.SystemResponse;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className JxAdService
 * @date 2019/3/25 9:46 PM
 */
public interface JxAdService {

    /**
     * 保存
     * @param id
     * @param title
     * @param url
     * @param startDate
     * @param endDate
     * @return
     */
    SystemResponse save(String id, String title, String url, String startDate, String endDate);

    SystemResponse list(String page, String pageSize, String title);

    SystemResponse get(String id);

    SystemResponse delete(String ids);
}
