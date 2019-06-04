package com.tlkj.cod.config.service.impl;

import com.tlkj.cod.config.model.enums.CodConfigSourceType;
import com.tlkj.cod.config.service.CodConfigService;
import com.tlkj.cod.config.service.ConfigChangeListener;
import com.tlkj.cod.data.service.CodDataService;
import com.tlkj.cod.data.service.impl.CodDataServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * Desc CodData 数据源
 *
 * @author sourcod
 * @version 1.0
 * @className CodConfigDataServiceImpl
 * @date 2019/5/30 11:17 AM
 */
@Service("codConfigDataServiceImpl")
public class CodConfigDataServiceImpl implements CodConfigService {

    private Map<String, Object> map = new HashMap<>();

    CodDataService codDataService = new CodDataServiceImpl();

    @Override
    public CodConfigSourceType getType() {
        return CodConfigSourceType.DATA;
    }

    @Override
    public boolean init() {
        return true;
    }

    @Override
    public Map<String, Object> load() {
        // 模拟数据
        Map<String, Object> map = new HashMap<>();
        map.put("asd", "111");
        map.put("qwe", "222");
        map.put("zxc", "333");
        map.put("fff", "444");
        map.put("cod.database.url", "444");
        // map = codDataService.config();
        return map;
    }


    @Override
    public Set<String> list() {
        return map.keySet();
    }

    @Override
    public String getProperty(String key, String defaultValue) {
        if (map.isEmpty()){
            map = load();
        }
        return map.getOrDefault(key, defaultValue) != null ? map.getOrDefault(key, defaultValue).toString() : null;
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
