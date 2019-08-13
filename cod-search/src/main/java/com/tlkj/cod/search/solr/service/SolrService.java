/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.search.solr.service;

import com.tlkj.cod.search.solr.model.GulianModel;

import java.util.List;

/**
 * Desc solrService
 *
 * @author sourcod
 * @version 1.0
 * @className SolrService
 * @date 2019/2/21 1:36 PM
 */
public interface SolrService {

    /**
     * 搜索
     * @param content 搜索内容
     * @param page    从几开始
     * @param rows    搜索多少行
     * @return
     */
    List<GulianModel> search(String content, int page, int rows);

    void add(GulianModel gulianModel);

}
