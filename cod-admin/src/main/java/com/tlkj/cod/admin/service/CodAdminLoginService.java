package com.tlkj.cod.admin.service;

import com.tlkj.cod.admin.model.dto.CodAdminLoginDto;
import com.tlkj.cod.admin.model.entity.CodAdminUserDo;

import java.util.Map;

/**
 * Desc 登录service
 *
 * @author sourcod
 * @version 1.0
 * @className LoginService
 * @date 2018/7/1 上午11:19
 */

public interface CodAdminLoginService {

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @param code 验证码
     * @param isRemeber 是否记住我
     * @return 登录是否成功
     */
    CodAdminLoginDto login(String username, String password, String code, String isRemeber);

    /**
     * 创建验证码
     * @return 验证码, 验证码路径
     */
    Map createCode(String token);

    CodAdminUserDo findCodAdminUserDo(String username);
}
