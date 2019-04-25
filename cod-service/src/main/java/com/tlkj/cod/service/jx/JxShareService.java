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
 * Desc 分享service
 *
 * @author sourcod
 * @version 1.0
 * @className JxShareService
 * @date 2019/3/24 4:56 PM
 */
public interface JxShareService {

    /**
     * 保存
     */
    SystemResponse save(String id, String img, String title, String intro, String content, String previewUrl, String downloadUrl,
                        String fine, String author, String zanNum, String commentNum, String previewNum, String price, String top,
                        String sort, String recommend);

    /**
     * 删除
     */
    SystemResponse del(String ids);

    /**
     * 列表
     * @param page     第几页
     * @param pageSize 每页大小
     * @return
     */
    SystemResponse list(String page, String pageSize);

    /**
     * 详情
     * @param id 分享id
     * @return
     */
    SystemResponse get(String id);

    /**
     * 下载
     * @param id
     * @param userId
     * @return
     */
    SystemResponse download(String id, String userId);

    /**
     * 预览
     */
    SystemResponse preview(String id, String userId);
}
