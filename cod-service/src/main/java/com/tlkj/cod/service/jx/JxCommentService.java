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
 * Desc 评论service
 *
 * @author sourcod
 * @version 1.0
 * @className JxCommentService
 * @date 2019/3/25 7:02 PM
 */
public interface JxCommentService {


    SystemResponse save(String id, String content, String userId, String pId);

    SystemResponse get(String id);

    SystemResponse list(String page, String pageSize, String username, String content, String startDate, String endDate);

    SystemResponse del(String ids);

    SystemResponse audit(String id, String state);


}
