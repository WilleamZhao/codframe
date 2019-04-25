/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.service.system.impl;

import com.tlkj.cod.log.annotation.Log;
import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Pagination;
import com.tlkj.cod.dao.jdbc.Updater;
import com.tlkj.cod.model.system.dto.CodFrameCompanyDto;
import com.tlkj.cod.model.system.dto.CodFrameDeptListDto;
import com.tlkj.cod.model.system.entity.CodFrameDeptDo;
import com.tlkj.cod.model.enums.StatusCode;
import com.tlkj.cod.service.system.CompanyService;
import com.tlkj.cod.service.system.DeptService;
import com.tlkj.cod.common.CodCommonDate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc 部门管理Service Impl
 *
 * @author sourcod
 * @version 1.0
 * @className DeptServiceImpl
 * @date 2018/11/7 下午10:23
 */
@Service
public class DeptServiceImpl implements DeptService {

    private static Logger logger = LoggerFactory.getLogger(DeptServiceImpl.class);

    @Autowired
    Finder finder;

    @Autowired
    Updater updater;

    @Autowired
    CompanyService companyService;

    @Log(name = "获取部门列表")
    @Override
    public Page<List<CodFrameDeptListDto>> listDept(String deptName, String deptNo, String deptLevel, String page, String pageSize) {
        Finder.Query query = finder.from(CodFrameDeptDo.TABLE_NAME).not("state", "-1").orderBy("sort");
        query = StringUtils.isNotBlank(deptName) ?  query.where("dept_name", deptName) : query;
        query = StringUtils.isNotBlank(deptNo) ?  query.where("dept_no", deptNo) : query;
        query = StringUtils.isNotBlank(deptLevel) ?  query.where("dept_level", deptLevel) : query;
        int currentPage = StringUtils.isNotBlank(page) ? Integer.parseInt(page) : 1;
        int perPage = StringUtils.isNotBlank(pageSize) ? Integer.parseInt(pageSize) : Pagination.DEFAULT_PER_PAGE;
        Pagination<CodFrameDeptDo> pagination;
        try {
            pagination = query.paginate(CodFrameDeptDo.class, currentPage, perPage);
        } catch (Exception e) {
            logger.error("sql查询错误:={}", e.getMessage());
            return null;
        }
        if (pagination == null) {
            return new Page<>();
        }

        List<CodFrameDeptDo> codFrameDeptDos = pagination.getData();
        List<CodFrameDeptListDto> codFrameDeptListDto = new ArrayList<>();
        for (CodFrameDeptDo codFrameDeptDo : codFrameDeptDos){
            CodFrameDeptListDto listDto = new CodFrameDeptListDto();
            listDto.setDeptName(codFrameDeptDo.getDept_name());
            listDto.setDeptLevel(codFrameDeptDo.getDept_level());
            listDto.setDeptNo(codFrameDeptDo.getDept_no());
            listDto.setId(codFrameDeptDo.getId());
            listDto.setCompanyId(codFrameDeptDo.getCompany_id());
            // 获取公司名
            CodFrameCompanyDto codFrameCompanyDto = companyService.getCompanyById(codFrameDeptDo.getCompany_id());
            listDto.setCompanyName(codFrameCompanyDto != null ? codFrameCompanyDto.getCompanyName() : "");

            listDto.setStatus(codFrameDeptDo.getState());
            codFrameDeptListDto.add(listDto);
        }
        Page<List<CodFrameDeptListDto>> tempPage = new Page<>(codFrameDeptListDto, pagination);
        return tempPage;
    }

    /**
     * 保存部门信息
     * @param deptId    部门Id
     * @param deptName  部门名称
     * @param deptNo    部门编号
     * @param deptLevel 部门等级
     * @param deptAdmin 部门管理员
     * @param pId       父Id
     * @param companyId 公司Id
     * @param state    状态
     * @param sort      排序
     * @return
     */
    @Log(name = "保存部门信息")
    @Override
    public StatusCode saveDept(String deptId, String deptName, String deptNo, String deptLevel, String deptAdmin, String pId, String companyId, String state, String sort) {

        // 有主键更新，没有新增
        Updater.Update update = StringUtils.isBlank(deptId) ? updater.insert(CodFrameDeptDo.TABLE_NAME).setId() : updater.update(CodFrameDeptDo.TABLE_NAME).set("update_time", CodCommonDate.getDate(CodCommonDate.PATTERN_YMDHMS)).where("id", deptId);
        update = StringUtils.isNotBlank(deptName) ? update.set("dept_name", deptName) : update;
        update = StringUtils.isNotBlank(deptNo) ? update.set("dept_no", deptNo) : update;
        update = StringUtils.isNotBlank(deptLevel) ? update.set("dept_level", deptLevel) : update;
        update = StringUtils.isNotBlank(deptAdmin) ? update.set("dept_admin", deptAdmin) : update;
        update = StringUtils.isNotBlank(pId) ? update.set("p_id", pId) : update;
        update = StringUtils.isNotBlank(companyId) ? update.set("company_id", companyId) : update;
        // 判断状态是否符合规则
        update = verifyStatus(state) ? update.set("state", state) : update;
        int i = update.update();
        return i == 1 ? StatusCode.SUCCESS_CODE : StatusCode.FAIL_CODE;
    }

    /**
     * 删除部门
     * @param deptId 部门Id
     * @return
     */
    @Log(name = "删除部门信息")
    @Override
    public StatusCode delDept(String deptId) {
        String[] deptIds = deptId.split(",");
        int i = 0;
        for (String id : deptIds){
            i = i + updater.update(CodFrameDeptDo.TABLE_NAME).set("status", "-1").where("id", id.trim()).update();
        }
        return i == deptIds.length ? StatusCode.SUCCESS_CODE : StatusCode.FAIL_CODE;
    }

    private boolean verifyStatus(String status){
        return StringUtils.isNotBlank(status) && ("1".equals(status) || "2".equals(status));
    }
}
