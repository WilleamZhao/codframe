/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.sourcod.com
 */

package com.tlkj.cod.dao.view;

/**
 * Desc 表
 *
 * @author sourcod
 * @version 1.0
 * @className TableModel
 * @date 2019/1/7 7:16 PM
 */
public class TableModel {

    private String name;
    private String alias;

    public TableModel() {

    }

    public TableModel(String name, String alias) {
        this.name = name;
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
