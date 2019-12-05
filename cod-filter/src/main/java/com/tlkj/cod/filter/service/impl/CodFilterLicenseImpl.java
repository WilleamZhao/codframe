/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.filter.service.impl;

import com.alibaba.druid.support.spring.stat.annotation.Stat;
import com.tlkj.cod.common.CodCommonDate;
import com.tlkj.cod.common.CodCommonHttpClient;
import com.tlkj.cod.common.CodCommonJson;
import com.tlkj.cod.data.service.CodDataService;
import com.tlkj.cod.filter.common.CodFilterCommon;
import com.tlkj.cod.filter.model.config.CodFilterLicenseConfig;
import com.tlkj.cod.filter.service.CodFilterService;
import com.tlkj.cod.model.common.Response;
import com.tlkj.cod.model.enums.StatusCode;
import com.tlkj.cod.spring.common.CodSpringContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Map;

/**
 * Desc license 过滤器
 *
 * @author sourcod
 * @version 1.0
 * @className CodFilterLicenseImpl
 * @date 2019/12/4 2:20 PM
 */
@Service("codFilterLicense")
public class CodFilterLicenseImpl implements CodFilterService {

    private CodFilterLicenseConfig codFilterLicenseConfig;
    private CodDataService codDataService;

    @Autowired
    public CodFilterLicenseImpl(CodFilterLicenseConfig codFilterLicenseConfig){
        this.codFilterLicenseConfig = codFilterLicenseConfig;
        this.codDataService = CodSpringContext.getBean("codData", CodDataService.class);
    }

    @Override
    public boolean state() {
        return false;
    }

    @Override
    public String name() {
        return "codFilterLicense";
    }

    @Override
    public String alias() {
        return "License 过滤器";
    }

    @Override
    public int sort() {
        return 0;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    /**
     * 监听 在线 license
     * 1. 获取项目名
     * 2. 获取授权
     * 3. 判断授权是否到期
     * 4. 更新授权缓存
     */
    @Scheduled(cron="0/10 * *  * * ? ")
    public void license(){
        String projectName = codDataService.getDataValue("cod.config.project.name");
        String url = "http://api.sourcod.com/codframe/admin/api/server/getLicense";
        try {
            HttpResponse httpResponse = CodCommonHttpClient.httpGet(url + "?projectName=" + projectName);
            if (httpResponse.getStatusLine().getStatusCode() == 200){
                String response = EntityUtils.toString(httpResponse.getEntity());
                if (StringUtils.isNotBlank(response)) {
                    Response response1 = CodCommonJson.load(response, Response.class);
                    Map map = (Map) response1.getData();
                    String type = String.valueOf(map.get("type"));
                    String value = String.valueOf(map.get("value"));
                    if ("1".equals(type)) {
                        if ("1".equals(value)) {
                            codDataService.setData(projectName + "-license", "1");
                        } else {
                            codDataService.setData(projectName + "-license", "0");
                        }
                    } else if ("2".equals(type)){
                        Date date = CodCommonDate.parseDate(value, "yyyy-MM-dd");
                        if (date.after(CodCommonDate.now())){
                            codDataService.setData(projectName + "-license", "1");
                        } else {
                            codDataService.setData(projectName + "-license", "0");
                        }
                    } else {
                        codDataService.setData(projectName + "-license", "0");
                    }
                }
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String state = codFilterLicenseConfig.getState();
        if ("1".equals(state)){
            String projectName = codDataService.getDataValue("cod.config.project.name");
            String license = codDataService.getDataValue(projectName + "-license");
            if (StringUtils.isNotBlank(license) && "1".equals(license) ){
                chain.doFilter(request, response);
            } else {
                Response res = new Response(StatusCode.LICENSE_ERROR_CODE);
                CodFilterCommon.response(response, res);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
