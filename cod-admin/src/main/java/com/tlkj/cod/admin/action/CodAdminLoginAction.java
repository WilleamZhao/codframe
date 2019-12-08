package com.tlkj.cod.admin.action;

import com.tlkj.cod.admin.model.dto.CodAdminLoginDto;
import com.tlkj.cod.admin.service.CodAdminLoginService;
import com.tlkj.cod.cache.database.service.CodCacheDBService;
import com.tlkj.cod.common.CodCommonNetWork;
import com.tlkj.cod.log.service.CodLogService;
import com.tlkj.cod.model.common.GeneralResponse;
import com.tlkj.cod.model.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Desc 登录Action
 *
 * @author sourcod
 * @version 1.0
 * @className LoginAction
 * @date 2018/6/30 下午6:40
 */
@RestController
@RequestMapping("system/login")
public class CodAdminLoginAction extends GeneralResponse {

    @Autowired
    CodAdminLoginService loginService;

    @Autowired
    CodLogService codLogService;

    @Autowired
    CodCacheDBService codCacheDBService;

    /**
     * 登录接口
     *
     * @return 登录结果
     */
    @RequestMapping("/login")
    public Response login(HttpServletRequest request){
        // 用户名
        String username = request.getParameter("username");
        // 密码
        String password = request.getParameter("password");
        // 记住我 (存数据库)
        String isRemember = request.getParameter("isRemember");
        // 验证码
        String code = request.getParameter("code");
        String host = "";
        try {
            host = CodCommonNetWork.getIpAddress(request);
        } catch (IOException e) {
            System.out.println("获取IP失败");
            e.printStackTrace();
        }
        CodAdminLoginDto codAdminLoginDto = loginService.login(username, password, code, isRemember);
        if (codAdminLoginDto != null && "1".equals(codAdminLoginDto.getCode())){
            // TODO 记住我
            codCacheDBService.set(host, "");
        }
        return super.success(codAdminLoginDto);
    }

    /**
     * 创建验证码 并放到session
     * @return 验证码路径
     */
    @RequestMapping("createCode")
    public String createCode(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        //生成随机字串
        Map verifyCode = loginService.createCode("");
        //存入会话session
        HttpSession session = request.getSession(true);
        //删除以前的
        session.removeAttribute("verCode");
        session.setAttribute("verCode", verifyCode.get("code"));
        return verifyCode.get("codeImgUrl").toString();
    }
}
