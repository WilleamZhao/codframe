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
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Desc 左侧菜单Dto
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminMenuDto
 * @date 2018/10/29 下午2:59
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties({"id"})
public class CodAdminMenuDto {

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
    private List<CodAdminMenuDto> menuDtoList;

}
