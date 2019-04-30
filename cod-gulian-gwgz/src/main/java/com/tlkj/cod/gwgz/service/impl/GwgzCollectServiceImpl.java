package com.tlkj.cod.gwgz.service.impl;

import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Pagination;
import com.tlkj.cod.dao.jdbc.Updater;
import com.tlkj.cod.gwgz.model.entity.GwgzCollectDo;
import com.tlkj.cod.gwgz.service.GwgzCollectService;
import com.tlkj.cod.model.common.SystemResponse;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public SystemResponse list(String page, String pageSize) {
        SystemResponse response = new SystemResponse();
        Finder.Query query = finder.from(GwgzCollectDo.TABLE_NAME);
        Pagination pagination = query.paginate(GwgzCollectDo.class, page, pageSize);
        if (pagination.isNull()){
            return response.fail();
        }
        List<GwgzCollectDo> gwgzCollectDos = query.all(GwgzCollectDo.class);

        return null;
    }

    @Override
    public SystemResponse listAdmin(String openid) {
        return null;
    }

    @Override
    public SystemResponse get() {
        return null;
    }

    @Override
    public SystemResponse getAdmin() {
        return null;
    }

    @Override
    public SystemResponse save() {
        return null;
    }
}
