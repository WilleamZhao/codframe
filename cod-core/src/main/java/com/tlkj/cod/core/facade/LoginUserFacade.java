/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.core.facade;

import java.util.List;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className LoginUserFacade
 * @date 2018/12/20 11:59 AM
 */
public interface LoginUserFacade {
    /**
     * 获取权限
     * @param id 用户id
     * @return 权限列表
     */
    List getPermission(String id);
}
