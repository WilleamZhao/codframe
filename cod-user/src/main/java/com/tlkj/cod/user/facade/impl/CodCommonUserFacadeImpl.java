/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.user.facade.impl;

import com.tlkj.cod.model.common.SystemResponse;
import com.tlkj.cod.user.facade.CodCommonUserFacade;
import com.tlkj.cod.user.model.entity.CodCommonUserDo;
import com.tlkj.cod.user.service.CodCommonUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Desc 通用用户Facade
 *
 * @author sourcod
 * @version 1.0
 * @className CodCommonUserFacadeImpl
 * @date 2019/3/21 11:36 PM
 */
@Service
public class CodCommonUserFacadeImpl implements CodCommonUserFacade {

    @Autowired
    CodCommonUserService codCommonUserService;

    /**
     * TODO 保存用户
     * @param userId
     * @param name
     * @param headUrl
     * @param password
     * @param email
     * @param phone
     * @return
     */
    @Override
    public SystemResponse saveUser(String userId, String name, String headUrl, String password, String email, String phone) {
        CodCommonUserDo codCommonUserDo = new CodCommonUserDo();
        SystemResponse systemResponse = codCommonUserService.saveUser(codCommonUserDo);
        return systemResponse;
    }

    @Override
    public SystemResponse recharge(String userId, String coin, String type, String receipt) {
        return null;
    }
}
