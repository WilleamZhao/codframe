/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.admin.service;

import com.tlkj.cod.admin.model.dto.CodAdminCompanyDto;
import com.tlkj.cod.admin.model.dto.CodAdminCompanyListDto;
import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.model.enums.StatusCode;

import java.util.List;

/**
 * Desc 公司管理Service
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminCompanyService
 * @date 2018/10/30 下午4:13
 */
public interface CodAdminCompanyService {

    /**
     * 保存公司信息
     *
     * @param companyId       公司Id
     * @param companyName     公司名称
     * @param companyNickName 公司昵称
     * @param companyNo       公司编号
     * @param companyContact  公司联系方式
     * @param companyFax      公司传真
     * @param companyPhone    公司电话
     * @param companyEin      公司税号
     * @param status          状态
     * @return
     */
    StatusCode saveCompany(String companyId, String companyName, String companyNickName, String companyNo, String companyContact, String companyFax, String companyPhone, String companyEin, String status);

    /**
     * 获取公司列表
     *
     * @param companyName 公司名称
     * @param companyNo   公司编号
     * @param page
     * @param pageSize
     * @return
     */
    Page<List<CodAdminCompanyListDto>> listCompany(String companyName, String companyNo, String page, String pageSize);

    /**
     * 根据id获取公司信息
     *
     * @param id id
     * @return 公司信息
     */
    CodAdminCompanyDto getCompanyById(String id);

    /**
     * 删除菜单
     *
     * @param ids 主键
     * @return 成功/失败
     */
    StatusCode delMenu(String ids);

}
