package com.tlkj.cod.gwgz.service.impl;

import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Pagination;
import com.tlkj.cod.dao.jdbc.Updater;
import com.tlkj.cod.gwgz.model.dto.GwgzCollectDto;
import com.tlkj.cod.gwgz.model.entity.GwgzCollectDo;
import com.tlkj.cod.gwgz.service.GwgzCollectService;
import com.tlkj.cod.log.annotation.Log;
import com.tlkj.cod.model.common.SystemResponse;
import com.tlkj.cod.model.enums.StatusCode;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className GwgzCollectServiceImpl
 * @date 2019/4/30 8:10 AM
 */
@Service
@Getter
public class GwgzCollectServiceImpl implements GwgzCollectService {

    @Autowired
    Finder finder;

    @Autowired
    Updater updater;

    @Log(name = "收藏列表")
    @Override
    public SystemResponse list(String openid, String page, String pageSize) {
        SystemResponse response = new SystemResponse();
        Finder.Query query = finder.from(GwgzCollectDo.TABLE_NAME);
        Pagination<GwgzCollectDo> pagination = query.paginate(GwgzCollectDo.class, page, pageSize);
        if (pagination.isNull()){
            return response.fail();
        }

        List<GwgzCollectDo> gwgzCollectDos = pagination.getData();
        if (gwgzCollectDos.isEmpty()){
            return response.fail(StatusCode.DATA_NULL_CODE);
        }
        List<GwgzCollectDto> gwgzCollectDtos = gwgzCollectDos.stream().map(gwgzCollectDo -> gwgzCollectDo.toDto(GwgzCollectDto.class)).collect(Collectors.toList());
        return response.success(gwgzCollectDtos);
    }

    @Log(name = "后台收藏列表")
    @Override
    public SystemResponse listAdmin(String openid, String page, String pageSize) {
        return null;
    }

    @Log(name = "获取收藏")
    @Override
    public SystemResponse get(String openid, String id) {
        SystemResponse<GwgzCollectDo> response = new SystemResponse<>();
        GwgzCollectDo gwgzCollectDo = finder.from(GwgzCollectDo.TABLE_NAME).where("openid", openid).where("id", id).first(GwgzCollectDo.class);
        if (gwgzCollectDo != null){
            return response.success(gwgzCollectDo);
        }
        return response.fail(StatusCode.DATA_NULL_CODE);
    }

    @Override
    public SystemResponse getAdmin() {
        return null;
    }

    @Override
    public SystemResponse save() {
        return null;
    }

    @Override
    public SystemResponse del(String openid, String id) {
        return null;
    }
}
