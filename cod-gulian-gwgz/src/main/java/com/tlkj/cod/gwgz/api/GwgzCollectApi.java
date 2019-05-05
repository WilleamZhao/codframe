package com.tlkj.cod.gwgz.api;

import com.tlkj.cod.core.annotation.ParamNotNull;
import com.tlkj.cod.gwgz.service.GwgzCollectService;
import com.tlkj.cod.model.common.GeneralResponse;
import com.tlkj.cod.model.common.Response;
import com.tlkj.cod.model.common.SystemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Desc 收藏接口
 *
 * @author sourcod
 * @version 1.0
 * @className GwgzCollectApi
 * @date 2019/4/29 6:43 PM
 */
@RestController
@RequestMapping("gwgz/collect")
public class GwgzCollectApi extends GeneralResponse {

    @Autowired
    GwgzCollectService gwgzCollectService;

    @ParamNotNull(parameter = "openid")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Response list(HttpServletRequest request) {
        String openid = request.getParameter("openid");
        String page = request.getParameter("page");
        String pageSize = request.getParameter("pageSize");
        SystemResponse response = gwgzCollectService.list(openid, page, pageSize);
        return response.toResponse();
    }

    @ParamNotNull(parameter = "openid")
    @RequestMapping(value = "save", method = RequestMethod.GET)
    public Response save(HttpServletRequest request) {
        String openid = request.getParameter("openid");
        String word = request.getParameter("word");
        String annotation = request.getParameter("annotation");
        String source = request.getParameter("source");
        String original = request.getParameter("original");
        String sort = request.getParameter("sort");
        SystemResponse response = gwgzCollectService.save();
        return response.toResponse();
    }

    @ParamNotNull(parameter = "openid")
    @RequestMapping(value = "get", method = RequestMethod.GET)
    public Response get(HttpServletRequest request) {
        String openid = request.getParameter("openid");
        String id = request.getParameter("id");
        SystemResponse response = gwgzCollectService.get(openid, id);
        return response.toResponse();
    }

    @ParamNotNull(parameter = "openid")
    @RequestMapping(value = "del", method = RequestMethod.GET)
    public Response del(HttpServletRequest request) {
        String openid = request.getParameter("openid");
        String id = request.getParameter("id");
        SystemResponse response = gwgzCollectService.del(openid, id);
        return response.toResponse();
    }

}
