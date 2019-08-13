/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.admin.service.impl;

import com.tlkj.cod.admin.model.dto.CodAdminUserDeptListDto;
import com.tlkj.cod.admin.model.dvo.CodAdminUserDeptDvo;
import com.tlkj.cod.admin.service.CodAdminUserDeptService;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Updater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc 用户部门ServiceImpl
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminUserDeptServiceImpl
 * @date 2019/1/7 11:49 PM
 */
@Service
public class CodAdminUserDeptServiceImpl implements CodAdminUserDeptService {

    @Autowired
    Finder finder;

    @Autowired
    Updater updater;

    /**
     * 根据用户id获取部门
     * @param userId 用户id
     * @return 部门列表
     */
    @Override
    public List<CodAdminUserDeptListDto> listDeptByUserId(String userId) {
        CodAdminUserDeptDvo dvo = new CodAdminUserDeptDvo();
        Finder.Query query = finder.from(dvo);
        query.where(CodAdminUserDeptDvo.USER_TABLE + ".id", userId);
        List<CodAdminUserDeptDvo> deptDvos = query.all(CodAdminUserDeptDvo.class);

        List<CodAdminUserDeptListDto> dtos = new ArrayList<>();
        for (CodAdminUserDeptDvo deptDvo : deptDvos){
            CodAdminUserDeptListDto dto = new CodAdminUserDeptListDto();
            dto.setId(deptDvo.getId());
            dto.setDeptId(deptDvo.getDeptId());
            dto.setDeptName(deptDvo.getDeptName());
            dto.setUserId(deptDvo.getUserId());
            dto.setUserName(deptDvo.getUserName());
            dtos.add(dto);
        }
        return dtos;
    }

}
