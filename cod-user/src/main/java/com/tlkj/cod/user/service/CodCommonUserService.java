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
