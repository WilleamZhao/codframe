/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.service.system;

import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.model.system.dto.CodFrameUserDto;
import com.tlkj.cod.model.enums.StatusCode;

import java.util.List;

/**
 * Desc 用户管理Service
 *
 * @author sourcod
 * @version 1.0
 * @className UserService
 * @date 2018/10/29 上午11:19
 */
public interface UserService {

    /**
     * 添加用户
     * @param userDto 用户信息Dto
     * @return 状态码
     */
    StatusCode addUser(CodFrameUserDto userDto);

    /**
     * 获取用户信息
     * @param id 主键
     * @return 状态码
     */
    CodFrameUserDto getUser(String id);

    /**
     * 从缓存中获取token
     * @param token token
     * @return 在缓存中获取token
     */
    CodFrameUserDto getUserByCache(String token);

    /**
     * 根据登录账号获取用户信息
     * @param loginAccount 登录账号
     */
    StatusCode getUserByLoginAccount(String loginAccount);

    /**
     * 获取用户列表
     * @param page 当前页
     * @param pageSize 页数大小
     * @return 状态码
     */
    Page<List<CodFrameUserDto>> listUser(String page, String pageSize, String userName, String loginAccount, String userPhone, String userSex, String state);

    /**
     * 保存用户
     * @param userName     用户名
     * @param loginAccount 登录账号
     * @param userHead     用户头像
     * @param userPhone    用户手机号
     * @param userEmail    用户email
     * @param userSex      用户性别
     * @param state        状态
     */
    StatusCode saveUser(String id, String userName, String loginAccount, String userHead, String userPhone, String userEmail, String userSex, String state);

    /**
     * 验证登录名是否重复
     * @param loginAccount 登录名
     */
    Boolean verifyLoginAccount(String loginAccount);

    /**
     * 修改密碼
     * @param id       id
     * @param password 新密码
     * @return 是否成功
     */
    StatusCode updatePassword(String id, String password);

}
