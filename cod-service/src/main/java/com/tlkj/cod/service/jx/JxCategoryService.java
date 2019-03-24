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
 * Desc 精享分类service
 *
 * @author sourcod
 * @version 1.0
 * @className JxCategoryService
 * @date 2019/3/24 6:01 PM
 */
public interface JxCategoryService {

    /**
     * 获取分类
     * @param id 分类id
     * @return
     */
    SystemResponse getCategory(String id);
}
