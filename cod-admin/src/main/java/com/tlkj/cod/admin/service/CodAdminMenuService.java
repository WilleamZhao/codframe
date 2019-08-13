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

import com.tlkj.cod.admin.model.dto.CodAdminMenuDto;
import com.tlkj.cod.admin.model.dto.CodAdminMenuListDto;
import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.model.enums.StatusCode;

import java.util.List;

/**
 * Desc 菜单管理Service
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminMenuService
 * @date 2018/10/29 下午2:27
 */
public interface CodAdminMenuService {

    /**
     * 获取左侧菜单接口
     * @param menuIds 菜单ids
     * @return 菜单json
     */
    List<CodAdminMenuDto> getMenu(String menuIds);

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
    Page<List<CodAdminMenuListDto>> listMenu(String page, String limit, String menuName, String menuTitle, String level, String status);


    /**
     * 根据id获取菜单
     * @param menuIds 菜单ids
     * @return 菜单列表
     */
    List<CodAdminMenuListDto> listMenu(String menuIds);



    /**
     * 保存菜单
     * @param id 主键
     * @param menuName 菜单名称
     * @param menuTitle 菜单显示名称
     * @param jump 菜单路径
     * @param pId 菜单父id
     * @param level 菜单级别
     * @param icon 菜单图标
     * @param status 菜单状态 0: 禁用; 1: 启用
     * @return 保存成功
     */
    StatusCode saveMenu(String id, String menuName, String menuTitle, String jump, String pId, String level, String icon, String status, String sort);

    /**
     * 验证菜单名称是否重复
     * @param menuName 菜单名称
     * @return
     */
    StatusCode verifyMenuName(String menuName);

    /**
     * 根据菜单Id查询菜单信息
     * @param menuId 菜单Id
     * @return
     */
    CodAdminMenuListDto getOneMenu(String menuId);

    /**
     * 删除菜单
     * @param menuIds 菜单id
     * @return 是否删除成功
     */
    StatusCode delMenu(String menuIds);
}
