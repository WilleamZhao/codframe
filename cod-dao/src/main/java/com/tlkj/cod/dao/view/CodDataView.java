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
 * Desc Cod视图接口
 *
 * @author sourcod
 * @version 1.0
 * @className CodDataView
 * @date 2019/1/7 4:41 PM
 */
public class CodDataView extends AbstractDataView {

    /**
     * 主表
     */
    private TableModel tableModel;

    /**
     * 连接
     */
    private JoinModel[] joinModels;

    /**
     * 条件
     */
    private String where;

    public TableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(TableModel tableModel) {
        this.tableModel = tableModel;
    }

    public JoinModel[] getJoinModels() {
        return joinModels;
    }

    public void setJoinModels(JoinModel[] joinModels) {
        this.joinModels = joinModels;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }
}
