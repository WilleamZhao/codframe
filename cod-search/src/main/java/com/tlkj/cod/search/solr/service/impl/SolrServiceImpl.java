/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.search.solr.service.impl;

import com.tlkj.cod.search.solr.model.GulianModel;
import com.tlkj.cod.search.solr.service.SolrService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Desc solr搜索
 *
 * @author sourcod
 * @version 1.0
 * @className SolrServiceImpl
 * @date 2019/2/21 1:38 PM
 */
@Component
public class SolrServiceImpl implements SolrService {

    private static String url = "http://47.110.248.80:9900/solr/gulian";
    private static HttpSolrClient solrClient = new HttpSolrClient.Builder(url).build();

    /**
     * 全文搜索
     * @param content 关键字
     * @return
     */
    @Override
    public List<GulianModel> search(String content, int page, int rows) {
        SolrQuery solrQuery = new SolrQuery();

        solrQuery.setQuery("content:"+content);
        // 高亮
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("content");
        solrQuery.setHighlightSimplePre("<span>");
        solrQuery.setHighlightSimplePost("<span>");
        solrQuery.setHighlightSnippets(1);
        solrQuery.setHighlightFragsize(40);

        solrQuery.setStart((Math.max(page, 1) - 1) * rows);
        solrQuery.setRows(rows);
        QueryResponse query = null;
        try {
            query = solrClient.query(solrQuery);
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
        if (query == null){
            return null;
        }
        List<GulianModel> list = query.getBeans(GulianModel.class);
        Map<String, Map<String, List<String>>> highlightresult = query.getHighlighting();
        SolrDocumentList results = query.getResults();
        for (int i = 0; i < results.size(); ++i) {
            SolrDocument document=results.get(i);
            if(highlightresult.get(document.get("id").toString())!=null && highlightresult.get(document.get("id").toString()).get("content")!=null){
                String t=highlightresult.get(document.get("id").toString()).get("content").get(0);
                list.get(i).setContent(t.replaceAll("<span>", ""));
                //list.get(i).setContent(t.replaceAll("</span>", ""));
            }
        }
        return list;
    }

    /**
     * 添加全文
     * @param gulianModel
     */
    @Override
    public void add(GulianModel gulianModel) {

        Map map = new HashMap();
        map = gulianModel.convertModel();
        SolrInputDocument document = new SolrInputDocument(map);

        //document.addField("id", System.currentTimeMillis() + "");
        //document.addField("title", gulianModel.getTitle());
        //document.addField("publishDate", gulianModel.getPublishDate());
        //document.addField("publishName", gulianModel.getPublishName());
        //document.addField("publishName", gulianModel.getPublishName());
        try {
            solrClient.add(document);
            solrClient.commit();
            //solrClient.close();
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SolrServiceImpl solrService = new SolrServiceImpl();
        GulianModel gulianModel = new GulianModel();
        gulianModel.setId("123");
        gulianModel.setContent("asd");

        solrService.add(gulianModel);
        // solrService.search("a", 1, 100);
    }
}
