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

import com.tlkj.cod.cache.CodCacheManager;
import com.tlkj.cod.model.common.SystemResponse;
import com.tlkj.cod.user.facade.CodCommonUserFacade;
import com.tlkj.cod.user.model.dto.CodCommonUserDto;
import com.tlkj.cod.user.model.entity.CodCommonUserDo;
import com.tlkj.cod.user.model.enums.LoginType;
import com.tlkj.cod.user.service.CodCommonUserConvertDo;
import com.tlkj.cod.user.service.CodCommonUserService;
import com.tlkj.cod.user.service.CodCommonUserVerifyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.tlkj.cod.user.model.enums.LoginType.EMAIL;
import static com.tlkj.cod.user.model.enums.LoginType.PHONE;

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

    @Autowired
    CodCacheManager cacheManager;


    @Override
    public SystemResponse register(LoginType type, String username, String code, CodCommonUserDto codCommonUserDto) {
        switch (type){
            case PHONE:
                boolean b = verify(username, code);
                if (b){
                    SystemResponse response = codCommonUserService.saveUser(convertCommonUserDo(codCommonUserDto));
                }
                break;
            case EMAIL:

                break;
            case WEHCAT:

                break;
            case WEIBO:

                break;
            case FACEBOOK:

                break;
            case TWITTER:

                break;
            case GITHUB:

                break;
            case ALIPAY:

                break;
            case TAOBAO:

                break;
            case OTHER1:

                break;
            case OTHER2:

                break;
            case OTHER3:

                break;
            default:
                break;
        }
        return null;
    }

    @Override
    public SystemResponse login(LoginType type, String username, String password, String code) {

        return null;
    }

    @Override
    public SystemResponse recharge(String userId, String coin, String type, String receipt) {
        return null;
    }

    @Override
    public SystemResponse bind(String id, String type, String identifier, String phone, String code) {
        return null;
    }

    @Override
    public SystemResponse logout(String username) {
        return null;
    }

    @Override
    public SystemResponse getUser(String userId) {
        return null;
    }

    @Override
    public SystemResponse updatePassword(String id, String oldPassword, String newPassword, String type) {
        return null;
    }

    /**
     * 验证验证码是否正确
     * @param username 用户名
     * @param code     验证码
     */
    protected boolean verify(String username, String code){
        String cacheCode = cacheManager.get(username, String.class);

        if (StringUtils.isEmpty(cacheCode) || cacheCode.equals(code)){
            return false;
        }

        return true;
    }

    /**
     * Dto转Do
     * @param dto
     * @return
     */
    protected CodCommonUserDo convertCommonUserDo(CodCommonUserDto dto){
        CodCommonUserDo commonUserDo = new CodCommonUserDo();
        commonUserDo.setId(dto.getId());
        commonUserDo.setUsername(dto.getId());
        commonUserDo.setNickname(dto.getId());
        commonUserDo.setLoginname(dto.getId());
        commonUserDo.setAge(dto.getId());
        commonUserDo.setReal_name(dto.getId());
        commonUserDo.setSex(dto.getId());
        commonUserDo.setHead(dto.getId());
        commonUserDo.setPhone(dto.getId());
        commonUserDo.setEmail(dto.getId());
        commonUserDo.setId_card(dto.getId());

        commonUserDo.setPwd(dto.getId());

        commonUserDo.setState(dto.getId());

        commonUserDo.setQq_id(dto.getId());
        commonUserDo.setOpenid(dto.getId());
        commonUserDo.setUnionid(dto.getId());
        commonUserDo.setTwitter_id(dto.getId());
        commonUserDo.setFacebook_id(dto.getId());
        commonUserDo.setGithub_id(dto.getId());
        commonUserDo.setAlipay_id(dto.getId());
        commonUserDo.setWeibo_id(dto.getId());
        commonUserDo.setVerify_code(dto.getId());
        commonUserDo.setOther1_id(dto.getId());
        commonUserDo.setOther2_id(dto.getId());
        commonUserDo.setOther3_id(dto.getId());

        commonUserDo.setLogin_time(dto.getId());

        return commonUserDo;
    }
}
