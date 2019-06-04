/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.admin.service;

import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.model.enums.StatusCode;
import com.tlkj.cod.model.system.dto.CodFrameDeptListDto;

import java.util.List;

/**
 * Desc 部门Service
 *
 * @author sourcod
 * @version 1.0
 * @className DeptService
 * @date 2018/11/7 下午10:23
 */
public interface DeptService {

    /**
     * 获取部门列表
     * @param deptname  部门名称
     * @param deptNo    部门编号
     * @param deptLevel 部门级别
     * @param page      当前页
     * @param pageSize  每页显示条数
     * @return 部门列表
     */
    Page<List<CodFrameDeptListDto>> listDept(String deptname, String deptNo, String deptLevel, String page, String pageSize);

    /**
     * 保存部门
     * @param deptId    部门Id
     * @param deptName  部门名称
     * @param deptNo    部门编号
     * @param deptLevel 部门等级
     * @param deptAdmin 部门管理员
     * @param pId       父Id
     * @param companyId 公司Id
     * @param status    状态
     * @param sort      排序
     * @return
     */
    StatusCode saveDept(String deptId, String deptName, String deptNo, String deptLevel, String deptAdmin, String pId, String companyId, String status, String sort);

    /**
     * 删除部门
     * @param deptId 部门Id
     * @return
     */
    StatusCode delDept(String deptId);

}
