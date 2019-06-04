/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.admin.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Desc 左侧菜单Dto
 *
 * @author sourcod
 * @version 1.0
 * @className CodFrameMenuDto
 * @date 2018/10/29 下午2:59
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties({"id"})
public class CodFrameMenuDto {

    /**
     * 主键
     */
    @JsonProperty("id")
    private String id;

    /**
     * 菜单名称
     */
    @JsonProperty("name")
    private String menuName;

    /**
     * 菜单显示名称
     */
    @JsonProperty("title")
    private String menuTitle;

    /**
     * 跳转路径
     */
    @JsonProperty("jump")
    private String jump;

    /**
     * 菜单图标
     */
    @JsonProperty("icon")
    private String icon;

    /**
     * 下级菜单
     */
    @JsonProperty("list")
    private List<CodFrameMenuDto> menuDtoList;


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

    public List<CodFrameMenuDto> getMenuDtoList() {
        return menuDtoList;
    }

    public void setMenuDtoList(List<CodFrameMenuDto> menuDtoList) {
        this.menuDtoList = menuDtoList;
    }
}
