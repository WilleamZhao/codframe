package com.tlkj.cod.data.model.config;

import com.tlkj.cod.common.CodCommonDeviceInfo;
import lombok.Getter;
import lombok.Setter;

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
public class CodDataConfig {

    private String type = "data";

    /**
     * 驱动
     */
    private String driver = "org.h2.Driver";

    /**
     * 编码
     */
    private String encoding = "UTF-8";

    /**
     * 默认地址
     */
    private String url = "jdbc:h2:./.cod-temp/codData/.codDataDB;AUTO_SERVER=TRUE;AUTO_RECONNECT=TRUE;DB_CLOSE_DELAY=-1;MODE=MySQL";

    /**
     * 用户名
     * 随机电脑序列号
     */
    private String username = CodCommonDeviceInfo.getHardware().getComputerSystem().getSerialNumber();

    /**
     * 密码
     * 每台电脑不一样 (CPU ID)
     */
    private String password = CodCommonDeviceInfo.getHardware().getProcessor().getProcessorID();


}
