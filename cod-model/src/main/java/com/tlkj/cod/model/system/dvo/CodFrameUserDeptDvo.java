/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.model.system.dvo;

import com.tlkj.cod.dao.annotation.Column;
import com.tlkj.cod.dao.annotation.Join;
import com.tlkj.cod.dao.annotation.Table;
import com.tlkj.cod.dao.annotation.Where;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.view.CodDataView;
import com.tlkj.cod.model.system.entity.CodFrameDeptDo;
import com.tlkj.cod.model.system.entity.CodFrameUserDeptDo;
import com.tlkj.cod.model.system.entity.CodFrameUserDo;

/**
 * Desc 部门公司Dvo
 *
 * @author sourcod
 * @version 1.0
 * @className CodFrameUserDeptDvo
 * @date 2019/1/4 7:21 PM
 */
@Table(name = "cod_sys_user_dept")
public class CodFrameUserDeptDvo extends CodDataView {

    /**
     * 部门表
     */
    public static final String DEPT_TABLE = CodFrameDeptDo.TABLE_NAME;

    /**
     * 用户表
     */
    public static final String USER_TABLE = CodFrameUserDo.TABLE_NAME;

    /**
     * 用户部门表
     */
    private static final String USER_DEPT_TABLE = CodFrameUserDeptDo.TABLE_NAME;

    /**
     * 视图名称
     */
    public static final String VIEW_NAME = "dept_company";

    @Join(join = "left join", table = DEPT_TABLE, on = {DEPT_TABLE + ".id", USER_DEPT_TABLE + ".dept_id"})
    private void dept(){

    }

    @Join(join = "left join", table = USER_TABLE, on = {USER_TABLE + ".id", USER_DEPT_TABLE + ".user_id"})
    private void user(){

    }

    @Where(value = {"a = b"})
    private void where(){

    }

    @Column(cName = "id", tName = USER_DEPT_TABLE, aliasName = "id")
    private String id;

    @Column(cName = "id", tName = DEPT_TABLE)
    private String deptId;

    @Column(cName = "id", tName = USER_TABLE)
    private String userId;

    @Column(cName = "dept_name", tName = DEPT_TABLE)
    private String deptName;

    @Column(cName = "user_name", tName = USER_TABLE)
    private String userName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static void main(String[] args) {
        CodFrameUserDeptDvo dvo = new CodFrameUserDeptDvo();
        /*TableModel tableModel = new TableModel();
        tableModel.setName("cod_sys_dept_company");
        tableModel.setAlias("deptCompany");
        dvo.setTableModel(tableModel);

        JoinModel joinModel = new JoinModel();
        joinModel.setTable("cod_sys_dept");
        joinModel.setJoin("left joinModel");
        joinModel.setAlias("dept");
        joinModel.setOn(new String[]{"a", "b"});
        dvo.setJoinModels(new JoinModel[]{joinModel});*/
        Finder finder = new Finder();
        Finder.Query query = finder.fromView(dvo);

    }

}
