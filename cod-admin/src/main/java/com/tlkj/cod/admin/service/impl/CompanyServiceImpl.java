/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.admin.service.impl;

import com.tlkj.cod.admin.service.CompanyService;
import com.tlkj.cod.admin.service.SystemSetService;
import com.tlkj.cod.common.CodCommonDate;
import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Updater;
import com.tlkj.cod.log.annotation.Log;
import com.tlkj.cod.log.service.LogService;
import com.tlkj.cod.model.enums.StatusCode;
import com.tlkj.cod.model.system.dto.CodFrameCompanyDto;
import com.tlkj.cod.model.system.dto.CodFrameCompanyListDto;
import com.tlkj.cod.model.system.entity.CodFrameCompanyDo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Desc 公司管理Service Impl
 *
 * @author sourcod
 * @version 1.0
 * @className CompanyServiceImpl
 * @date 2018/11/16 12:19 PM
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    Finder finder;

    @Autowired
    Updater updater;

    @Autowired
    LogService logService;

    @Autowired
    SystemSetService setService;


    /**
     * 保存公司信息
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
    @Log(name = "保存公司信息")
    @Override
    public StatusCode saveCompany(String companyId, String companyName, String companyNickName, String companyNo, String companyContact, String companyFax, String companyPhone, String companyEin, String status) {
        Updater.Update update = StringUtils.isNotBlank(companyId) ? updater.update(CodFrameCompanyDo.TABLE_NAME)
                .set("update_time", CodCommonDate.getDate(CodCommonDate.PATTERN_YMDHMS))
                .where("id", companyId) : updater.insert(CodFrameCompanyDo.TABLE_NAME).setId();
        update = StringUtils.isNotBlank(companyName) ? update.set("dept_name", companyName) : update;
        update = StringUtils.isNotBlank(companyNo) ? update.set("dept_no", companyNo) : update;
        update = StringUtils.isNotBlank(companyNickName) ? update.set("dept_no", companyNickName) : update;
        update = StringUtils.isNotBlank(companyContact) ? update.set("company_contact", companyContact) : update;
        update = StringUtils.isNotBlank(companyFax) ? update.set("company_fax", companyFax) : update;
        update = StringUtils.isNotBlank(companyPhone) ? update.set("company_phone", companyPhone) : update;
        update = StringUtils.isNotBlank(companyEin) ? update.set("company_ein", companyEin) : update;
        update = verifyStatus(status) ?  update.set("status", status) : update;
        update = StringUtils.isNotBlank(status) ? update.set("dept_no", status) : update;
        int i = update.update();
        return i == 1 ? StatusCode.SUCCESS_CODE : StatusCode.FAIL_CODE;
    }

    /**
     * 查询公司列表
     * @param companyName     公司名称
     * @param companyNickName 公司昵称
     * @param companyNo       公司编号
     * @param companyContact  公司联系人
     * @param companyFax      传真
     * @param companyPhone    公司电话
     * @param companyEin      税号
     * @param status          状态
     * @return
     */
    @Log(name = "查询公司列表")
    @Override
    public Page<List<CodFrameCompanyListDto>> listCompany(String companyName, String companyNickName, String companyNo, String companyContact, String companyFax, String companyPhone, String companyEin, String status) {
        logService.info("这是一个测试日志, 1={}, 2={}", "one", "two");
        logService.trace("这是一个测试数组日志, 1={}, 2={}", new Object[]{"one", "two"});
        return null;
    }

    /**
     * 根据Id获取公司信息
     * @param id id
     * @return 公司信息
     */
    @Log(name = "根据id获取公司信息")
    @Override
    public CodFrameCompanyDto getCompanyById(String id) {
        CodFrameCompanyDo codFrameCompanyDo = finder.from(CodFrameCompanyDo.TABLE_NAME).where("id", id).first(CodFrameCompanyDo.class);
        if (codFrameCompanyDo == null){
            return null;
        }
        CodFrameCompanyDto codFrameCompanyDto = new CodFrameCompanyDto();
        codFrameCompanyDto.setId(codFrameCompanyDo.getId());
        codFrameCompanyDto.setCompanyName(codFrameCompanyDo.getCompany_name());
        codFrameCompanyDto.setCompanyAddress(codFrameCompanyDo.getCompany_address());
        codFrameCompanyDto.setCompanyContact(codFrameCompanyDo.getCompany_contact());
        codFrameCompanyDto.setCompanyEin(codFrameCompanyDo.getCompany_ein());
        codFrameCompanyDto.setCompanyFax(codFrameCompanyDo.getCompany_fax());
        codFrameCompanyDto.setCompanyNickName(codFrameCompanyDo.getCompany_nickname());
        codFrameCompanyDto.setCompanyNo(codFrameCompanyDo.getCompany_no());
        codFrameCompanyDto.setCompanyPhone(codFrameCompanyDo.getCompany_phone());
        codFrameCompanyDto.setState(codFrameCompanyDo.getState());
        codFrameCompanyDto.setSort(codFrameCompanyDo.getSort());
        codFrameCompanyDto.setCreateTime(codFrameCompanyDo.getCreate_time());
        codFrameCompanyDto.setUpdateTime(codFrameCompanyDo.getUpdate_time());
        return codFrameCompanyDto;
    }

    /**
     * 删除菜单
     * @param ids 主键
     * @return
     */
    @Log(name = "删除菜单")
    @Override
    public StatusCode delMenu(String ids) {
        String[] menus = ids.split(",");
        int i = 0;
        try {
            for (String menuid : menus){
                String menuIdTrim = menuid.trim();
                if (StringUtils.isNotBlank(menuIdTrim)){
                    // 删除本菜单
                    i = i + updater.delete(CodFrameCompanyDo.TABLE_NAME).where("id", menuIdTrim).update();
                    // 删除子菜单
                    int childNum = updater.delete(CodFrameCompanyDo.TABLE_NAME).where("p_id", menuIdTrim).update();
                    logService.info("删除菜单成功, 本菜单数量={}, 子菜单数量={}", menuIdTrim, childNum);
                }
            }
        } catch (Exception e){
            logService.error("删除菜单错误, {}", e.getMessage());
            return StatusCode.FAIL_CODE;
        }
        if (i > 0){
            return StatusCode.SUCCESS_CODE;
        }
        return StatusCode.FAIL_CODE;
    }

    /**
     * 判断状态码是否符合规则
     * @param status 状态码
     * @return 是否符合
     */
    private boolean verifyStatus(String status){
        return StringUtils.isNotBlank(status) && ("1".equals(status) || "2".equals(status));
    }
}
