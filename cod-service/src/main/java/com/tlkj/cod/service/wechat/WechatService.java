/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.service.wechat;

import com.tlkj.cod.model.wechat.dto.WechatLoginDto;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className WechatService
 * @date 2018/9/13 下午1:53
 */
public interface WechatService {

    /**
     * 获取openId
     * @param code code
     * @return openId
     */
    WechatLoginDto getOpenid(String code, String appId, String secret);


}
