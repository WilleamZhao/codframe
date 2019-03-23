/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.user.facade;

import com.tlkj.cod.model.common.SystemResponse;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className CodCommonUserFacade
 * @date 2019/3/21 11:36 PM
 */
public interface CodCommonUserFacade {


    /**
     * 保存用户
     * @return
     */
    SystemResponse saveUser(String userId, String name, String headUrl, String password, String email, String phone);

    /**
     * 充值接口
     * @param userId  用户Id
     * @param coin    充值金额
     * @param type    充值类型
     *                1: 微信
     *                2: 支付宝
     *                3: 苹果支付
     *                4: 通联支付
     *                5: 银联
     *                6: 财付通
     * @param receipt 收据/验证
     * @return
     */
    SystemResponse recharge(String userId, String coin, String type, String receipt);



}
