/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.admin.action;

import com.tlkj.cod.admin.model.bo.CodAdminDictItemBo;
import com.tlkj.cod.admin.service.CodAdminDictService;
import com.tlkj.cod.admin.service.CodAdminSystemSetService;
import com.tlkj.cod.model.common.GeneralResponse;
import com.tlkj.cod.model.common.Response;
import com.tlkj.cod.model.system.core.SystemModel;
import com.tlkj.cod.model.system.core.SystemSetAllowDisable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Desc 缓存Action
 *
 * @author sourcod
 * @version 1.0
 * @className CacheAction
 * @date 2018/12/26 8:44 PM
 */
@RestController
@RequestMapping("system/cache")
public class CodAdminCacheAction extends GeneralResponse {

    @Autowired
    CodAdminDictService codAdminDictService;

    @Autowired
    CodAdminSystemSetService codAdminSystemSetService;

    /**
     * 刷新黑白名单
     * @return
     */
    @RequestMapping(value = "allowDisable", method = {RequestMethod.GET})
    public Response allowDisable(HttpServletRequest request){
        List<String> blackList = codAdminDictService.getItemByType("black-set").stream().map(CodAdminDictItemBo::getValue).collect(Collectors.toList());
        List<String> whiteList = codAdminDictService.getItemByType("white-set").stream().map(CodAdminDictItemBo::getValue).collect(Collectors.toList());
        SystemSetAllowDisable systemSetAllowDisable = new SystemSetAllowDisable();
        systemSetAllowDisable.setAllowDisable(codAdminSystemSetService.getSetValue("allow_disable"));
        systemSetAllowDisable.setBlackList(blackList);
        systemSetAllowDisable.setWhiteList(whiteList);
        SystemModel.getInstance().setAllowDisable(systemSetAllowDisable);
        return success();
    }
}
