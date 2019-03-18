/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.dao.view;

/**
 * Desc 连接/关联
 *
 * @author sourcod
 * @version 1.0
 * @className JoinModel
 * @date 2019/1/7 7:17 PM
 */
public class JoinModel {
    private String join = "left join";
    private String table = "";
    private String alias = "";
    private String[] on;

    public JoinModel() {

    }

    public JoinModel(String join, String table, String alias, String[] on) {
        this.join = join;
        this.table = table;
        this.alias = alias;
        this.on = on;
    }

    public String getJoin() {
        return join;
    }

    public void setJoin(String join) {
        this.join = join;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String[] getOn() {
        return on;
    }

    public void setOn(String[] on) {
        this.on = on;
    }
}
