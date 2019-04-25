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
 * @className JxBannerService
 * @date 2019/3/25 2:35 PM
 */
public interface JxBannerService {

    SystemResponse list(String page, String pageSize);

    SystemResponse save(String id, String img, String url, String type, String sort, String state);

    SystemResponse get(String id);

    SystemResponse del(String id);
}
