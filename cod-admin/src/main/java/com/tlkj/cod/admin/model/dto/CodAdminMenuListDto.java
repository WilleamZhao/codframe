/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.admin.model.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Desc 菜单列表Dto
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminMenuListDto
 * @date 2018/10/29 下午11:20
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CodAdminMenuListDto {

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

    @JsonProperty("pId")
    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }
}
