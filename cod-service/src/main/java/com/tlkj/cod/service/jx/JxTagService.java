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
 * @className JxTagService
 * @date 2019/3/24 6:20 PM
 */
public interface JxTagService {

    /**
     * 热门tag
     * @return
     */
    SystemResponse hotTag();

    /**
     * 获取tag
     */
    SystemResponse list(String shareId);

    /**
     * 保存tag
     */
    SystemResponse save(String shareId, String name);
}
