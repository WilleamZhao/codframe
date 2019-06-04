package com.tlkj.cod.dao.facade.impl;

import com.tlkj.cod.config.model.enums.CodConfigSourceType;
import com.tlkj.cod.config.service.CodConfigService;
import com.tlkj.cod.config.service.ConfigChangeListener;
import com.tlkj.cod.dao.jdbc.Finder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * Desc codConfig 数据库数据源
 *
 * @author sourcod
 * @version 1.0
 * @className CodConfigDatabaseServiceImpl
 * @date 2019/5/30 1:08 PM
 */
@Service("codConfigDatabaseServiceImpl")
public class CodConfigDatabaseServiceImpl implements CodConfigService {

    private Map<String, Object> map = new HashMap<>();

    private Finder finder = null;

    @Autowired
    public CodConfigDatabaseServiceImpl(){
        this.finder = new Finder();
    }


    @Override
    public CodConfigSourceType getType() {
        return CodConfigSourceType.DATABASE;
    }

    @Override
    public boolean init() {
        return false;
    }

    @Override
    public int order() {
        return 0;
    }

    @Override
    public Map<String, Object> load() {
        Map<String, Object> map = new HashMap<>();
        map.put("port", "asd");
        map.put("ip", "ip");
        map.put("zxc", "333");
        map.put("fff", "444");
        map.put("cod.database.url", "444");
        map.put("cod.database.type", "444");
        map.put("cod.database.driverClass", "444");
        // List o = finder.from("information_schema").all();
        // System.out.println(CodCommonJson.dump(o));
        return map;
    }


    @Override
    public Set<String> list() {
        return null;
    }

    @Override
    public String getProperty(String key, String defaultValue) {
        // step 1: check system properties, i.e. -Dkey=value
        String value = System.getProperty(key);

        if (StringUtils.isEmpty(value)) {
            value = System.getenv(key);
        }
        if (this.map.isEmpty()){
            this.map = load();
        }
        if (StringUtils.isEmpty(value)){
            value = map.getOrDefault(key, defaultValue) != null ? map.getOrDefault(key, defaultValue).toString() : null;
        }
        return value;
    }

    @Override
    public Set<String> getPropertyNames() {
        return null;
    }

    @Override
    public void addChangeListener(ConfigChangeListener listener) {

    }

    @Override
    public void addChangeListener(ConfigChangeListener listener, Set<String> interestedKeys) {

    }

    @Override
    public void addChangeListener(ConfigChangeListener listener, Set<String> interestedKeys, Set<String> interestedKeyPrefixes) {

    }

    @Override
    public boolean removeChangeListener(ConfigChangeListener listener) {
        return false;
    }

    @Override
    public <T> T getProperty(String key, Function<String, T> function, T defaultValue) {
        return null;
    }

    @Override
    public CodConfigService getSourceType() {
        return null;
    }
}
