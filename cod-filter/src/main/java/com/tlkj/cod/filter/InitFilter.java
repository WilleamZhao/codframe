package com.tlkj.cod.filter;

import com.tlkj.cod.core.spring.SpringContextUtil;
import com.tlkj.cod.filter.service.CodFilterService;
import com.tlkj.cod.launcher.CodModuleInitialize;
import com.tlkj.cod.launcher.CodModuleOrderEnum;
import com.tlkj.cod.launcher.model.CodModuleLauncherModel;
import com.tlkj.cod.server.model.CodServerFilterModel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className InitFilter
 * @date 2019/6/5 2:24 PM
 */
public class InitFilter implements CodModuleInitialize {

    private Logger logger = LoggerFactory.getLogger(InitFilter.class);

    @Override
    public int order() {
        return CodModuleOrderEnum.FILTER.getOrder();
    }

    @Override
    public String alias() {
        return "过滤器";
    }

    /**
     * 初始化 filter
     * @param codModuleLauncherModel 启动引导对象
     */
    @Override
    public void success(CodModuleLauncherModel codModuleLauncherModel) {
        // 获取所有 filter service
        Map<String, CodFilterService> map = SpringContextUtil.getApplicationContext().getBeansOfType(CodFilterService.class);
        // 遍历
        Set<Map.Entry<String, CodFilterService>> set = map.entrySet();
        List<CodServerFilterModel> list = new LinkedList<>();
        for (Map.Entry<String, CodFilterService> entry : set){
            String key = entry.getKey();
            // 获取 filter service/
            CodFilterService codFilterService = entry.getValue();
            // 设置 filter model
            if (codFilterService.state()) {
                CodServerFilterModel codServerFilterModel = new CodServerFilterModel();
                codServerFilterModel.setName(codFilterService.name());
                codServerFilterModel.setFilter(codFilterService.filter());
                codServerFilterModel.setDispatcher(codFilterService.dispatcherType());
                codServerFilterModel.setMapping(codFilterService.mapping());
                codServerFilterModel.setParamList(codFilterService.params());
                list.add(codServerFilterModel);
            }

            logger.info("加载 {} 过滤器, mapping = {}, name = {}, dispatcher = {}",
                    StringUtils.isBlank(codFilterService.alias()) ? codFilterService.name() : codFilterService.alias(),
                    codFilterService.mapping(), codFilterService.name(), codFilterService.dispatcherType().toString());
        }
        // 设置 filter
        codModuleLauncherModel.setData(CodModuleOrderEnum.FILTER.getOrder(), list, false);
    }

    @Override
    public void fail(CodModuleLauncherModel codModuleLauncherModel, Throwable e) {

    }

    @Override
    public void init(CodModuleLauncherModel codModuleLauncherModel) {
        codModuleLauncherModel.finish();
    }

    @Override
    public void fail(Throwable e) {

    }

}
