package com.tlkj.cod.data;

import com.tlkj.cod.dao.bean.DataConnectBean;
import com.tlkj.cod.dao.model.enums.CodDaoDatasourceTypeEnum;
import com.tlkj.cod.dao.util.CodDaoConnectionPool;
import com.tlkj.cod.data.model.config.CodDataConfig;
import com.tlkj.cod.launcher.CodModuleOrderEnum;
import com.tlkj.cod.launcher.init.CodModuleDataInitialize;
import com.tlkj.cod.launcher.model.CodModuleLauncherModel;

/**
 * Desc 初始化数据
 *
 * @author sourcod
 * @version 1.0
 * @className InitData
 * @date 2019/5/28 11:06 AM
 */
public class InitData implements CodModuleDataInitialize {

    @Override
    public int order() {
        return CodModuleOrderEnum.DATA.getOrder();
    }

    @Override
    public void init(CodModuleLauncherModel codModuleLauncherModel) {
        // 设置 CodData 数据库连接信息 H2
        DataConnectBean bean = new DataConnectBean();
        CodDataConfig codDataConfig = new CodDataConfig();
        bean.setCharacterEncoding(codDataConfig.getEncoding());
        bean.setDriverClass(codDataConfig.getDriver());
        bean.setUsername(codDataConfig.getUsername());
        bean.setPassword(codDataConfig.getPassword());
        bean.setUrl(codDataConfig.getUrl());
        bean.setName(CodDaoDatasourceTypeEnum.DATA.name());
        CodDaoConnectionPool.getInstance().setDataSource(CodDaoDatasourceTypeEnum.DATA.name(), bean);
    }

    @Override
    public void fail(Throwable e) {

    }


}
