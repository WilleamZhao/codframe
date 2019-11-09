/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.wechat.mp.service.impl;

import com.tlkj.cod.wechat.common.error.WxErrorException;
import com.tlkj.cod.wechat.mp.service.CodWechatMpMenuService;
import org.springframework.stereotype.Service;

/**
 * Desc 公众号 菜单管理 service
 *
 * @author sourcod
 * @version 1.0
 * @className CodWechatMpMenuServiceImpl
 * @date 2019/11/8 10:58 PM
 */
@Service
public class CodWechatMpMenuServiceImpl implements CodWechatMpMenuService {

    @Override
    public String menuCreate(String json) throws WxErrorException {
        return null;
    }

    @Override
    public void menuDelete() throws WxErrorException {

    }

    @Override
    public void menuDelete(String menuId) throws WxErrorException {

    }
}
