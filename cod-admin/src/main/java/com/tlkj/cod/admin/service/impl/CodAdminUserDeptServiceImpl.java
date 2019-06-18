/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.admin.service.impl;

import com.tlkj.cod.admin.service.CodAdminUserDeptService;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Updater;
import com.tlkj.cod.model.system.dto.CodFrameUserDeptListDto;
import com.tlkj.cod.model.system.dvo.CodDaoFrameUserDeptDvo;

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
    public List<CodFrameUserDeptListDto> listDeptByUserId(String userId) {
        CodDaoFrameUserDeptDvo dvo = new CodDaoFrameUserDeptDvo();
        Finder.Query query = finder.fromView(dvo);
        query.where(CodDaoFrameUserDeptDvo.USER_TABLE + ".id", userId);
        List<CodDaoFrameUserDeptDvo> deptDvos = query.all(CodDaoFrameUserDeptDvo.class);

        List<CodFrameUserDeptListDto> dtos = new ArrayList<>();
        for (CodDaoFrameUserDeptDvo deptDvo : deptDvos){
            CodFrameUserDeptListDto dto = new CodFrameUserDeptListDto();
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
