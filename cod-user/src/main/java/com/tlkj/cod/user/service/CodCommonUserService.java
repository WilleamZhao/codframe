package com.tlkj.cod.user.service;

import com.tlkj.cod.model.common.SystemResponse;
import com.tlkj.cod.user.model.entity.CodCommonUserDo;

/**
 * Desc 通用用户Service
 *
 * @author sourcod
 * @version 1.0
 * @className CodCommonUserService
 * @date 2019/3/21 7:57 PM
 */
public interface CodCommonUserService {

    /**
     * 注册
     * @param username 用户名
     * @param type     类型
     */
    SystemResponse register(String username, String type);

    /**
     * 更新密码
     * @param id          id
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    SystemResponse updatePassword(String id, String oldPassword, String newPassword, String type);


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
     * 获取银币信息
     */
    SystemResponse getCoin(String userId);

    /**
     * 保存用户信息
     */
    SystemResponse saveUser(CodCommonUserDo codCommonUserDo);

    /**
     * 充值
     */
    SystemResponse recharge(String receipt, String money, String userId);


}
