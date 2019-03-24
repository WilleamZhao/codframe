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
import com.tlkj.cod.user.model.dto.CodCommonUserDto;
import com.tlkj.cod.user.model.enums.LoginType;

/**
 * Desc 通用用户facade
 *
 * @author sourcod
 * @version 1.0
 * @className CodCommonUserFacade
 * @date 2019/3/21 11:36 PM
 */
public interface CodCommonUserFacade {


    /**
     * 注册
     * @param username 用户名
     * @param type     类型
     */
    SystemResponse register(LoginType type, String username, String code, CodCommonUserDto codCommonUserDto);

    /**
     * 登录
     * @param type     类型
     * @param username 用户名
     * @param password 密码
     * @param code     验证码
     * @return
     */
    SystemResponse login(LoginType type, String username, String password, String code);

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

    /**
     * 绑定第三方
     * @param id         主键
     * @param type       类型
     *                   1: 手机号
     *                   2: email
     *                   3: QQ
     *                   4: 微信:openid
     *                   4: 微信:unionid
     *                   5: 微博
     *                   6: facebook
     *                   7: twitter
     *                   8: github
     *                   9: alipay
     *                   10: taobao
     *                   11: other1Id
     *                   12: other2Id
     *                   13: other3Id
     * @param identifier 唯一标识(第三方返回)
     * @param code       验证码
     */
    SystemResponse bind(String id, String type, String identifier, String phone, String code);

    /**
     * 登出
     * @param username 用户名
     */
    SystemResponse logout(String username);

    /**
     * 获取用户信息
     */
    SystemResponse getUser(String userId);

    /**
     * 更新密码
     * @param id          id
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    SystemResponse updatePassword(String id, String oldPassword, String newPassword, String type);



}
