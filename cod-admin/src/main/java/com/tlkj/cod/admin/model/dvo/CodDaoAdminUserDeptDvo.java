/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.admin.model.dvo;

import com.tlkj.cod.dao.annotation.CodDaoViewColumn;
import com.tlkj.cod.dao.annotation.CodDaoViewJoin;
import com.tlkj.cod.dao.annotation.CodDaoViewTable;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.view.CodDaoDataView;
import com.tlkj.cod.model.system.entity.CodFrameDeptDo;
import com.tlkj.cod.model.system.entity.CodFrameUserDeptDo;
import com.tlkj.cod.model.system.entity.CodFrameUserDo;
import lombok.Getter;
import lombok.Setter;

/**
 * Desc 部门公司Dvo
 *
 * @author sourcod
 * @version 1.0
 * @className CodDaoAdminUserDeptDvo
 * @date 2019/1/4 7:21 PM
 */
@Getter
@Setter
@CodDaoViewTable(name = "cod_sys_user_dept")
public class CodDaoAdminUserDeptDvo extends CodDaoDataView {

    /**
     * 部门表
     */
    private static final String DEPT_TABLE = CodFrameDeptDo.TABLE_NAME;

    /**
     * 用户表
     */
    private static final String USER_TABLE = CodFrameUserDo.TABLE_NAME;

    /**
     * 用户部门表
     */
    private static final String USER_DEPT_TABLE = CodFrameUserDeptDo.TABLE_NAME;

    @CodDaoViewJoin(join = "left join", table = DEPT_TABLE, on = {DEPT_TABLE + ".id", USER_DEPT_TABLE + ".dept_id"})
    private String dept;

    @CodDaoViewJoin(join = "left join", table = USER_TABLE, on = {USER_TABLE + ".id", USER_DEPT_TABLE + ".user_id"})
    private String user;

    @CodDaoViewColumn(cName = "id", tName = USER_DEPT_TABLE, aliasName = "id")
    private String id;

    @CodDaoViewColumn(cName = "id", tName = DEPT_TABLE)
    private String deptId;

    @CodDaoViewColumn(cName = "id", tName = DEPT_TABLE)
    private String userId;

    @CodDaoViewColumn(cName = "dept_name", tName = DEPT_TABLE)
    private String deptName;

    @CodDaoViewColumn(cName = "user_name", tName = USER_TABLE)
    private String userName;

    public static void main(String[] args) {
        CodDaoAdminUserDeptDvo dvo = new CodDaoAdminUserDeptDvo();
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
        query.dev(true).createGenerator();
    }

}
