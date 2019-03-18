/*
 * Copyright (c) 2018-2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.model.system.entity;

/**
 * Desc 用户部门表
 *
 * @author sourcod
 * @version 1.0
 * @className CodFrameUserDeptDo
 * @date 2019/1/7 11:26 PM
 */
public class CodFrameUserDeptDo {

    public static final String TABLE_NAME = "cod_sys_user_dept";

    /**
     * 主键
     */
    private String id;

    /**
     * 用户id
     */
    private String user_id;

    /**
     * 部门id
     */
    private String dept_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }
}
