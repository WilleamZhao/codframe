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

import com.tlkj.cod.admin.service.DeptService;
import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.model.common.GeneralResponse;
import com.tlkj.cod.model.common.Response;
import com.tlkj.cod.model.enums.StatusCode;
import com.tlkj.cod.model.system.dto.CodFrameDeptListDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Desc 部门管理Action
 *
 * @author sourcod
 * @version 1.0
 * @className DeptAction
 * @date 2018/10/29 下午2:24
 */
@RestController
@RequestMapping("system/dept")
public class DeptAction extends GeneralResponse {

    @Autowired
    DeptService deptService;

    /**
     * 获取部门列表
     */
    @RequestMapping(value = "list", method = {RequestMethod.GET})
    public Response listDept(HttpServletRequest request){
        String deptName = request.getParameter("deptName");
        String deptNo = request.getParameter("deptNo");
        String deptLevel = request.getParameter("deptLevel");
        String page = request.getParameter("page");
        String pageSize = request.getParameter("pageSize");
        Page<List<CodFrameDeptListDto>> listPage = deptService.listDept(deptName, deptNo, deptLevel, page, pageSize);
        return listPage == null ? super.fail() : super.success(listPage);
    }

    /**
     * 获取部门信息
     */
    @RequestMapping(value = "get", method = {RequestMethod.GET})
    public Response getDept(HttpServletRequest request){
        String id = request.getParameter("deptId");
        return super.success();
    }

    /**
     * 保存部门
     */
    @RequestMapping(value = "save", method = {RequestMethod.POST})
    public Response saveDept(HttpServletRequest request){
        String deptId = request.getParameter("deptId");
        String deptName = request.getParameter("deptName");
        String deptNo = request.getParameter("deptNo");
        String deptLevel = request.getParameter("deptLevel");
        String deptAdmin = request.getParameter("deptAdmin");
        String pId = request.getParameter("pId");
        String companyId = request.getParameter("companyId");
        String sort = request.getParameter("sort");
        String status = request.getParameter("status");
        StatusCode statusCode = deptService.saveDept(deptId, deptName, deptNo, deptLevel, deptAdmin, pId, companyId, status, sort);
        return StatusCode.verifyStatusCode(statusCode) ? super.success(statusCode) : super.fail(statusCode);
    }

    /**
     * 删除部门
     */
    @RequestMapping(value = "del", method = {RequestMethod.POST})
    public Response delDept(HttpServletRequest request){
        String deptId = request.getParameter("deptId");
        StatusCode statusCode = deptService.delDept(deptId);
        return StatusCode.verifyStatusCode(statusCode) ? super.success(statusCode) : super.fail(statusCode);
    }
}
