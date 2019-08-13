/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.user.service;

import com.tlkj.cod.cache.CodCacheManager;
import com.tlkj.cod.model.common.SystemResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Desc 验证
 *
 * @author sourcod
 * @version 1.0
 * @className CodCommonUserVerifyService
 * @date 2019/3/24 3:10 PM
 */
@Component
public abstract class CodCommonUserVerifyService {

    @Autowired
    CodCacheManager cacheManager;

    /**
     * 验证电话
     * @param field 手机号
     * @param code  验证吗
     * @return
     */
    public SystemResponse verify(String field, String code){
        SystemResponse response = new SystemResponse();
        String cacheCode = cacheManager.get(field, String.class);

        if (StringUtils.isEmpty(cacheCode) || cacheCode.equals(code)){
            return response.fail(false);
        }

        return response.success(true);
    }

    SystemResponse verifyOther(){
        return new SystemResponse();
    }


}
