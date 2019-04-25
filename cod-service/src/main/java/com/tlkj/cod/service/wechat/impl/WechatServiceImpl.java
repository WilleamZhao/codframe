/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.service.wechat.impl;

import com.tlkj.cod.common.CodCommonHttpClient;
import com.tlkj.cod.common.CodCommonJson;
import com.tlkj.cod.common.ComCommonJwt;
import com.tlkj.cod.log.annotation.Log;
import com.tlkj.cod.core.annotation.ParamNotNull;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Updater;
import com.tlkj.cod.model.wechat.bo.WechatSmallOpenIdModel;
import com.tlkj.cod.model.wechat.config.WechatConfig;
import com.tlkj.cod.model.wechat.dto.WechatLoginDto;
import com.tlkj.cod.model.wechat.entity.WxUserDo;
import com.tlkj.cod.service.system.SystemSetService;
import com.tlkj.cod.service.wechat.WechatService;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * Desc wechatService
 *
 * @author sourcod
 * @version 1.0
 * @className WechatServiceImpl
 * @date 2018/9/13 下午1:54
 */
@Service
public class WechatServiceImpl implements WechatService {

    @Autowired
    WechatConfig wechatConfig;

    @Autowired
    Finder finder;

    @Autowired
    Updater updater;

    @Autowired
    SystemSetService service;

    /**
     * 获取openId
     * @param code code
     * @return openId
     */
    @Log(name = "登录接口")
    @ParamNotNull(parameter = "code, appId, secret")
    @Override
    public WechatLoginDto getOpenid(String code, String appId, String secret) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code";
        String result;
        try {
            HttpResponse httpResponse = CodCommonHttpClient.httpsGet(url);
            result = EntityUtils.toString(httpResponse.getEntity());
        } catch (IOException | URISyntaxException e) {
            service.getLog().error("获取openId失败, {}", e.getMessage());
            return null;
        }
        service.getLog().info("微信返回数据={}", result);
        if (StringUtils.isEmpty(result)){
            return null;
        }
        WechatSmallOpenIdModel wechatSmallOpenIdModel = CodCommonJson.load(result, WechatSmallOpenIdModel.class);
        if (wechatSmallOpenIdModel == null){
            return null;
        }
        String openId = wechatSmallOpenIdModel.getOpenId();
        if (StringUtils.isEmpty(openId)){
            return null;
        }
        WechatLoginDto wechatLoginDto = new WechatLoginDto();
        wechatLoginDto.setOpenId(openId);
        String unionid = wechatSmallOpenIdModel.getUnionid();
        wechatLoginDto.setUnionid(unionid);

        WxUserDo userDo = finder.from("user").where("openid", openId).first(WxUserDo.class);
        Map<String, String> map = new HashMap<>();
        map.put("code", code);
        map.put("openId", openId);
        map.put("unionid", wechatSmallOpenIdModel.getUnionid());
        String token = ComCommonJwt.createJavaWebToken(map);
        Updater.Update update = null;
        int u;
        if (userDo == null){
            update = updater.insert("user").set("openid", openId).set("unionid", unionid);
            update.set("salt", token).setId();
            u = update.update();
        } else {
            update = updater.update("user").set("openid", openId).set("unionid", unionid);
            u = update.set("salt", token).where("id", userDo.getId()).update();
        }

        if (u == 1){
            wechatLoginDto.setToken(token);
        }

        return wechatLoginDto;
    }


}
