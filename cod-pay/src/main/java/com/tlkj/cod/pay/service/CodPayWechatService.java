/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.pay.service;

import com.tlkj.cod.pay.model.dito.CodPayWechatUnifiedOrderNotifyDito;
import com.tlkj.cod.pay.model.doto.CodPayWechatUnifiedOrderDoto;
import com.tlkj.cod.pay.model.dto.CodPayWechatUnifiedOrderDto;

/**
 * Desc 微信
 *
 * @author sourcod
 * @version 1.0
 * @className CodPayWechatService
 * @date 2019/8/21 9:23 AM
 */
public interface CodPayWechatService {

    /**
     * 统一下单接口
     * @param wechatUnifiedOrderDoto 传输 pojo
     * @return 预支付信息
     */
    CodPayWechatUnifiedOrderDto unifiedOrder(CodPayWechatUnifiedOrderDoto wechatUnifiedOrderDoto);

    CodPayWechatUnifiedOrderNotifyDito notify(String result);
}
