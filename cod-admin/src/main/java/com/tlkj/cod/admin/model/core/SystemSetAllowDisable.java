/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.admin.model.core;

import java.util.List;

/**
 * Desc 黑白名单设置
 *
 * @author sourcod
 * @version 1.0
 * @className SystemSetAllowDisable
 * @date 2018/12/26 8:41 PM
 */
public class SystemSetAllowDisable {

    private String allowDisable;

    private List<String> blackList;

    private List<String> whiteList;

    public String getAllowDisable() {
        return allowDisable;
    }

    public void setAllowDisable(String allowDisable) {
        this.allowDisable = allowDisable;
    }

    public List<String> getBlackList() {
        return blackList;
    }

    public void setBlackList(List<String> blackList) {
        this.blackList = blackList;
    }

    public List<String> getWhiteList() {
        return whiteList;
    }

    public void setWhiteList(List<String> whiteList) {
        this.whiteList = whiteList;
    }
}
