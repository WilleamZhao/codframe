/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.admin.model.dto;

import com.tlkj.cod.common.CodCommonModelConvert;
import lombok.Getter;
import lombok.Setter;

/**
 * Desc 服务管理 Dto
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminServerDto
 * @date 2019/12/3 8:37 PM
 */
@Getter
@Setter
public class CodAdminServerDto extends CodCommonModelConvert {

    /**
     * 主键
     */
    private String id;

    /**
     * 项目名
     */
    private String projectName;

    /**
     * 服务名
     */
    private String serverName;

    /**
     * 服务 ip/域名
     */
    private String serverIp;

    /**
     * 服务内网 ip/域名
     */
    private String serverIntranetIp;

    /**
     * 服务端口
     */
    private String serverPort;

    /**
     * 服务版本
     */
    private String serverVersion;

    /**
     * 服务状态
     */
    private String serverState;

    /**
     * 服务配置
     */
    private String serverConfig;

    /**
     * license 类型
     */
    private String serverLicenseType;

    /**
     * license 值
     */
    private String serverLicenseValue;

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
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;
}
