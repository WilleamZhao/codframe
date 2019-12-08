/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.admin.service.impl;

import com.tlkj.cod.admin.model.dto.CodAdminPermissionListDto;
import com.tlkj.cod.admin.service.CodAdminResourceService;
import com.tlkj.cod.core.security.util.PermissionUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc 资源ServiceImpl
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminResourceServiceImpl
 * @date 2018/11/7 下午10:30
 */
@Service
public class CodAdminResourceServiceImpl implements CodAdminResourceService {

    /**
     * 获取资源列表
     */
    @Override
    public List<CodAdminPermissionListDto> listResource() {
        List<CodAdminPermissionListDto> dtos = new ArrayList<>();
        PermissionUtil.getAllPermission().forEach(item -> {
            CodAdminPermissionListDto dto = new CodAdminPermissionListDto();
            dto.setName(item.getName());
            dto.setPermission(item.getNum());
            dtos.add(dto);
        });
        return dtos;
    }
}
