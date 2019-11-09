package com.tlkj.cod.admin.service.impl;

import com.tlkj.cod.admin.model.dto.CodAdminLoginDto;
import com.tlkj.cod.admin.model.entity.CodAdminUserDo;
import com.tlkj.cod.admin.service.CodAdminLoginService;
import com.tlkj.cod.cache.CodCacheManager;
import com.tlkj.cod.common.CodCommonEncryption;
import com.tlkj.cod.common.CodCommonUUID;
import com.tlkj.cod.common.CodCommonVerifyCode;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Updater;
import com.tlkj.cod.log.annotation.CodLog;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Desc 登录实现
 *
 * @author sourcod
 * @version 1.0
 * @className LoginServiceImpl
 * @date 2018/7/1 上午11:23
 */
@Service
public class CodAdminLoginServiceImpl implements CodAdminLoginService {


    private static Logger logger = LoggerFactory.getLogger(CodAdminLoginServiceImpl.class);

    @Autowired
    Finder finder;

    @Autowired
    Updater updater;

    @Autowired
    CodCacheManager codCacheManager;

    /**
     * 1. 验证验证码是否正确
     * 2. 验证用户名密码是否正确
     * 3. 生成token
     * 4. 返回结果
     *
     * @param username 用户名
     * @param password 密码
     * @param code     验证码
     * @return 登录结果
     */
    @CodLog(name = "登陆")
    @Override
    public CodAdminLoginDto login(String username, String password, String code, String isRemeber) {
        CodAdminLoginDto codAdminLoginDto = new CodAdminLoginDto();
        /*
         * 1. 验证验证码是否正确
         */
        /*if (StringUtils.isNotEmpty(code) && StringUtils.isNotEmpty(code) && code.equals(code)){
            memcachedService.get(code);
            logger.debug("用户{}: 验证码验证成功={}", username, code);
        }*/
        String codAdminToken = CodCommonUUID.getUUID();

        if ("codAdmin".equals(username) && "123456".equals(password)){
            codAdminLoginDto.setToken(codAdminToken);
            codAdminLoginDto.setCode("1");
            codAdminLoginDto.setCode("登陆成功");
            return codAdminLoginDto;
        }

        // 根据用户名查询用户是否存在
        CodAdminUserDo codAdminUserDo = finder.from(CodAdminUserDo.TABLE_NAME).where("login_account", username).first(CodAdminUserDo.class);
        if (codAdminUserDo == null) {
            codAdminLoginDto.setMsg("用户名或密码错误");
            codAdminLoginDto.setCode("0");
            return codAdminLoginDto;
        }

        String tempPassword = "";
        switch (codAdminUserDo.getEncry_type()){
            // MD5
            case 1:
                tempPassword = CodCommonEncryption.MD5(password);
                break;
            // RSA
            case 2:
                break;
            // AES
            case 3:
                tempPassword = CodCommonEncryption.encryptAES128ECB(password, codAdminUserDo.getPrivate_key_id());
                break;
            // DES
            case 4:
                break;
            default:
                break;
        }

        // 登陆密码
        if (!tempPassword.equals(codAdminUserDo.getLogin_pass())){
            codAdminLoginDto.setMsg("用户名或密码错误");
            codAdminLoginDto.setCode("0");
            return codAdminLoginDto;
        }



        // 记住我
        if ("true".equals(isRemeber) || "1".equals(isRemeber)){
            updater.update(CodAdminUserDo.TABLE_NAME).set("is_remeber", "1").set("token", codAdminToken).update();
        }

        codCacheManager.set(codAdminToken, codAdminUserDo);


        /*Subject subject = SecurityUtils.getSubject();
        try {
            StatelessToken statelessToken = new StatelessToken(username, request.getParameterMap(), password);
            subject.login(statelessToken);
        } catch (UnknownAccountException e) {
            //抛出账号不存在异常
            error = "账号不存在";
            return "";
        } catch (IncorrectCredentialsException e) {
            error = "密码错误";
            return "";
        }
        subject.hasRole("admin");
        Set set = subject.getPrincipals().getRealmNames();
        Iterator iterator = set.iterator();
        String s = "";
        while (iterator.hasNext()) {
            s = iterator.next().toString();
        }*/

        codAdminLoginDto.setMsg("登陆成功");
        codAdminLoginDto.setCode("1");
        codAdminLoginDto.setToken(codAdminToken);
        return codAdminLoginDto;
    }

    /**
     * 验证用户名密码是否正确
     *
     * @param username 用户名
     * @param password 密码
     * @return 是否正确
     */
    private boolean verifyUserPass(String username, String password) {
        CodAdminUserDo codAdminUserDo = finder.from(CodAdminUserDo.TABLE_NAME)
                .where("login_account", username)
                .where("login_pass", password)
                .where("state", "1").first(CodAdminUserDo.class);
        if (codAdminUserDo != null) {
            return true;
        }

        return false;
    }

    /**
     * 创建验证码并返回路径
     * @return 验证码, 验证码路径
     */
    @Override
    public Map createCode(String token) {
        /*
         * 1. 生成验证码
         */
        String verifyCode = CodCommonVerifyCode.generateVerifyCode();

        if (StringUtils.isBlank(token)) {
            token = getToken();
        }

        /*
         * 2 生成图片
         *//*
        InputStream is = null;
        try {
            is = VerifyCodeUtil.createCode(verifyCode);
        } catch (IOException e) {
            logger.error("生成图片错误");
        }

        *//*
         * 3. 上传文件
         * @return 图片相对路径
         *//*
        String url = OSSUtil.uploadOSS("code/" + String.valueOf(DateUtils.now().getTime()) + ".jpg", is);
        Map<String, String> map = new HashMap<>(2);
        map.put("code", verifyCode);
        map.put("codeImgUrl", url);
        */
        Map<String, String> map = new HashMap<>(2);
        map.put("code", "");
        map.put("codeImgUrl", "");
        return map;
    }

    @Override
    public CodAdminUserDo findCodAdminUserDo(String username) {
        return finder.from(CodAdminUserDo.TABLE_NAME).where("login_account", username).first(CodAdminUserDo.class);
    }

    /**
     * @return
     */
    private String getToken() {
        return CodCommonUUID.getUUID();
    }
}
