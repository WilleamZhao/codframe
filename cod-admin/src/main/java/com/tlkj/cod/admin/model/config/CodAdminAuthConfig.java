/*
 * Copyright (c) 2018-2019.
 * sourcod All rights reserved
 *
 * author: sourcod
 * github: https://github.com/WilleamZhao
 * site：http://blog.sourcod.com
 */

package com.tlkj.cod.admin.model.config;

        import lombok.Getter;
        import lombok.Setter;
        import org.springframework.beans.factory.annotation.Value;
        import org.springframework.stereotype.Component;

/**
 * Desc 权限配置
 *
 * @author sourcod
 * @version 1.0
 * @className CodAdminAuthConfig
 * @date 2019/11/9 12:44 PM
 */
@Getter
@Setter
@Component
public class CodAdminAuthConfig {

    @Value("${cod.admin.auth.type:}")
    private String type;
}
