/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.model.system.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Desc 菜单列表Dto
 *
 * @author sourcod
 * @version 1.0
 * @className CodFrameMenuListDto
 * @date 2018/10/29 下午11:20
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CodFrameMenuListDto {

    /**
     * 主键
     */
    private String id;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单显示名称
     */
    private String menuTitle;

    /**
     * 菜单路径
     */
    private String jump;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 父菜单id
     */
    private String pId;

    /**
     * 菜单等级
     */
    private String level;

    /**
     * 菜单状态
     */
    private String status;

    /**
     * 上级菜单
     */
    private String prevMenuName;

    /**
     * 排序
     */
    private String sort;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public String getJump() {
        return jump;
    }

    public void setJump(String jump) {
        this.jump = jump;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getPrevMenuName() {
        return prevMenuName;
    }

    public void setPrevMenuName(String prevMenuName) {
        this.prevMenuName = prevMenuName;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
