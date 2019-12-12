/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.core.license;

/**
 * Desc license 授权 service
 *
 * @author sourcod
 * @version 1.0
 * @className CodLicenseService
 * @date 2019/10/21 12:46 PM
 */
public interface CodLicenseService {

    /**
     * 默认在线 license
     * @return 默认支持
     */
    default String getSupportType(){
        return "online";
    }

    boolean verify();


}
