/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.service.jx.impl;

import com.tlkj.cod.core.annotation.Log;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.model.business.jx.dto.JxCategoryDto;
import com.tlkj.cod.model.business.jx.entity.JxCategoryDo;
import com.tlkj.cod.model.common.SystemResponse;
import com.tlkj.cod.model.enums.StatusCode;
import com.tlkj.cod.service.jx.JxCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc 精享分类service impl
 *
 * @author sourcod
 * @version 1.0
 * @className JxCategoryServiceImpl
 * @date 2019/3/24 6:02 PM
 */
@Service
public class JxCategoryServiceImpl implements JxCategoryService {

    @Autowired
    Finder finder;

    /**
     * 获取分类
     * @param id 分类id
     * @return
     */
    @Log(name = "获取分类")
    @Override
    public SystemResponse getCategory(String id) {
        SystemResponse systemResponse = new SystemResponse();
        JxCategoryDo jxCategoryDo = finder.from(JxCategoryDo.TABLE_NAME).where("id", id).first(JxCategoryDo.class);

        if (jxCategoryDo == null){
            return systemResponse.success(StatusCode.DATA_NULL_CODE);
        }

        JxCategoryDto dto = jxCategoryDo.toDto(JxCategoryDto.class);

        List<JxCategoryDo> jxCategoryDtoList = finder.from(JxCategoryDo.TABLE_NAME)
                .where("p_id", jxCategoryDo.getId()).all(JxCategoryDo.class);

        List<JxCategoryDto> dtos = new ArrayList<>();
        jxCategoryDtoList.forEach(item -> dtos.add(item.toDto(JxCategoryDto.class)));
        dto.setChildList(dtos);
        return systemResponse.success(dto);
    }
}
