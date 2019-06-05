/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.admin.facade;

import com.tlkj.cod.model.system.dto.CodFrameMenuDto;

import java.util.List;

/**
 * Desc 菜单facade
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminMenuFacade
 * @date 2018/12/26 2:10 PM
 */
public interface CodAdminMenuFacade {

    /**
     * 根据角色获取菜单
     * @param token token
     * @return 左侧菜单树
     */
    List<CodFrameMenuDto> getMenu(String token);
}
