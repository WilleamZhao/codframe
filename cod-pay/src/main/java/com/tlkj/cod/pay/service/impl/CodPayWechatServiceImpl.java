/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.pay.service.impl;

import com.tlkj.cod.cache.CodCacheManager;
import com.tlkj.cod.common.CodCommonHttpClient;
import com.tlkj.cod.common.CodCommonJson;
import com.tlkj.cod.common.CodCommonXml;
import com.tlkj.cod.log.annotation.CodLog;
import com.tlkj.cod.log.service.CodLogService;
import com.tlkj.cod.pay.model.config.CodPayWechatUrlConfig;
import com.tlkj.cod.pay.model.dito.CodPayWechatUnifiedOrderDito;
import com.tlkj.cod.pay.model.dito.CodPayWechatUnifiedOrderNotifyDito;
import com.tlkj.cod.pay.model.doto.CodPayWechatUnifiedOrderDoto;
import com.tlkj.cod.pay.model.dto.CodPayWechatUnifiedOrderDto;
import com.tlkj.cod.pay.service.CodPayWechatService;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Desc 微信支付 service impl
 *
 * @author sourcod
 * @version 1.0
 * @className CodPayWechatServiceImpl
 * @date 2019/8/21 10:48 AM
 */
@Service("wechat")
public class CodPayWechatServiceImpl implements CodPayWechatService {

    @Autowired
    CodLogService codLogService;

    @Autowired
    CodCacheManager codCacheManager;

    /**
     * 统一下单接口
     */
    @Override
    public CodPayWechatUnifiedOrderDto unifiedOrder(CodPayWechatUnifiedOrderDoto wechatUnifiedOrderDoto) {

        String result = "";

        try {
            StringEntity stringEntity = new StringEntity(CodCommonXml.toXml(wechatUnifiedOrderDoto), "UTF-8");
            Header header = new BasicHeader("Content-Type", "text/xml;charset=UTF-8");
            HttpResponse response = CodCommonHttpClient.httpsPost(CodPayWechatUrlConfig.UNIFIED_ORDER_URL, stringEntity, header);
            String order = wechatUnifiedOrderDoto.getOut_trade_no();
            codCacheManager.set(order, wechatUnifiedOrderDoto.getSign());
            result = EntityUtils.toString(response.getEntity(), "UTF-8");
            System.out.println("微信返回" + result);
            CodPayWechatUnifiedOrderDito codPayWechatUnifiedOrderDito = CodCommonXml.toBean(result, CodPayWechatUnifiedOrderDito.class);
            return codPayWechatUnifiedOrderDito.toCall();
        } catch (URISyntaxException | IOException | JAXBException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        codLogService.info(result);
        return null;
    }

    /**
     * 接收通知接口
     * @param result 通知字符串
     * @return
     */
    @CodLog(name = "微信回调")
    @Override
    public CodPayWechatUnifiedOrderNotifyDito notify(String result) {
        CodPayWechatUnifiedOrderNotifyDito notifyDito = null;
        try {
            notifyDito = CodCommonXml.toBean(result, CodPayWechatUnifiedOrderNotifyDito.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        if (notifyDito != null){
            System.out.println(CodCommonJson.dump(notifyDito));
            // 成功
            if ("SUCCESS".equals(notifyDito.getReturn_code())) {
                return notifyDito;
            }
        }
        return null;
    }
}
