package com.tlkj.cod.gwgz.service;

import com.tlkj.cod.model.common.SystemResponse;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className GwgzCollectService
 * @date 2019/4/29 6:42 PM
 */
public interface GwgzCollectService {

    SystemResponse list(String page, String pageSize);

    SystemResponse listAdmin(String openid);

    SystemResponse get();

    SystemResponse getAdmin();

    SystemResponse save();
}
