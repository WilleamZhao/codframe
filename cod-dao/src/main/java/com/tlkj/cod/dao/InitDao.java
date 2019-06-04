package com.tlkj.cod.dao;

import com.google.common.collect.Lists;
import com.tlkj.cod.common.CodCommonJson;
import com.tlkj.cod.config.model.config.CodCoreConfig;
import com.tlkj.cod.config.spring.config.PropertySourcesProcessor;
import com.tlkj.cod.dao.facade.impl.CodConfigDatabaseServiceImpl;
import com.tlkj.cod.launcher.init.CodSpringInitialize;
import com.tlkj.cod.launcher.model.LauncherModel;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * Desc 初始化Dao
 *
 * @author sourcod
 * @version 1.0
 * @className InitDao
 * @date 2019/5/30 10:24 AM
 */
public class InitDao implements CodSpringInitialize {
    @Override
    public int order() {
        return -7;
    }

    @Override
    public void init(LauncherModel launcherModel) {

        AnnotationConfigWebApplicationContext applicationContext = launcherModel.getSpring();

        // 注册codData数据源
        PropertySourcesProcessor.addCodConfigDataSource(Lists.newArrayList(new CodConfigDatabaseServiceImpl()), 0);

        // 定义processor
        PropertySourcesProcessor propertySourcesProcessor = new PropertySourcesProcessor();
        propertySourcesProcessor.setEnvironment(applicationContext.getEnvironment());
        applicationContext.addBeanFactoryPostProcessor(propertySourcesProcessor);

        Object o1 = launcherModel.getSpring().getBean(CodCoreConfig.class);
        System.out.println(CodCommonJson.dump(o1));
        launcherModel.next();
    }

    @Override
    public void fail(Throwable e) {

    }
}
