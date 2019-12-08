/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.apple.service;

import com.tlkj.cod.model.common.SystemResponse;

/**
 * Desc 支付Service
 *
 * @author sourcod
 * @version 1.0
 * @className ApplePayService
 * @date 2019/2/27 5:04 PM
 */
public interface ApplePayService {

    /**
     * 验证支付
     * @param receipt 收据
     */
    SystemResponse verifyPay(String receipt);
}
