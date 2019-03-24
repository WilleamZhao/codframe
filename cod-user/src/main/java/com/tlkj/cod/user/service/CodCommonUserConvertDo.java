/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.user.service;

import com.tlkj.cod.user.model.dto.CodCommonUserDto;
import com.tlkj.cod.user.model.entity.CodCommonUserDo;
import org.springframework.stereotype.Component;

/**
 * Desc 通用转换
 *
 * @author sourcod
 * @version 1.0
 * @className CodCommonUserConvertDo
 * @date 2019/3/24 3:33 PM
 */
@Component
public abstract class CodCommonUserConvertDo {

    public CodCommonUserDo codCommonUserDo(CodCommonUserDto dto){

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
