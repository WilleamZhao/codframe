/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.admin.action;

import com.tlkj.cod.admin.service.DictService;
import com.tlkj.cod.core.annotation.ParamNotNull;
import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.model.common.GeneralResponse;
import com.tlkj.cod.model.common.Response;
import com.tlkj.cod.model.enums.StatusCode;
import com.tlkj.cod.model.system.dto.CodFrameDictItemListDto;
import com.tlkj.cod.model.system.dto.CodFrameDictTypeListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Desc 字典管理Action
 *
 * @author sourcod
 * @version 1.0
 * @className DictAction
 * @date 2018/11/8 下午9:43
 */
@RestController
@RequestMapping("system/dict")
public class DictAction extends GeneralResponse {

    @Autowired
    @Lazy
    DictService dictService;

    /**
     * 获取字典类型列表
     * @param request
     * @return
     */
    @RequestMapping(value = "listDictType", method = {RequestMethod.GET})
    public Response listDictType(HttpServletRequest request){
        String typeName = request.getParameter("typeName");
        String typeCode = request.getParameter("typeCode");
        String allpin = request.getParameter("allpin");
        String typeStatus = request.getParameter("typeStatus");
        String page = request.getParameter("page");
        String pageSize = request.getParameter("pageSize");
        Page<List<CodFrameDictTypeListDto>> listPage = dictService.listDictType(typeName, typeCode, allpin, typeStatus, page, pageSize);
        return listPage == null ? super.fail() : super.success(listPage);
    }

    /**
     * 获取字典数据列表
     */
    @RequestMapping(value = "listDictItem", method = {RequestMethod.GET})
    public Response listDictItem(HttpServletRequest request){
        String typeId = request.getParameter("typeId");
        String page = request.getParameter("page");
        String pageSize = request.getParameter("pageSize");
        String allpin = request.getParameter("itemAllPin");
        String itemName = request.getParameter("itemName");
        String itemCode = request.getParameter("itemCode");
        String itemStatus = request.getParameter("itemStatus");
        String simplePin = request.getParameter("simplepin");
        Page<List<CodFrameDictItemListDto>> listPage = dictService.listDictItem(typeId, page, pageSize, allpin, itemName, itemCode, itemStatus, simplePin);
        return listPage == null ? super.fail() : super.success(listPage);
    }

    /**
     * 获取字典数据
     */
    @ParamNotNull(parameter = "itemId")
    @RequestMapping(value = "getDictItem", method = {RequestMethod.GET})
    public Response getDictItem(HttpServletRequest request){
        String itemId = request.getParameter("itemId");

        return super.success();
    }

    /**
     * 获取字典类型数据
     */
    @RequestMapping(value = "getDictType", method = {RequestMethod.GET})
    public Response getDictType(HttpServletRequest request){
        String typeId = request.getParameter("typeId");

        return super.success();
    }

    /**
     * 保存字典类型
     * @param request
     * @return
     */
    @ParamNotNull(parameter = "typeCode, typeName")
    @RequestMapping(value = "saveDictType", method = {RequestMethod.POST})
    public Response saveDictType(HttpServletRequest request){
        String typeId = request.getParameter("typeId");
        String typeCode = request.getParameter("typeCode");
        String typeName = request.getParameter("typeName");
        String englishName = request.getParameter("englishName");
        String typeStatus = request.getParameter("typeStatus");
        String remark = request.getParameter("remark");
        StatusCode statusCode = dictService.saveDictType(typeId, typeCode, typeName, englishName, typeStatus, remark);
        return StatusCode.verifyStatusCode(statusCode) ? super.success() : super.fail();
    }

    /**
     * 保存字典数据
     */
    @ParamNotNull(parameter = "typeId, itemName, itemCode")
    @RequestMapping(value = "saveDictItem", method = {RequestMethod.POST})
    public Response saveDictItem(HttpServletRequest request){
        String itemId = request.getParameter("itemId");
        String typeId = request.getParameter("typeId");
        String itemName = request.getParameter("itemName");
        String itemCode = request.getParameter("itemCode");
        String itemValue = request.getParameter("itemValue");
        String isFixed = request.getParameter("isFixed");
        String itemStatus = request.getParameter("itemStatus");
        String englishName = request.getParameter("englishName");
        String sort = request.getParameter("sort");
        String remark = request.getParameter("remark");
        StatusCode statusCode = dictService.saveDictItem(itemId, typeId, itemCode, itemName, itemValue, isFixed, itemStatus, englishName, sort, remark);
        return StatusCode.verifyStatusCode(statusCode) ? super.success() : super.fail();
    }

    /**
     * 删除字典数据
     */
    @ParamNotNull(parameter = "id")
    @RequestMapping(value = "delDictItem", method = {RequestMethod.POST})
    public Response delDictItem(HttpServletRequest request){
        String itemId = request.getParameter("id");
        StatusCode statusCode = dictService.delDictItem(itemId);
        return StatusCode.verifyStatusCode(statusCode) ? super.success() : super.fail();
    }

    /**
     * 删除字典类型
     */
    @ParamNotNull(parameter = "id")
    @RequestMapping(value = "delDictType", method = {RequestMethod.POST})
    public Response delDictType(HttpServletRequest request){
        String itemId = request.getParameter("id");
        StatusCode statusCode = dictService.delDictItem(itemId);
        return StatusCode.verifyStatusCode(statusCode) ? super.success() : super.fail();
    }


}
