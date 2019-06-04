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

import com.tlkj.cod.admin.service.ResourceService;
import com.tlkj.cod.core.security.util.PermissionUtil;
import com.tlkj.cod.model.system.dto.CodFramePermissionListDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc 资源ServiceImpl
 *
 * @author sourcod
 * @version 1.0
 * @className ResourceServiceImpl
 * @date 2018/11/7 下午10:30
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    /**
     * 获取资源列表
     */
    @Override
    public List<CodFramePermissionListDto> listResource() {
        List<CodFramePermissionListDto> dtos = new ArrayList<>();
        PermissionUtil.getAllPermission().forEach(item -> {
            CodFramePermissionListDto dto = new CodFramePermissionListDto();
            dto.setName(item.getName());
            dto.setPermission(item.getNum());
            dtos.add(dto);
        });
        return dtos;
    }
}
