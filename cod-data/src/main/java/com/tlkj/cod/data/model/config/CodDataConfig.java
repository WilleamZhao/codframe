package com.tlkj.cod.data.model.config;

import com.tlkj.cod.common.CodCommonDeviceInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Desc 内置H2数据库信息
 *
 * @author sourcod
 * @version 1.0
 * @className CodDataConfig
 * @date 2019/5/27 11:04 PM
 */
@Getter
@Setter
@Component
public class CodDataConfig {

    @Value("${cod.config.data.driver:org.h2.Driver}")
    private String driver;

    @Value("${cod.config.data.encode:UTF-8}")
    private String encoding;

    @Value("${cod.config.data.url:jdbc:h2:./.codConfigDB;AUTO_SERVER=TRUE;DB_CLOSE_DELAY=-1;MODE=MySQL}")
    private String url;

    private String username = CodCommonDeviceInfo.getHardware().getComputerSystem().getSerialNumber();

    private String password = CodCommonDeviceInfo.getHardware().getProcessor().getProcessorID();


}
