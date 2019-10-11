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

import com.tlkj.cod.admin.model.bo.CodAdminDictItemBo;
import com.tlkj.cod.admin.model.dto.CodAdminDictItemListDto;
import com.tlkj.cod.admin.model.dto.CodAdminDictTypeListDto;
import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.model.enums.StatusCode;

import java.util.List;

/**
 * Desc 字典管理Service
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminDictService
 * @date 2018/11/8 下午9:47
 */
public interface CodAdminDictService {

    /**
     * 验证代码是否重复
     * @param code 代码
     * @param type 1: 字典; 2: 类型;
     * @return
     */
    Boolean verifyCode(String code, String type);

    /**
     * 获取字典类型接口
     * @param typeName   类型名称
     * @param typeCode   类型代码
     * @param allpin     全拼
     * @param typeStatus 字典状态
     * @param page       第几页
     * @param pageSize   一页几条
     * @return
     */
    Page<List<CodAdminDictTypeListDto>> listDictType(String typeName, String typeCode, String allpin, String typeStatus, String page, String pageSize);

    /**
     * 根据字典码获取全部字典数据
     * @param typeId      类型Id
     * @param allPin      全拼
     * @param itemStatus  状态
     * @param itemCode    字典码
     * @param itemName    字典名称
     * @param page        第几页
     * @param pageSize    一页几条
     * @param simplePin   简拼
     * @return
     */
    Page<List<CodAdminDictItemListDto>> listDictItem(String typeId, String page, String pageSize, String allPin, String itemName, String itemCode, String itemStatus, String simplePin);

    /**
     * 根据类型获取字典数据
     * @param typeId   类型ID
     * @param typeCode 类型代码
     * @return
     */
    Page<List<CodAdminDictItemListDto>> listDictItemByType(String typeId, String typeCode, String page, String pageSize);

    /**
     * 保存字典数据
     * @return
     */
    StatusCode saveDictItem(String itemId, String typeId, String itemCode, String itemName, String itemValue, String isFixed, String itemStatus, String englishName, String sort, String remark);

    /**
     * 保存字典类型
     * @return
     */
    StatusCode saveDictType(String typeId, String typeCode, String typeName, String englishName, String typeStatus, String remark, String sort);

    /**
     * 根据类型code 获取item
     * @param code typeCode
     * @return
     */
    List<CodAdminDictItemBo> getItemByType(String code);

    /**
     * 根据数据code 获取item
     * @param code itemCode
     * @return
     */
    CodAdminDictItemBo getItem(String code);

    /**
     * 删除字典数据
     * @param id id
     * @return
     */
    StatusCode delDictItem(String id);

    /**
     * 删除字典类型
     * @param id id
     * @return
     */
    StatusCode delDictType(String id);

}
