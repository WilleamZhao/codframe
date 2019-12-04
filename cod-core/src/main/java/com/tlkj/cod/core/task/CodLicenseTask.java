/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.core.task;

import com.tlkj.cod.common.CodCommonDate;
import com.tlkj.cod.common.CodCommonHttpClient;
import com.tlkj.cod.common.CodCommonJson;
import com.tlkj.cod.data.service.CodDataService;
import com.tlkj.cod.model.common.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Map;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className CodLicenseTask
 * @date 2019/12/3 7:33 PM
 */
@Component
public class CodLicenseTask {

    @Autowired
    CodDataService codDataService;

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
            } else {
                codDataService.setData(projectName + "-license", "0");
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
