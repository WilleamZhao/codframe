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
 * @className JxSearchHistoryService
 * @date 2019/3/26 11:52 AM
 */
public interface JxSearchHistoryService {

    SystemResponse all();

    SystemResponse save(String id, String content, String userId);
}
