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

import com.tlkj.cod.admin.service.DictService;
import com.tlkj.cod.common.CodCommonPinyin;
import com.tlkj.cod.core.annotation.ParamNotNull;
import com.tlkj.cod.core.annotation.SystemLog;
import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Pagination;
import com.tlkj.cod.dao.jdbc.Updater;
import com.tlkj.cod.log.annotation.Log;
import com.tlkj.cod.model.enums.StatusCode;
import com.tlkj.cod.model.system.bo.CodFrameDictItemBo;
import com.tlkj.cod.model.system.dto.CodFrameDictItemListDto;
import com.tlkj.cod.model.system.dto.CodFrameDictTypeListDto;
import com.tlkj.cod.model.system.entity.CodFrameDictItemDo;
import com.tlkj.cod.model.system.entity.CodFrameDictTypeDo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc 字典管理Service Impl
 *
 * @author sourcod
 * @version 1.0
 * @className DictServiceImpl
 * @date 2018/11/8 下午9:47
 */
@Service
public class DictServiceImpl implements DictService {

    private static Logger logger =LoggerFactory.getLogger(DictServiceImpl.class);

    @Autowired
    Finder finder;

    @Autowired
    Updater updater;

    /**
     * 验证代码是否重复
     * @param code 代码
     * @param type 1: 类型; 2: 数据;
     * @return boolean
     */
    @Log(name = "验证代码是否重复")
    @Override
    public Boolean verifyCode(String code, String type) {
        Finder.Query query = null;
        switch (type){
            case "1":
                query = finder.from(CodFrameDictTypeDo.TABLE_NAME).where("type_code", code);
                break;
            case "2":
                query = finder.from(CodFrameDictItemDo.TABLE_NAME).where("item_code", code);
                break;
            default:

                break;
        }

        if (query == null){
            return true;
        }
        int num = query.select("count(*)").firstForObject(Integer.class);
        if (num > 0){
            return true;
        }
        return false;
    }

    /**
     * 获取字典类型列表
     * @param typeName    类型名称
     * @param typeCode    类型编码
     * @param allpin      全拼
     * @param typeStatus  状态
     * @return
     */
    @Log(name = "获取字典类型列表")
    @Override
    public Page<List<CodFrameDictTypeListDto>> listDictType(String typeName, String typeCode, String allpin, String typeStatus, String page, String pageSize) {
        Finder.Query query = finder.from(CodFrameDictTypeDo.TABLE_NAME).not("state", "-1").orderBy("sort");
        if (StringUtils.isNotBlank(typeCode)){
            query.like("type_code", "%" + typeCode + "%");
        }

        if (StringUtils.isNotBlank(typeName)){
            query.like("type_name", "%" + typeName + "%");
        }

        if (StringUtils.isNotBlank(allpin)){
            query.like("allpin", "%" + allpin + "%");
        }

        if (StringUtils.isNotBlank(typeStatus)){
            query.where("state", typeStatus);
        }

        int currentPage = StringUtils.isNotBlank(page) ? Integer.parseInt(page) : 1;
        int perPage = StringUtils.isNotBlank(pageSize) ? Integer.parseInt(pageSize) : Pagination.DEFAULT_PER_PAGE;
        Pagination<CodFrameDictTypeDo> pagination;
        try {
            pagination = query.paginate(CodFrameDictTypeDo.class, currentPage, perPage);
        } catch (Exception e) {
            logger.error("sql查询错误:={}", e.getMessage());
            return null;
        }
        if (pagination == null) {
            return new Page<>();
        }

        List<CodFrameDictTypeDo> list = pagination.getData();
        List<CodFrameDictTypeListDto> listDtos = new ArrayList<>();
        list.forEach( dictTypeDo -> {
            CodFrameDictTypeListDto dictTypeListDto = new CodFrameDictTypeListDto();
            dictTypeListDto.setAllPin(dictTypeDo.getAllpin());
            dictTypeListDto.setEnglishName(dictTypeDo.getEnglish_name());
            dictTypeListDto.setId(dictTypeDo.getId());
            dictTypeListDto.setRemark(dictTypeDo.getRemark());
            dictTypeListDto.setTypeCode(dictTypeDo.getType_code());
            dictTypeListDto.setTypeName(dictTypeDo.getType_name());
            dictTypeListDto.setTypeStatus(dictTypeDo.getState());
            dictTypeListDto.setType(dictTypeDo.getType());
            listDtos.add(dictTypeListDto);
        });

        Page<List<CodFrameDictTypeListDto>> tempPage = new Page<>(listDtos, pagination);
        return tempPage;
    }

    /**
     * 获取字典数据
     * @param page         第几页
     * @param pageSize     每页显示多少条
     * @param allPin       全拼
     * @param itemName     字典名称
     * @param itemCode     字典代码
     * @param itemStatus   状态
     * @param simplePin    简拼
     * @return 字典数据列表
     */
    @Log(name = "获取字典数据")
    @Override
    public Page<List<CodFrameDictItemListDto>> listDictItem(String typeId, String page, String pageSize, String allPin, String itemName, String itemCode, String itemStatus, String simplePin) {
        Finder.Query query = finder.from(CodFrameDictItemDo.TABLE_NAME).not("state", "-1").orderBy("sort");

        query = StringUtils.isNotBlank(typeId) ?  query.where("type_id", typeId) : query;
        if (StringUtils.isNotBlank(allPin)){
            query.like("allpin", "%" + allPin + "%");
        }

        if (StringUtils.isNotBlank(itemName)){
            query.like("item_name", "%" + itemName + "%");
        }

        if (StringUtils.isNotBlank(itemCode)){
            query.like("item_code", "%" + itemCode + "%");
        }

        if (StringUtils.isNotBlank(itemStatus)){
            query.where("state", itemStatus);
        }

        if (StringUtils.isNotBlank(simplePin)){
            query.like("simplepin", "%" + simplePin + "%");
        }

        int currentPage = StringUtils.isNotBlank(page) ? Integer.parseInt(page) : 1;
        int perPage = StringUtils.isNotBlank(pageSize) ? Integer.parseInt(pageSize) : Pagination.DEFAULT_PER_PAGE;
        Pagination<CodFrameDictItemDo> pagination;
        try {
            pagination = query.paginate(CodFrameDictItemDo.class, currentPage, perPage);
        } catch (Exception e) {
            logger.error("sql查询错误:={}", e.getMessage());
            return null;
        }
        if (pagination == null) {
            return new Page<>();
        }
        List<CodFrameDictItemDo> codFrameDictItemDoList = pagination.getData();
        List<CodFrameDictItemListDto> dtoList = new ArrayList<>();

        codFrameDictItemDoList.forEach( dictItemDo -> {
            CodFrameDictItemListDto dto = new CodFrameDictItemListDto();
            dto.setId(dictItemDo.getId());
            dto.setAllPin(dictItemDo.getAllpin());
            dto.setIsFixed(dictItemDo.getIsfixed());
            dto.setEnglishName(dictItemDo.getEnglish_name());
            dto.setItemCode(dictItemDo.getItem_code());
            dto.setItemName(dictItemDo.getItem_name());
            dto.setItemValue(dictItemDo.getItem_value());
            dto.setItemStatus(dictItemDo.getState());
            dto.setRemark(dictItemDo.getRemark());
            dto.setSimplePin(dictItemDo.getSimplepin());
            dto.setSort(dictItemDo.getSort());
            dto.setTypeId(dictItemDo.getType_id());
            dto.setType(dictItemDo.getType());
            dto.setItemValue(dictItemDo.getItem_value());
            dtoList.add(dto);
        });

        Page<List<CodFrameDictItemListDto>> tempPage = new Page<>(dtoList, pagination);
        return tempPage;
    }

    /**
     * 保存字典数据
     * @param itemId      数据Id(更新需要)
     * @param typeId      类型Id
     * @param itemCode    数据代码
     * @param itemName    数据名称
     * @param isFixed     是否固定
     * @param itemStatus  数据状态(1: 启用; 0: 禁用)
     * @param englishName 英文名称
     * @param sort        排序
     * @param remark      备注
     * @return
     */
    @Log(name = "保存字典数据")
    @Override
    public StatusCode saveDictItem(String itemId, String typeId, String itemCode, String itemName, String itemValue, String isFixed, String itemStatus, String englishName, String sort, String remark) {
        // 有主键更新，没有新增
        Updater.Update update = StringUtils.isBlank(itemId) ? updater.insert(CodFrameDictItemDo.TABLE_NAME).setId() : updater.update(CodFrameDictItemDo.TABLE_NAME).where("id", itemId);
        update.set("item_name", itemName)
                .set("type_id", typeId)
                .set("item_code", itemCode)
                .set("item_value", itemValue);
        update = StringUtils.isNotBlank(isFixed) ? update.set("isfixed", isFixed) : update.set("isfixed", 0);
        // 判断状态是否符合规则
        if (!verifyStatus(itemStatus)){
            logger.info("状态码错误!");
            return StatusCode.FAIL_CODE;
        }
        update.set("state", itemStatus)
                .set("english_name", englishName)
                .set("sort", sort)
                .set("remark", remark);

        // 全拼
        String allPin = CodCommonPinyin.convertLower(itemName);

        // 简拼
        String simplePing = CodCommonPinyin.lowercaseLetter(itemName);
        update.set("allpin", allPin);
        update.set("simplepin", simplePing);

        int i = update.update();

        if (i == 1){
            return StatusCode.SUCCESS_CODE;
        }

        return StatusCode.FAIL_CODE;
    }

    /**
     * 保存字典类型
     * @param typeCode    类型代码
     * @param typeName    类型名称
     * @param englishName 英文名称
     * @param typeStatus  类型状态
     * @param remark      备注
     * @return 是否保存成功
     */
    @Log(name = "保存字典类型")
    @SystemLog
    @Override
    public StatusCode saveDictType(String dictId, String typeCode, String typeName, String englishName, String typeStatus, String remark) {

        // 有主键更新，没有新增
        Updater.Update update = StringUtils.isBlank(dictId) ? updater.insert(CodFrameDictTypeDo.TABLE_NAME).setId() : updater.update(CodFrameDictTypeDo.TABLE_NAME).where("id", dictId);
        update.set("type_name", typeName)
                .set("type_code", typeCode)
                .set("english_name", englishName);
        // 判断状态是否符合规则
        if (!verifyStatus(typeStatus)){
            logger.info("状态码错误!");
            return StatusCode.FAIL_CODE;
        }
        update.set("state", typeStatus);
        update.set("remark", remark);
        String allPin = CodCommonPinyin.convertLower(typeName);
        update.set("allpin", allPin);
        int i = update.update();
        if (i == 1){
            return StatusCode.SUCCESS_CODE;
        }
        return StatusCode.FAIL_CODE;
    }

    /**
     * 根据类型 code 获取 item
     * @param code 代码
     * @return item List
     */
    @ParamNotNull(parameter = "code")
    @Log(name = "根据code获取字典数据")
    @Override
    public List<CodFrameDictItemBo> getItemByType(String code) {
        List<CodFrameDictItemDo> codFrameDictItemDo = null;
        try {
            codFrameDictItemDo = finder.from(CodFrameDictItemDo.TABLE_NAME + " item")
                    .join("join " + CodFrameDictTypeDo.TABLE_NAME + " type on item.type_id = type.id ")
                    .where("type.type_code", code).where("item.state", "1")
                    .select("item.id", "item.item_name", "item.item_code", "item.isFixed", "item.english_name", "item.allpin", "item.simplepin", "item.remark", "item.item_value")
                    .all(CodFrameDictItemDo.class);
        } catch (Exception e){
            logger.error("获取item, sql异常, {}", e.getMessage());
            return null;
        }

        if (codFrameDictItemDo == null){
            return new ArrayList<>();
        }

        List<CodFrameDictItemBo> codFrameDictItemBos = new ArrayList<>();
        codFrameDictItemDo.forEach(item -> {
            CodFrameDictItemBo codFrameDictItemBo = new CodFrameDictItemBo();
            codFrameDictItemBo.setId(item.getId());
            codFrameDictItemBo.setAllPin(item.getAllpin());
            codFrameDictItemBo.setCode(item.getItem_code());
            codFrameDictItemBo.setEnglishName(item.getEnglish_name());
            codFrameDictItemBo.setName(item.getItem_name());
            codFrameDictItemBo.setRemark(item.getRemark());
            codFrameDictItemBo.setSimplePin(item.getSimplepin());
            codFrameDictItemBo.setValue(item.getItem_value());
            codFrameDictItemBos.add(codFrameDictItemBo);
        });

        return codFrameDictItemBos;
    }

    /**
     * 根据code获取数据字典
     * typeCode:itemCode 形式获取类型下code
     * itemCode 形式获取数据字典
     * @param code itemCode/typeCode/typeCode:itemCode
     * @return 数据字典
     */
    @ParamNotNull(parameter = "code")
    @Override
    public CodFrameDictItemBo getItem(String code) {
        String[] codes = code.split(":");
        String itemCode;
        Finder.Query query = finder.from(CodFrameDictItemDo.TABLE_NAME + " item");
        if (codes.length == 2){
            String typeCode = codes[0];
            query.join("join " + CodFrameDictTypeDo.TABLE_NAME + " type on item.type_id = type.id ").where("type.type_code", typeCode);
            query.select("item.id", "item.item_name", "item.item_code", "item.isFixed", "item.english_name", "item.allpin", "item.simplepin", "item.remark", "item.item_value");
            itemCode = codes[1];
        } else {
            itemCode = code;
        }
        query.where("item.item_code", itemCode).where("item.state", "1");
        CodFrameDictItemDo dictItemDo = query.first(CodFrameDictItemDo.class);
        CodFrameDictItemBo bo = new CodFrameDictItemBo();
        if (dictItemDo == null){
            return bo;
        }
        bo.setId(dictItemDo.getId());
        bo.setValue(dictItemDo.getItem_value());
        bo.setName(dictItemDo.getItem_name());
        bo.setCode(dictItemDo.getItem_code());
        bo.setSimplePin(dictItemDo.getSimplepin());
        bo.setRemark(dictItemDo.getRemark());
        bo.setEnglishName(dictItemDo.getEnglish_name());
        bo.setAllPin(dictItemDo.getAllpin());
        return bo;
    }

    /**
     * 删除字典数据
     * @param id id
     * @return
     */
    @Log(name = "删除字典数据")
    @Override
    public StatusCode delDictItem(String id) {
        int i = updater.update(CodFrameDictItemDo.TABLE_NAME).set("state", "-1").where("id", id).not("type", "0").update();
        if (i == 1) {
            return StatusCode.SUCCESS_CODE;
        }
        return StatusCode.FAIL_CODE;
    }

    @Log(name = "删除字典类型")
    @Override
    public StatusCode delDictType(String id) {
        int i = updater.update(CodFrameDictTypeDo.TABLE_NAME).set("state", "-1").where("id", id).not("type", "0").update();
        int i1 = updater.update(CodFrameDictItemDo.TABLE_NAME).set("state", "-1").where("type_id", id).not("type", "0").update();
        if (i == 1) {
            return StatusCode.SUCCESS_CODE;
        }
        return StatusCode.FAIL_CODE;
    }

    /**
     * 加入状态正常条件
     */
    private void setEnable(Finder.Query query){
        query.where("state", "1");
    }

    /**
     * 判断状态码是否正确
     * @param status 状态码 0: 禁用; 1: 启用; -1: 刪除;
     * @return
     */
    private boolean verifyStatus(String status){
        return StringUtils.isNotBlank(status) && ("1".equals(status) || "0".equals(status));
    }
}
