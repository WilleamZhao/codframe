/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.admin.model.entity;

import com.tlkj.cod.dao.model.CodDaoDo;
import lombok.Getter;
import lombok.Setter;

/**
 * Desc 服务管理
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminServerDo
 * @date 2019/12/3 8:32 PM
 */
@Getter
@Setter
public class CodAdminServerDo extends CodDaoDo {

    public static final String TABLE_NAME = "cod_admin_server";
    private static final long serialVersionUID = -972572404863034143L;

    /**
     * 主键
     */
    private String id;

    /**
     * 项目名
     */
    private String project_name;

    /**
     * 服务名
     */
    private String server_name;

    /**
     * 服务 ip/域名
     */
    private String server_ip;

    /**
     * 服务内网 ip/域名
     */
    private String server_intranet_ip;

    /**
     * 服务端口
     */
    private String server_port;

    /**
     * 服务版本
     */
    private String server_version;

    /**
     * 服务状态
     */
    private String server_state;

    /**
     * 服务配置
     */
    private String server_config;

    /**
     * license 类型
     */
    private String server_license_type;

    /**
     * license 值
     */
    private String server_license_value;

    /**
     * 标签
     */
    private String tag;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private String create_time;

    /**
     * 修改时间
     */
    private String update_time;

}
