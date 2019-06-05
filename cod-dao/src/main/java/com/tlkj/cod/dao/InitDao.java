package com.tlkj.cod.dao;

import com.tlkj.cod.launcher.init.CodModuleSpringInitialize;
import com.tlkj.cod.launcher.model.CodModuleLauncherModel;

/**
 * Desc 初始化Dao
 *
 * @author sourcod
 * @version 1.0
 * @className InitDao
 * @date 2019/5/30 10:24 AM
 */
public class InitDao implements CodModuleSpringInitialize {

    @Override
    public int order() {
        return -70;
    }

    @Override
    public void init(CodModuleLauncherModel codModuleLauncherModel) {

        /*
        AnnotationConfigWebApplicationContext applicationContext = codModuleLauncherModel.getSpring();

        // 注册codData数据源
        PropertySourcesProcessor.addCodConfigDataSource(Lists.newArrayList(new CodConfigDatabaseServiceImpl()), 0);

        // 定义processor
        PropertySourcesProcessor propertySourcesProcessor = new PropertySourcesProcessor();
        propertySourcesProcessor.setEnvironment(applicationContext.getEnvironment());
        applicationContext.addBeanFactoryPostProcessor(propertySourcesProcessor);

        Object o1 = codModuleLauncherModel.getSpring().getBean(CodCoreConfig.class);
        System.out.println(CodCommonJson.dump(o1));
        codModuleLauncherModel.finish();
        */
    }

    @Override
    public void fail(Throwable e) {

    }
}
