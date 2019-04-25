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

import com.tlkj.cod.log.annotation.Log;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Updater;
import com.tlkj.cod.model.business.jx.dto.JxCategoryDto;
import com.tlkj.cod.model.business.jx.entity.JxCategoryDo;
import com.tlkj.cod.model.common.SystemResponse;
import com.tlkj.cod.model.enums.StatusCode;
import com.tlkj.cod.service.jx.JxCategoryService;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    Updater updater;

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

    /**
     * 保存分类
     * @param id   主键
     * @param name 分类名称
     * @param pId  父id
     * @param sort 顺序
     * @return
     */
    @Override
    public SystemResponse save(String id, String name, String pId, String sort) {
        SystemResponse response = new SystemResponse();
        Updater.Update update = StringUtils.isEmpty(id) ? updater.insert(JxCategoryDo.TABLE_NAME).set("id", id) : updater.update(JxCategoryDo.TABLE_NAME).where("id", id).set("update_time", "now()", Void.class);
        update.set("name", name).set("p_id", pId).set("sort", sort);
        int num = update.update();
        if (num == 1){
            return response.success();
        }
        return response.fail();
    }

    @Override
    public SystemResponse del(String id, String state) {
        SystemResponse response = new SystemResponse();
        switch (state){
            // 删除子类
            case "1":
                int i = updater.delete(JxCategoryDo.TABLE_NAME).where("id", id).update();
                updater.delete(JxCategoryDo.TABLE_NAME).where("p_id", id).update();
                if (i == 0){
                    return response.success();
                }
                return response.fail();
                // 不删除子类
            case "0":
                int num = finder.from(JxCategoryDo.TABLE_NAME).where("p_id", id).select("count(*)").firstForObject(Integer.class);
                if (num > 0){
                    return response.fail();
                }
                i = updater.delete(JxCategoryDo.TABLE_NAME).where("id", id).update();
                if (i == 1){
                    return response.success();
                }
                return response.fail();
            default:

                break;
        }
        return response;
    }

    @Override
    public SystemResponse list(String name, String pId) {
        return null;
    }
}
