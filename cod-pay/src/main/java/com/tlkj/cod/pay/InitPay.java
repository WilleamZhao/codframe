package com.tlkj.cod.pay;

import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.CodModuleOrderEnum;
import com.tlkj.cod.launcher.model.CodModuleLauncherModel;

/**
 * Desc 初始化 pay 模块
 *
 * @author sourcod
 * @version 1.0
 * @className InitPay
 * @date 2019/8/21 9:17 AM
 */
public class InitPay implements CodModuleInitialize {

    @Override
    public int order() {
        return CodModuleOrderEnum.NO.getOrder();
    }

    @Override
    public String alias() {
        return "支付";
    }

    @Override
    public void success(CodModuleLauncherModel codModuleLauncherModel) {

    }

    @Override
    public void fail(CodModuleLauncherModel codModuleLauncherModel, Throwable e) {

    }

    @Override
    public void init(CodModuleLauncherModel codModuleLauncherModel) {
        codModuleLauncherModel.next();
    }

    @Override
    public void fail(Throwable e) {

    }
}
