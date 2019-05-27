/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.apple.service.impl;

import com.tlkj.cod.apple.model.to.VerifyReceiptTo;
import com.tlkj.cod.apple.service.ApplePayService;
import com.tlkj.cod.common.CodCommonHttpClient;
import com.tlkj.cod.common.CodCommonJson;
import com.tlkj.cod.log.annotation.Log;
import com.tlkj.cod.dao.jdbc.Updater;
import com.tlkj.cod.log.service.LogService;
import com.tlkj.cod.model.common.SystemResponse;
import com.tlkj.cod.model.enums.StatusCode;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Desc 支付ServiceImpl
 *
 * @author sourcod
 * @version 1.0
 * @className ApplePayServiceImpl
 * @date 2019/2/27 5:04 PM
 */
@Service
public class ApplePayServiceImpl implements ApplePayService {

    // 沙盒url
    private final static String sandboxUrl = "https://sandbox.itunes.apple.com/verifyReceipt";

    // 正式url
    private final static String formalUrl = "https://buy.itunes.apple.com/verifyReceipt";

    @Autowired
    LogService logService;

    @Autowired
    Updater updater;

    @Log(name = "验证支付")
    @Override
    public SystemResponse verifyPay(String receipt) {
        SystemResponse systemResponse;
        systemResponse = verify(formalUrl, receipt);
        if (systemResponse.getStatusCode() == StatusCode.SUCCESS_CODE){
            return systemResponse;
        }
        systemResponse.setStatusCode(StatusCode.FAIL_CODE);
        return systemResponse;
    }

    /**
     * 验证
     */
    private SystemResponse verify(String url, String receipt){
        SystemResponse systemResponse = new SystemResponse();

        JSONObject data = new JSONObject();
        data.put("receipt-data", receipt);
        System.out.println(data.toString());
        StringEntity entity = new StringEntity(data.toString(), "utf-8");
        entity.setContentType("application/json");
        try {
            HttpResponse httpResponse = CodCommonHttpClient.httpsPost(url, entity);
            String responseStr = EntityUtils.toString(httpResponse.getEntity());
            System.out.println(responseStr);
            VerifyReceiptTo verifyReceiptTo = CodCommonJson.load(responseStr, VerifyReceiptTo.class);
            System.out.println(CodCommonJson.dump(verifyReceiptTo));

            if (verifyReceiptTo.getStatus() == 21007){
                return verify(sandboxUrl, receipt);
            }

            if (verifyReceiptTo.getStatus() == 0){
                systemResponse.setData(verifyReceiptTo);
                systemResponse.setStatusCode(StatusCode.SUCCESS_CODE);
                return systemResponse;
            }
        } catch (IOException | URISyntaxException e) {
            logService.error("验证支付异常", e);
            systemResponse.setStatusCode(StatusCode.FAIL_CODE);
            return systemResponse;
        }
        systemResponse.setStatusCode(StatusCode.FAIL_CODE);
        return systemResponse;
    }
}
