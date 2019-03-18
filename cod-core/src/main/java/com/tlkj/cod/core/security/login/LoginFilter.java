/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.core.security.login;

import com.tlkj.cod.cache.CodCacheManager;
import com.tlkj.cod.core.security.util.PermissionUtil;
import com.tlkj.cod.core.spring.SpringContextUtil;
import com.tlkj.cod.model.enums.StatusCode;
import com.tlkj.cod.model.system.dto.CodFramePermissionItemDto;
import com.tlkj.cod.model.system.dto.CodFramePermissionTreeDto;
import com.tlkj.cod.model.system.entity.CodFrameUserDo;
import com.tlkj.cod.model.common.GeneralResponse;
import com.tlkj.cod.common.CodCommonJson;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Desc 提供访问控制的基础功能；比如是否允许访问/当访问拒绝时如何处理等：
 *
 * @author sourcod
 * @version 1.0
 * @className LoginFilter
 * @date 2018/10/22 下午2:38
 */
public class LoginFilter extends AccessControlFilter {

    /**
     * tokenName
     */
    private static String TOKEN_NAME = "access_token";

    /**
     * 表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，如果允许访问返回true，否则false；
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue) {

        return false;
    }

    /**
     * 表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；如果返回false表示该拦截器实例已经处理了，将直接返回即可。
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse response) throws Exception {
        CodCacheManager codCacheManager = (CodCacheManager) SpringContextUtil.getBean("CodCacheManagerImpl");
        /*HttpServletRequest request = (HttpServletRequest) servletRequest;
        // 1. 获取浏览器信息
        String ua = request.getHeader("User-Agent");
        // 转成UserAgent对象
        UserAgent userAgent = UserAgent.parseUserAgentString(ua);
        // 2. 生成浏览器唯一id
        int id = userAgent.getId();
        // 3. 获取真实ip
        String ip = NetWorkUtil.getIpAddress(request);
        // 4. 生成token
        // String token = UUIDUtil.getUUID();
        String password = request.getParameter("password");

        String token = getToken(request);

        //2、客户端传入的用户身份
        // Constants.PARAM_USERNAME
        String username = request.getParameter("username");
        //3、客户端请求的参数列表
        Map<String, String[]> params = new HashMap<>(request.getParameterMap());

        // Constants.PARAM_DIGEST
        params.remove("digest");

        JedisUtil.Strings jedis = JedisUtil.getInstance().STRINGS;

        //4、生成无状态Token
        StatelessToken statelessToken = new StatelessToken(username, params, password);
        System.out.println(token);
        try {
            //5、委托给Realm进行登录
            Object o = getSubject(request, response);
            Subject subject = SecurityUtils.getSubject();
            boolean a = subject.isPermitted("admin");
            // ((Subject) o).checkPermission();
            System.out.println("asd");
        } catch (Exception e) {
            e.printStackTrace();
            //6、登录失败
            onLoginFail(response);
            return false;
        }*/
        HttpServletRequest request = WebUtils.toHttp(servletRequest);
        String token = getToken(request);
        if (StringUtils.isBlank(token)){
            return false;
        }
        Subject subject = (Subject) codCacheManager.get(token);
        if (subject != null){
            CodFrameUserDo userDo = (CodFrameUserDo) subject.getPrincipals().getPrimaryPrincipal();
            // id=1 是超级管理员
            if ("1".equals(userDo.getId())){
                return true;
            }
            List<CodFramePermissionTreeDto> dtoList = (List<CodFramePermissionTreeDto>) codCacheManager.get(token + "permission");
            // 权限不足
            if (dtoList == null){
                onLoginFail(response, StatusCode.NO_PERMISSION_CODE);
                return false;
            }
            String path = request.getPathInfo();
            for (CodFramePermissionTreeDto treeDto : dtoList){
                String code = treeDto.getCode();
                List<CodFramePermissionItemDto> dtos = treeDto.getPermission();
                for (CodFramePermissionItemDto dto : dtos){
                    if (verifyPermission(path, code, dto.getCode())){
                        return true;
                    }
                }
            }
        } else {
            onLoginFail(response, StatusCode.LOGIN_FAIL_CODE);
            return false;
        }
        onLoginFail(response, StatusCode.NO_PERMISSION_CODE);
        return false;
    }

    //登录失败时默认返回401状态码
    private void onLoginFail(ServletResponse response, StatusCode statusCode) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        response.setContentType("text/html;charset=utf-8");
        httpResponse.getWriter().write(CodCommonJson.dump(new GeneralResponse().output(statusCode)));
    }

    private String getToken(HttpServletRequest request){
        String token = request.getParameter("access_token");
            if (StringUtils.isBlank(token)){
            token = request.getHeader("access_token");
        }
        return token;
    }

    /**
     * 验证权限
     * @param path 路径
     * @param code 代码
     * @param name 权限
     * @return
     */
    private boolean verifyPermission(String path, String code, String name){
        String[] codes = code.split("/");
        String[] strings = path.split("/");
        int j = 0;
        for (int i = codes.length; i > 0; i--){
            // 去掉第一个和最后一个
            for (int h = 1; h < strings.length - 1; h++){
                String codeTemp = codes[i - 1];
                String pathTemp = strings[h];
                if (codeTemp.equals(pathTemp)){
                    j++;
                    break;
                }
            }
        }
        // 权限1
        String code1 = getStringByRegExp(strings[strings.length - 1]);

        String permissionUrl = PermissionUtil.getName(code1).toLowerCase();

        if (j == codes.length && StringUtils.isNotBlank(permissionUrl) && strings[strings.length - 1].startsWith(permissionUrl)){
            return true;
        }
        return false;
    }

    public static String getStringByRegExp(String string){
        String result = "";
        String regExp = "(.*?)[A-Z]+";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(string);

        if (matcher.find()){
            result = matcher.group(1);
            return result;
        }
        return string;
    }
}
