/*
 * Copyright (c) 2019.
 * Beijing sky blue technology co., LTD.
 * All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://codframe.com
 */

package com.tlkj.cod.admin.model.entity;

/**
 * Desc 用户角色关联表Do
 *
 * @author sourcod
 * @version 1.0
 * @className CodFrameUserRoleDo
 * @date 2018/10/29 上午10:52
 */
public class CodFrameUserRoleDo {

    public static String TABLE_NAME = "cod_sys_user_role";

    /**
     * 主键
     */
    private String id;

    /**
     * 用户id
     */
    private String user_id;

    /**
     * 角色id
     */
    private String role_id;

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

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }
}
