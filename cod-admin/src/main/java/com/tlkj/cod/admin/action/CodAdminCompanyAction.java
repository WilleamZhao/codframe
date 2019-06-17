/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.admin.action;

import com.tlkj.cod.admin.service.CodAdminCompanyService;
import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.model.common.GeneralResponse;
import com.tlkj.cod.model.common.Response;
import com.tlkj.cod.model.system.dto.CodFrameCompanyDto;
import com.tlkj.cod.model.system.dto.CodFrameCompanyListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Desc 公司管理Action
 *
 * @author sourcod
 * @version 1.0
 * @className CompanyAction
 * @date 2018/10/29 下午2:24
 */
@RestController
@RequestMapping("system/company")
public class CodAdminCompanyAction extends GeneralResponse {

    @Autowired
    CodAdminCompanyService codAdminCompanyService;

    /**
     * 查询公司列表接口
     */
    @RequestMapping(value = "list", method = {RequestMethod.GET})
    public Response listCompany(HttpServletRequest request) {
        String companyName = request.getParameter("companyName");
        String companyNo = request.getParameter("companyNo");
        String page = request.getParameter("page");
        String pageSize = request.getParameter("pageSize");
        Page<List<CodFrameCompanyListDto>> listPage = codAdminCompanyService.listCompany(companyName, companyNo, page, pageSize);
        if (listPage != null) {
            super.success(listPage);
        }
        return super.fail();
    }

    /**
     * 保存公司接口
     */
    @RequestMapping(value = "save", method = {RequestMethod.POST})
    public Response saveCompany(HttpServletRequest request) {
        String companyId = request.getParameter("companyId");
        String companyName = request.getParameter("companyName");
        String companyNickName = request.getParameter("companyNickName");
        String companyNo = request.getParameter("companyNo");
        String companyContact = request.getParameter("companyContact");
        String companyFax = request.getParameter("companyFax");
        String companyPhone = request.getParameter("companyPhone");
        String companyEin = request.getParameter("companyEin");
        String status = request.getParameter("status");
        String sort = request.getParameter("sort");
        codAdminCompanyService.saveCompany(companyId, companyName, companyNickName, companyNo, companyContact, companyFax, companyPhone, companyEin, status);
        return super.success();
    }

    /**
     * 获取公司信息接口
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "get", method = {RequestMethod.GET})
    public Response getCompany(HttpServletRequest request) {
        String id = request.getParameter("id");
        CodFrameCompanyDto codFrameCompanyDto = codAdminCompanyService.getCompanyById(id);
        return codFrameCompanyDto == null ? super.fail() : super.success(codFrameCompanyDto);
    }

    /**
     * 删除公司信息接口
     */
    @RequestMapping(value = "del", method = {RequestMethod.GET})
    public Response delCompany(HttpServletRequest request) {
        String id = request.getParameter("ids");
        CodFrameCompanyDto codFrameCompanyDto = codAdminCompanyService.getCompanyById(id);
        return codFrameCompanyDto == null ? super.fail() : super.success(codFrameCompanyDto);
    }
}
