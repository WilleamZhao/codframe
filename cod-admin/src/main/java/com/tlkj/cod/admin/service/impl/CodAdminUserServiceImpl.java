/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.admin.service.impl;

import com.tlkj.cod.admin.service.CodAdminUserService;
import com.tlkj.cod.cache.CodCacheManager;
import com.tlkj.cod.common.CodCommonJson;
import com.tlkj.cod.core.service.LoginUserService;
import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Pagination;
import com.tlkj.cod.dao.jdbc.Updater;
import com.tlkj.cod.model.enums.StatusCode;
import com.tlkj.cod.model.system.dto.CodFrameUserDto;
import com.tlkj.cod.model.system.entity.CodFrameUserDo;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc 用户管理ServiceImpl
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminUserServiceImpl
 * @date 2018/10/29 上午11:19
 */
@Service
public class CodAdminUserServiceImpl implements CodAdminUserService, LoginUserService {

    private static Logger logger = LoggerFactory.getLogger(CodAdminUserServiceImpl.class);

    @Autowired
    Finder finder;

    @Autowired
    Updater updater;

    @Autowired
    CodCacheManager codCacheManager;


    /**
     * 添加用户
     * @param userDto 用户信息Dto
     * @return
     */
    @Override
    public StatusCode addUser(CodFrameUserDto userDto) {
        logger.info("添加用户接口, 参数={}", CodCommonJson.dump(userDto));
        return null;
    }

    @Override
    public CodFrameUserDto getUser(String id) {
        CodFrameUserDo userDo = finder.from(CodFrameUserDo.TABLE_NAME).where("id", id).first(CodFrameUserDo.class);
        CodFrameUserDto dto = new CodFrameUserDto();
        doToDto(userDo, dto);
        return dto;
    }

    /**
     * 缓存中获取用户信息
     * @param token token
     * @return 用户信息
     */
    @Override
    public CodFrameUserDto getUserByCache(String token) {
        CodFrameUserDo codFrameUserDo = (CodFrameUserDo) codCacheManager.get(token);
        if (codFrameUserDo == null){
            return null;
        }
        CodFrameUserDto dto = new CodFrameUserDto();
        doToDto(codFrameUserDo, dto);
        return dto;
    }

    @Override
    public StatusCode getUserByLoginAccount(String loginAccount) {
        return null;
    }

    /**
     * 获取全部用户信息接口
     * @param page 当前页
     * @param pageSize 页数大小
     * @return
     */
    @Override
    public Page<List<CodFrameUserDto>> listUser(String page, String pageSize, String userName, String loginAccount, String userPhone, String userSex, String state) {
        logger.info("获取全部用户接口, 参数:page={}, pageSize={}", page, pageSize);

        List<CodFrameUserDo> userDoList;
        Finder.Query query = finder.from("cod_sys_user");

        if (StringUtils.isNotBlank(userName)){
            query.where("user_name", userName);
        }

        if (StringUtils.isNotBlank(loginAccount)){
            query.where("login_account", loginAccount);
        }

        if (StringUtils.isNotBlank(userPhone)){
            query.where("user_phone", userPhone);
        }

        if (StringUtils.isNotBlank(userSex)){
            query.where("user_sex", userSex);
        }

        if (StringUtils.isNotBlank(state)){
            query.where("state", state);
        } else {
            query.where("state", "1");
        }

        Pagination<CodFrameUserDo> pagination;
        if (StringUtils.isNotBlank(pageSize)){
            pagination = query.paginate(CodFrameUserDo.class, Integer.parseInt(page), Integer.parseInt(pageSize));
        } else {
            pagination = query.paginate(CodFrameUserDo.class, Integer.parseInt(page));
        }

        userDoList = pagination.getData();
        List<CodFrameUserDto> userDtoList = new ArrayList<>();
        if (userDoList != null && userDoList.size() != 0){
            userDoList.forEach(userDo -> {
                CodFrameUserDto userDto = new CodFrameUserDto();
                doToDto(userDo, userDto);
                userDtoList.add(userDto);
            });
        }
        Page<List<CodFrameUserDto>> tempPage = new Page<>(userDtoList, pagination);
        return tempPage;
    }

    /**
     * 保存用户信息
     * @param userName     用户名
     * @param loginAccount 登录账号
     * @param userHead     用户头像
     * @param userPhone    用户手机号
     * @param userEmail    用户email
     * @param userSex      用户性别
     * @param state        状态
     * @return
     */
    @Override
    public StatusCode saveUser(String id, String userName, String loginAccount, String userHead, String userPhone, String userEmail, String userSex, String state) {
        Updater.Update update = StringUtils.isNotBlank(id) ? updater.update(CodFrameUserDo.TABLE_NAME).where("id", id) : updater.insert(CodFrameUserDo.TABLE_NAME).setId();
        if (StringUtils.isNotBlank(userName)){
            update.set("user_name", userName);
        }

        if (StringUtils.isNotBlank(loginAccount)){
            update.set("login_account", loginAccount);
        }

        if (StringUtils.isNotBlank(userHead)){
            update.set("user_head", userHead);
        }

        if (StringUtils.isNotBlank(userPhone)){
            update.set("user_phone", userPhone);
        }

        if (StringUtils.isNotBlank(userEmail)){
            update.set("user_email", userEmail);
        }

        if (StringUtils.isNotBlank(userSex)){
            update.set("user_sex", userSex);
        }

        if (update.update() == 1){
            return StatusCode.SUCCESS_CODE;
        }

        return StatusCode.FAIL_CODE;
    }

    @Override
    public Boolean verifyLoginAccount(String loginAccount) {
        int i = finder.from(CodFrameUserDo.TABLE_NAME).where("login_account", loginAccount).select("count(*)").firstForObject(Integer.class);
        if (i == 0){
            return true;
        }
        return false;
    }

    /**
     * 修改密码
     * @param id       主键
     * @param password 新密码
     * @return 是否修改成功
     */
    @Override
    public StatusCode updatePassword(String id, String password) {
        int i = updater.update(CodFrameUserDo.TABLE_NAME).set("login_pass", password).where("id", id).update();
        return i == 1 ? StatusCode.SUCCESS_CODE : StatusCode.FAIL_CODE;
    }

    @Override
    public CodFrameUserDo getUserByUsername(String username) {
        CodFrameUserDo codFrameUserDo = finder.from(CodFrameUserDo.TABLE_NAME).where("login_account", username).first(CodFrameUserDo.class);
        return codFrameUserDo;
    }

    /**
     * 设置 Dto
     * @param userDo  do
     * @param userDto dto
     */
    private void doToDto(CodFrameUserDo userDo, CodFrameUserDto userDto){
        userDto.setId(userDo.getId());
        userDto.setCompanyId(userDo.getCompany_id());
        userDto.setDeptId(userDo.getDept_id());
        userDto.setEmployeeId(userDo.getEmployee_id());
        userDto.setExpiration(userDo.getExpiration());
        userDto.setIntro(userDo.getIntro());
        userDto.setLoginAccount(userDo.getLogin_account());
        userDto.setRegisterTime(userDo.getRegister_time());
        userDto.setState(userDo.getState());
        userDto.setUpdateTime(userDo.getUpdate_time());
        userDto.setUserBirthday(userDo.getUser_birthday());
        userDto.setUserEmail(userDo.getUser_email());
        userDto.setUserHead(userDo.getUser_head());
        userDto.setUserName(userDo.getUser_name());
        userDto.setUserPhone(userDo.getUser_phone());
        userDto.setUserSex(userDo.getUser_sex());
    }
}
