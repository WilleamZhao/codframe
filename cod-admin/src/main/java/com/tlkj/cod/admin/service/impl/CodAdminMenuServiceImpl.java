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

import com.tlkj.cod.admin.model.dto.CodAdminMenuDto;
import com.tlkj.cod.admin.model.dto.CodAdminMenuListDto;
import com.tlkj.cod.admin.model.entity.CodAdminMenuDo;
import com.tlkj.cod.admin.service.CodAdminMenuService;
import com.tlkj.cod.common.CodCommonJson;
import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Pagination;
import com.tlkj.cod.dao.jdbc.Updater;
import com.tlkj.cod.log.annotation.CodLog;
import com.tlkj.cod.log.service.CodLogService;
import com.tlkj.cod.model.enums.StatusCode;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc 菜单管理Service
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminMenuServiceImpl
 * @date 2018/10/29 下午2:28
 */
@Service
public class CodAdminMenuServiceImpl implements CodAdminMenuService {

    @Autowired
    Finder finder;

    @Autowired
    Updater updater;

    @Autowired
    CodLogService codLogService;

    /**
     * 获取左侧菜单接口
     * @return 菜单json
     */
    @CodLog(name = "获取左侧菜单")
    @Override
    public List<CodAdminMenuDto> getMenu(String menuIds) {
        Finder.Query query = finder.from(CodAdminMenuDo.TABLE_NAME).where("status", "1").orderBy("sort");

        /*
         * 菜单id不为空用菜单id
         * 为空查全部
         */
        if (StringUtils.isNotBlank(menuIds)){
            query.in("id", menuIds.split(","));
        }
        List<CodAdminMenuDo> menuDoList = query.all(CodAdminMenuDo.class);
        // 1级list
        List<CodAdminMenuDto> menuDtoList = null;

        menuDtoList =  this.getMenuLevel(1, menuDoList, "");
        codLogService.info("获取左侧菜单成功, 树={}", CodCommonJson.dump(menuDtoList));
        return menuDtoList;
    }

    /**
     * 获取菜单列表接口
     * @param page 当前页
     * @param limit 每页显示多少条
     * @param menuName 菜单名称
     * @param menuTitle 菜单显示名称
     * @param level 菜单级别
     * @param status 菜单状态
     * @return 菜单列表
     */
    @CodLog(name = "获取菜单列表")
    @Override
    public Page<List<CodAdminMenuListDto>> listMenu(String page, String limit, String menuName, String menuTitle, String level, String status) {
        Finder.Query query = finder.from("cod_sys_menu").orderBy("").orderBy("sort");
        if (StringUtils.isNotBlank(menuName)){
            query.like("menu_name", "%" + menuName + "%");
        }

        if (StringUtils.isNotBlank(menuTitle)){
            query.like("menu_title", "%" + menuTitle + "%");
        }

        if (StringUtils.isNotBlank(level)){
            query.where("level", level);
        }

        if (StringUtils.isNotBlank(status)){
            query.where("status", status);
        }

        Pagination<CodAdminMenuDo> pagination = null;
        pagination = query.paginate(CodAdminMenuDo.class, page, limit);

        List<CodAdminMenuDo> menuDoList = null;
        if (pagination == null){
            return null;
        }

        menuDoList = pagination.getData();

        if (menuDoList == null){
            return null;
        }
        List<CodAdminMenuListDto> menuListDtos = new ArrayList<>();
        // 查询所有遍历用
        List<CodAdminMenuDo> list = finder.from(CodAdminMenuDo.TABLE_NAME).all(CodAdminMenuDo.class);
        for (CodAdminMenuDo menuDo : menuDoList){
            CodAdminMenuListDto menuListDto = new CodAdminMenuListDto();
            menuListDto.setId(menuDo.getId());
            menuListDto.setIcon(menuDo.getIcon());
            menuListDto.setJump(menuDo.getJump());
            String prevMenu = menuDo.getP_id();
            if (StringUtils.isNotBlank(prevMenu)){
                for (CodAdminMenuDo menuDo1 : list){
                    if (prevMenu.equals(menuDo1.getId())){
                        prevMenu = menuDo1.getMenu_title();
                        break;
                    }
                }
            }

            // 设置一级菜单为 -1
            menuListDto.setpId("1".equals(menuDo.getLevel()) ? "-1" : menuDo.getP_id());
            menuListDto.setPrevMenuName(prevMenu);
            menuListDto.setLevel(menuDo.getLevel());
            menuListDto.setMenuName(menuDo.getMenu_name());
            menuListDto.setMenuTitle(menuDo.getMenu_title());
            menuListDto.setCreateTime(menuDo.getCreate_time());
            menuListDto.setUpdateTime(menuDo.getUpdate_time());
            menuListDto.setStatus(menuDo.getStatus());
            menuListDto.setSort(menuDo.getSort());

            menuListDtos.add(menuListDto);
        }
        Page<List<CodAdminMenuListDto>> tempPage = new Page<>(pagination);
        tempPage.setData(menuListDtos);
        return tempPage;
    }

    @Override
    public List<CodAdminMenuListDto> listMenu(String menuIds) {
        Finder.Query query = finder.from(CodAdminMenuDo.TABLE_NAME);
        if (StringUtils.isNotBlank(menuIds)){
            query.in("id", menuIds.split(","));
        }
        List<CodAdminMenuDo> codAdminMenuDos = query.all(CodAdminMenuDo.class);
        List<CodAdminMenuListDto> codAdminMenuListDtos = new ArrayList<>();
        codAdminMenuDos.forEach(item -> {
            CodAdminMenuListDto dto = new CodAdminMenuListDto();
            dto.setId(item.getId());
            dto.setMenuName(item.getMenu_name());
            dto.setMenuTitle(item.getMenu_title());
            codAdminMenuListDtos.add(dto);
        });
        return codAdminMenuListDtos;
    }

    /**
     * 保存菜单接口
     * @param menuId 主键
     * @param menuName 菜单名称
     * @param menuTitle 菜单显示名称
     * @param jump 菜单路径
     * @param pId 菜单父id
     * @param level 菜单级别
     * @param icon 菜单图标
     * @param status 菜单状态 0: 禁用; 1: 启用
     * @return 保存是否成功
     */
    @CodLog(name = "保存菜单")
    @Override
    public StatusCode saveMenu(String menuId, String menuName, String menuTitle, String jump, String pId, String level, String icon, String status, String sort) {
        // 一级菜单没有pId
        if ("1".equals(level) && StringUtils.isNotBlank(pId)){
            return StatusCode.FAIL_CODE;
        }

        // 有主键更新，没有新增
        Updater.Update update = StringUtils.isBlank(menuId) ? updater.insert(CodAdminMenuDo.TABLE_NAME) : updater.update(CodAdminMenuDo.TABLE_NAME);

        update.set("menu_name", menuName)
                .set("menu_title", menuTitle)
                .set("jump", jump)
                .set("p_id", pId);

        if (StringUtils.isNotBlank(level)){
            update.set("level", level);
        }

        if (StringUtils.isNotBlank(sort)){
            update.set("sort", sort);
        }

        if (StringUtils.isNotBlank(status)){
            update.set("status", status);
        }

        if (StringUtils.isNotBlank(icon)){
            update.set("icon", icon);
        }

        update = StringUtils.isBlank(menuId) ? update.setId() : update.where("id", menuId);

        int i = update.update();
        if (i == 1){
            return StatusCode.SUCCESS_CODE;
        }
        return StatusCode.FAIL_CODE;
    }

    /**
     * 验证菜单名称是否重复
     * @param menuIds 菜单Id (1,2,3)
     * @return 是否删除成功
     */
    @CodLog(name = "删除菜单")
    @Override
    public StatusCode delMenu(String menuIds) {
        String[] menus = menuIds.split(",");
        int i = 0;
        try {
            for (String menuid : menus){
                String menuIdTrim = menuid.trim();
                if (StringUtils.isNotBlank(menuIdTrim)){
                    // 删除本菜单
                    i = i + updater.delete(CodAdminMenuDo.TABLE_NAME).where("id", menuIdTrim).update();
                    // 删除子菜单
                    int childNum = updater.delete(CodAdminMenuDo.TABLE_NAME).where("p_id", menuIdTrim).update();
                    codLogService.info("删除菜单成功, 本菜单数量={}, 子菜单数量={}", menuIdTrim, childNum);
                }
            }
        } catch (Exception e){
            codLogService.error("删除菜单错误, {}", e);
            return StatusCode.FAIL_CODE;
        }
        if (i > 0){
            return StatusCode.SUCCESS_CODE;
        }
        return StatusCode.FAIL_CODE;
    }

    /**
     * 验证菜单名称是否重复
     * @param menuName 菜单名称
     * @return
     */
    @CodLog(name = "验证菜单名称是否重复")
    @Override
    public StatusCode verifyMenuName(String menuName) {
        int i = finder.from(CodAdminMenuDo.TABLE_NAME).where("menu_name", menuName).all().size();
        if (i == 0){
            return StatusCode.FAIL_CODE;
        }
        return StatusCode.SUCCESS_CODE;
    }

    /**
     * 根据菜单Id, 获取菜单信息
     * @param menuId 菜单Id
     * @return 菜单信息
     */
    @CodLog(name = "根据菜单Id获取菜单信息")
    @Override
    public CodAdminMenuListDto getOneMenu(String menuId) {
        CodAdminMenuListDto codAdminMenuListDto = finder.from(CodAdminMenuDo.TABLE_NAME).where("id", menuId).first(CodAdminMenuListDto.class);
        return codAdminMenuListDto;
    }

    /**
     * 获取菜单级别
     * @return
     */
    private List<CodAdminMenuDto> getMenuLevel(int level, List<CodAdminMenuDo> menuDoList, String id){
        List<CodAdminMenuDto> tempMenuDtoList = new ArrayList<>();

        for (CodAdminMenuDo menuDo : menuDoList) {
            if (StringUtils.isNotBlank(menuDo.getLevel())){
                if (level == Integer.parseInt(menuDo.getLevel())) {
                    CodAdminMenuDto menuDto = new CodAdminMenuDto();
                    if (level == 1){
                        menuDto = setMenuDto(level, menuDoList, menuDto, menuDo);
                    }
                    if (StringUtils.isNotBlank(id) && menuDo.getP_id().equals(id)){
                        menuDto = setMenuDto(level, menuDoList, menuDto, menuDo);
                    }
                    if (StringUtils.isNotBlank(menuDto.getId())){
                        tempMenuDtoList.add(menuDto);
                    }
                }
            }
        }

        return tempMenuDtoList;
    }

    private CodAdminMenuDto setMenuDto(int level, List<CodAdminMenuDo> menuDoList, CodAdminMenuDto menuDto, CodAdminMenuDo menuDo){
        menuDto.setId(menuDo.getId());
        menuDto.setIcon(menuDo.getIcon());
        menuDto.setJump(menuDo.getJump());
        menuDto.setMenuName(menuDo.getMenu_name());
        menuDto.setMenuTitle(menuDo.getMenu_title());
        List<CodAdminMenuDto> menuDtos = this.getMenuLevel(level + 1, menuDoList, menuDto.getId());
        menuDto.setMenuDtoList(menuDtos);
        return menuDto;
    }
}
