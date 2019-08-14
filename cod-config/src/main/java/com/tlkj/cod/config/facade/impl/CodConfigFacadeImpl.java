package com.tlkj.cod.config.facade.impl;

import com.tlkj.cod.common.CodCommonJson;
import com.tlkj.cod.config.facade.CodConfigFacade;
import com.tlkj.cod.config.model.CodConfig;
import com.tlkj.cod.config.model.enums.CodConfigSourceType;
import com.tlkj.cod.config.service.CodConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Desc
 *
 * @author sourcod
 * @version 1.0
 * @className CodConfigFacadeImpl
 * @date 2019/5/30 11:30 AM
 */
// @Service
public class CodConfigFacadeImpl implements CodConfigFacade {

    // @Autowired
    private final List<CodConfigService> codConfigServiceList;

    private static final List<Map<String, Object>> list = new ArrayList<>();
    private static final List<CodConfig> codConfigs = new ArrayList<>();

    // @Autowired
    public CodConfigFacadeImpl(List<CodConfigService> codConfigServices){
        this.codConfigServiceList = sort(codConfigServices);
    }

    @Override
    public boolean init() {
        for (CodConfigService codConfigService : codConfigServiceList){
            if (codConfigService.init()){
                return false;
            }
        }
        return true;
    }

    @Override
    public List<Map<String, Object>> load() {
        for (CodConfigService codConfigService : codConfigServiceList){
            list.add(codConfigService.load());
        }
        return list;
    }

    @Override
    public List reload() {
        list.clear();
        for (CodConfigService codConfigService : codConfigServiceList){
            list.add(codConfigService.load());
        }
        return list;
    }

    @Override
    public List reload(CodConfigSourceType type) {
        for (CodConfigService codConfigService : codConfigServiceList){
            if (codConfigService.getType() == type){
                codConfigService.list();
                list.add(codConfigService.load());
            }
        }
        return list;
    }

    @Override
    public List list() {
        return null;
    }

    @Override
    public String getProperty(String key, String defaultValue) {
        if (list.isEmpty()){
            init();
            load();
        }
        System.out.println(CodCommonJson.dump(list));
        return "";
    }

    @Override
    public String getProperty(String key, String defaultValue, CodConfigSourceType type) {
        return null;
    }

    /**
     * 获取所有属性
     * @return
     */
    @Override
    public Set<String> getPropertyNames() {
        return null;
    }

    /**
     * 排序
     */
    private List<CodConfigService> sort(List<CodConfigService> codConfigServices){
        return codConfigServices.stream().sorted(Comparator.comparing(CodConfigService::order)).collect(Collectors.toList());
    }
}
