/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.action.system;

import com.tlkj.cod.model.common.Response;
import com.tlkj.cod.model.system.bo.CodFrameDictItemBo;
import com.tlkj.cod.model.system.core.SystemModel;
import com.tlkj.cod.model.system.core.SystemSetAllowDisable;
import com.tlkj.cod.service.system.DictService;
import com.tlkj.cod.service.system.SystemSetService;
import com.tlkj.cod.model.common.GeneralResponse;
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
public class CacheAction extends GeneralResponse {

    @Autowired
    DictService dictService;

    @Autowired
    SystemSetService systemSetService;

    /**
     * 刷新黑白名单
     * @return
     */
    @RequestMapping(value = "allowDisable", method = {RequestMethod.GET})
    public Response allowDisable(HttpServletRequest request){
        List<String> blackList = dictService.getItemByType("black-set").stream().map(CodFrameDictItemBo::getValue).collect(Collectors.toList());
        List<String> whiteList = dictService.getItemByType("white-set").stream().map(CodFrameDictItemBo::getValue).collect(Collectors.toList());
        SystemSetAllowDisable systemSetAllowDisable = new SystemSetAllowDisable();
        systemSetAllowDisable.setAllowDisable(systemSetService.getSetValue("allow_disable"));
        systemSetAllowDisable.setBlackList(blackList);
        systemSetAllowDisable.setWhiteList(whiteList);
        SystemModel.getInstance().setAllowDisable(systemSetAllowDisable);
        return success();
    }
}
