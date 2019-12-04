/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.admin.service.impl;

import com.tlkj.cod.admin.model.bo.CodAdminServerLicenseBo;
import com.tlkj.cod.admin.model.dto.CodAdminServerDto;
import com.tlkj.cod.admin.model.entity.CodAdminServerDo;
import com.tlkj.cod.admin.service.CodAdminServerService;
import com.tlkj.cod.common.CodCommonHttpClient;
import com.tlkj.cod.common.CodCommonJson;
import com.tlkj.cod.dao.bean.Page;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Pagination;
import com.tlkj.cod.dao.jdbc.Updater;
import com.tlkj.cod.model.common.Response;
import com.tlkj.cod.model.enums.StatusCode;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Desc 服务管理 service
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminServerServiceImpl
 * @date 2019/12/3 8:39 PM
 */
@Service
public class CodAdminServerServiceImpl implements CodAdminServerService {

    private Finder finder;
    private Updater updater;

    @Autowired
    public CodAdminServerServiceImpl(Finder finder, Updater updater){
        this.finder = finder;
        this.updater = updater;
    }

    @Override
    public Page<List<CodAdminServerDto>> list(String projectName, String page, String pageSize) {
        Finder.Query query = finder.from(CodAdminServerDo.TABLE_NAME);
        if (StringUtils.isNotBlank(projectName)) {
            query.where("project_name", projectName);
        }
        Pagination<CodAdminServerDo> pagination = query.paginate(CodAdminServerDo.class, page, page);
        if (pagination.isNull()){
            return new Page<>();
        }

        List<CodAdminServerDo> serverDos = pagination.getData();
        List<CodAdminServerDto> serverDtos = serverDos.stream().map(codAdminServerDo -> codAdminServerDo.toDto(CodAdminServerDto.class)).collect(Collectors.toList());
        return new Page<>(serverDtos, pagination);
    }

    /**
     * 获取服务详情
     * @param projectName 项目名
     * @param serverName  服务名
     * @param serverId    服务 id
     * @return
     */
    @Override
    public CodAdminServerDto get(String projectName, String serverName, String serverId) {
        Finder.Query query = finder.from(CodAdminServerDo.TABLE_NAME);
        if (StringUtils.isNotBlank(serverName)){
            query.where("server_name", serverName);
        }
        if (StringUtils.isNotBlank(serverId)){
            query.where("server_id", serverId);

        }
        if (StringUtils.isNotBlank(projectName)){
            query.where("project_name", projectName);
        }

        CodAdminServerDo codAdminServerDo = query.first(CodAdminServerDo.class);
        if (codAdminServerDo == null){
            return null;
        }
        return codAdminServerDo.toDto(CodAdminServerDto.class);
    }

    @Override
    public boolean delete(String serverId) {
        updater.delete("server_id", serverId);
        return false;
    }

    @Override
    public boolean setLicense(String serverId, String type, String value) {
        return false;
    }


    @Override
    public CodAdminServerLicenseBo getLicense(String projectName, String serverId) {
        Finder.Query query = finder.from(CodAdminServerDo.TABLE_NAME);
        if (StringUtils.isNotBlank(projectName)) {
            query.where("project_name", projectName);
        }

        if (StringUtils.isNotBlank(serverId)) {
            query.where("id", serverId);
        }

        CodAdminServerDo codAdminServerDo = query.first(CodAdminServerDo.class);
        if (codAdminServerDo == null){
            return null;
        }
        CodAdminServerLicenseBo bo = new CodAdminServerLicenseBo();
        bo.setType(codAdminServerDo.getServer_license_type());
        bo.setValue(codAdminServerDo.getServer_license_value());
        return bo;
    }

    @Override
    public boolean stop(String projectId) {
        return false;
    }

    @Override
    public boolean start(String projectId) {
        return false;
    }

    @Override
    public boolean restart(String projectId) {
        return false;
    }

    /**
     * 检查服务状态
     * TODO 添加服务状态枚举
     * 1. 获取服务信息
     * 2. 请求服务
     * 3. 检查返回信息
     * @param serverId 服务id
     * @return
     */
    @Override
    public boolean check(String serverId) {
        CodAdminServerDto codAdminServerDto = get("", "", serverId);
        if (codAdminServerDto == null){
            return false;
        }
        String projectName = codAdminServerDto.getProjectName();
        String ip = codAdminServerDto.getServerIp();
        String port = codAdminServerDto.getServerPort();
        String url = "http://" + ip + ":" + port + "/" + projectName + "/admin/api/health/check";
        try {
            HttpResponse httpResponse = CodCommonHttpClient.httpGet(url);
            HttpEntity httpEntity = httpResponse.getEntity();
            String result = EntityUtils.toString(httpEntity);
            if (StringUtils.isNotBlank(result)){
                Response response = CodCommonJson.load(result, Response.class);
                // TODO 添加状态码
                if (StatusCode.SUCCESS_CODE.getStatusCode().equals(response.getCode())){
                    return true;
                }
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void autoCheck() {
        Page<List<CodAdminServerDto>> listPage = list("", "1", "999");
        List<CodAdminServerDto> codAdminServerDtoList = listPage.getData();

    }


    /**
     * 服务注册
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
    @Override
    public boolean register(String projectName, String serverName, String serverIp, String serverIntranetIp, String serverPort, String serverVersion, String serverConfig, String serverTag, String remark) {
        Updater.Update update = updater.insert(CodAdminServerDo.TABLE_NAME).setId();
        update.set("project_name", projectName);
        update.set("server_name", serverName);
        update.set("server_ip", serverIp);
        update.set("server_intranet_ip", serverIntranetIp);
        update.set("server_port", serverPort);
        update.set("server_version", serverVersion);
        update.set("server_config", serverConfig);
        update.set("tag", serverTag);
        update.set("remark", remark);
        update.dev(true);
        try {
            int i = update.update();
            if (i == 1){
                return true;
            }
        } catch (Exception e){
            return false;
        }
        return false;
    }
}
