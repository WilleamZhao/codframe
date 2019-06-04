package com.tlkj.cod.h2.model;

import com.tlkj.cod.common.CodCommonDeviceInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * Desc 内置H2数据库信息
 *
 * @author sourcod
 * @version 1.0
 * @className CodH2Config
 * @date 2019/5/27 11:04 PM
 */
@Getter
@Setter
public class CodH2Config {

    private String driver = "org.h2.Driver";
    private String encoding = "UTF-8";
    private String url = "jdbc:h2:./.codConfigDB;AUTO_SERVER=TRUE;DB_CLOSE_DELAY=-1;MODE=MySQL";
    private String username = CodCommonDeviceInfo.getHardware().getComputerSystem().getSerialNumber();
    private String password = CodCommonDeviceInfo.getHardware().getProcessor().getProcessorID();


}
