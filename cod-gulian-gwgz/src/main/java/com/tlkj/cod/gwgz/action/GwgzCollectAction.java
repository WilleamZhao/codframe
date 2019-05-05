package com.tlkj.cod.gwgz.action;

import com.tlkj.cod.core.annotation.ParamNotNull;
import com.tlkj.cod.gwgz.service.GwgzCollectService;
import com.tlkj.cod.model.common.GeneralResponse;
import com.tlkj.cod.model.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Desc 古文观止后台
 *
 * @author sourcod
 * @version 1.0
 * @className GwgzCollectAction
 * @date 2019/4/29 6:38 PM
 */
@RestController
@RequestMapping(value = "gwgz/action/collect")
public class GwgzCollectAction extends GeneralResponse {

    @Autowired
    GwgzCollectService gwgzCollectService;

    @ParamNotNull(parameter = "openid")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Response list(HttpServletRequest request) {
        String openid = request.getParameter("openid");
        String page = request.getParameter("page");
        String pageSize = request.getParameter("pageSize");
        return gwgzCollectService.listAdmin(openid, page, pageSize).toResponse();
    }
}
