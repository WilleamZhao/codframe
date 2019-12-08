/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.admin.service;

import com.tlkj.cod.admin.model.bo.CodAdminServerLicenseBo;
import com.tlkj.cod.admin.model.dto.CodAdminServerDto;
import com.tlkj.cod.dao.bean.Page;

import java.util.List;

/**
 * Desc 服务管理 service
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminServerService
 * @date 2019/12/3 8:16 PM
 */
public interface CodAdminServerService {

    /**
     * 获取服务列表
     * @param projectName 项目名称
     * @return
     */
    Page<List<CodAdminServerDto>> list(String projectName, String page, String pageSize);

    /**
     * 获取服务详细信息
     * @param projectName 项目名
     * @param serverName  服务名
     * @param serverId    服务 id
     * @return
     */
    CodAdminServerDto get(String projectName, String serverName, String serverId);

    /**
     * 删除服务
     * @param serverId
     * @return
     */
    boolean delete(String serverId);

    /**
     * 设置 license
     * @param serverId 服务 id
     * @param type     license 类型
     *                 1: 禁用/启用
     *                 2: 时间
     * @param value    license值
     * @return 是否设置成功
     */
    boolean setLicense(String serverId, String type, String value);

    /**
     * 获取 license
     * @param projectName 项目名
     * @param serverId    服务id
     * @return
     */
    CodAdminServerLicenseBo getLicense(String projectName, String serverId);

    /**
     * 远程停止服务
     * @param serverId
     * @return
     */
    boolean stop(String serverId);

    /**
     * 远程开启服务
     * @param serverId
     * @return
     */
    boolean start(String serverId);

    /**
     * 远程重启服务
     * @param serverId
     * @return
     */
    boolean restart(String serverId);

    /**
     * 检查服务
     * @param serverId 服务id
     * @return
     */
    boolean check(String serverId);

    /**
     * 自动检查服务
     */
    void autoCheck();

    /**
     * 注册服务
     * @param projectName      项目名
     * @param serverName       服务名
     * @param serverIp         服务 ip
     * @param serverIntranetIp 内网服务 ip
     * @param serverPort       服务端口
     * @param serverVersion    服务版本
     * @param serverConfig     服务配置
     * @param serverTag        服务标签
     * @param remark           备注
     * @return
     */
    boolean register(String projectName, String serverName, String serverIp, String serverIntranetIp,
                     String serverPort, String serverVersion, String serverConfig, String serverTag, String remark);
}
