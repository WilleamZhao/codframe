/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.user.service.impl;

import com.tlkj.cod.cache.CodCacheManager;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Updater;
import com.tlkj.cod.model.common.SystemResponse;
import com.tlkj.cod.user.model.dto.CodCommonUserDto;
import com.tlkj.cod.user.model.entity.CodCommonUserDo;
import com.tlkj.cod.user.service.CodCommonUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Desc 通用用户ServiceImpl
 *
 * @author sourcod
 * @version 1.0
 * @className CodCommonUserServiceImpl
 * @date 2019/3/21 7:57 PM
 */
@Service
public class CodCommonUserServiceImpl implements CodCommonUserService {

    @Autowired
    Finder finder;

    @Autowired
    Updater updater;

    @Autowired
    CodCacheManager codCacheManager;

    /**
     * 获取用户信息
     * 1. 缓存中取
     * 2. 从数据库中取
     * 3. 返回用户信息
     * @param userId 用户Id
     * @return
     */
    @Override
    public SystemResponse<CodCommonUserDto> getUser(String userId) {
        SystemResponse<CodCommonUserDto> systemResponse = new SystemResponse<>();
        // 1. 从缓存中取
        CodCommonUserDto codCommonUserDto = codCacheManager.get(userId, CodCommonUserDto.class);
        // 2. 缓存没有从数据库取
        CodCommonUserDo codCommonUserDo = null;
        if (codCommonUserDto == null){
            codCommonUserDo = finder.from(CodCommonUserDo.TABLE_NAME).where("id", userId).first(CodCommonUserDo.class);
        }
        // 3. 数据库没有返回失败
        if (codCommonUserDo == null){
            return systemResponse.fail();
        }
        codCommonUserDto = codCommonUserDo.toDto(CodCommonUserDto.class);
        return systemResponse.success(codCommonUserDto);
    }

    /**
     * 获取用户虚拟币
     * @param userId 用户Id
     * @return
     */
    @Override
    public SystemResponse getCoin(String userId) {
        SystemResponse<String> systemResponse = new SystemResponse<>();
        CodCommonUserDto data = getUser(userId).getData();
        if (data != null){
            return systemResponse.success(data.getCoin());
        }
        return systemResponse.fail();
    }

    /**
     * 保存用户
     * @param userId
     * @param name
     * @param headUrl
     * @param password
     * @param email
     * @param phone
     * @return
     */
    @Override
    public SystemResponse saveUser(CodCommonUserDo codCommonUserDo) {
        Updater.Update update = StringUtils.isEmpty(codCommonUserDo.getId()) ? updater.insert(CodCommonUserDo.TABLE_NAME) : updater.update(CodCommonUserDo.TABLE_NAME).where("id", codCommonUserDo.getId());
        if (StringUtils.isEmpty(codCommonUserDo.getReal_name())){
            update.set("real_name", codCommonUserDo.getReal_name());
        }

        if (StringUtils.isEmpty(codCommonUserDo.getHead())){
            update.set("head", codCommonUserDo.getHead());
        }

        if (StringUtils.isEmpty(codCommonUserDo.getPwd())){
            update.set("pwd", codCommonUserDo.getPwd());
        }

        if (StringUtils.isEmpty(codCommonUserDo.getEmail())){
            update.set("email", codCommonUserDo.getEmail());
        }

        if (StringUtils.isEmpty(codCommonUserDo.getPhone())){
            update.set("phone", codCommonUserDo.getPhone());
        }

        if (StringUtils.isEmpty(codCommonUserDo.getQq_id())){
            update.set("qq_id", codCommonUserDo.getQq_id());
        }

        if (StringUtils.isEmpty(codCommonUserDo.getPhone())){
            update.set("weibo_id", codCommonUserDo.getPhone());
        }

        if (StringUtils.isEmpty(codCommonUserDo.getPhone())){
            update.set("openid", codCommonUserDo.getPhone());
        }

        if (StringUtils.isEmpty(codCommonUserDo.getPhone())){
            update.set("unionid", codCommonUserDo.getPhone());
        }

        if (StringUtils.isEmpty(codCommonUserDo.getPhone())){
            update.set("nickname", codCommonUserDo.getPhone());
        }

        if (StringUtils.isEmpty(codCommonUserDo.getPhone())){
            update.set("pwd", codCommonUserDo.getPhone());
        }

        if (StringUtils.isEmpty(codCommonUserDo.getPhone())){
            update.set("coin", codCommonUserDo.getPhone());
        }

        if (StringUtils.isEmpty(codCommonUserDo.getPhone())){
            update.set("username", codCommonUserDo.getPhone());
        }

        if (StringUtils.isEmpty(codCommonUserDo.getPhone())){
            update.set("id_card", codCommonUserDo.getPhone());
        }

        if (StringUtils.isEmpty(codCommonUserDo.getPhone())){
            update.set("age", codCommonUserDo.getPhone());
        }

        if (StringUtils.isEmpty(codCommonUserDo.getPhone())){
            update.set("sex", codCommonUserDo.getPhone());
        }

        if (StringUtils.isEmpty(codCommonUserDo.getPhone())){
            update.set("real_name", codCommonUserDo.getPhone());
        }

        if (StringUtils.isEmpty(codCommonUserDo.getPhone())){
            update.set("verify_code", codCommonUserDo.getPhone());
        }

        if (StringUtils.isEmpty(codCommonUserDo.getPhone())){
            update.set("update_time", "now()", Void.class);
        }
        return null;
    }

    /**
     * 充值接口
     * @param receipt 收据
     * @param money   金额
     * @param userId  用户Id
     * @return
     */
    @Override
    public SystemResponse recharge(String receipt, String money, String userId) {

        return null;
    }
}
