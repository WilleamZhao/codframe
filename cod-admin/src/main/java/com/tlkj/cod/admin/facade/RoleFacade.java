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

import java.util.List;

/**
 * Desc 角色Facade
 *
 * @author sourcod
 * @version 1.0
 * @className RoleFacade
 * @date 2018/12/14 2:56 PM
 */
public interface RoleFacade {

    /**
     * 根据角色id获取权限和全部权限
     * @param roleIds 角色id
     */
    List getPermission(String roleIds);

    /**
     * 维护菜单
     * @param roleIds 角色id
     * @return
     */
    List editMenu(String roleIds);
}
