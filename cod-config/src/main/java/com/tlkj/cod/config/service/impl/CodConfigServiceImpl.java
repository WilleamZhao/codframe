package com.tlkj.cod.config.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.tlkj.cod.config.model.enums.CodConfigSourceType;
import com.tlkj.cod.config.service.CodConfigService;
import com.tlkj.cod.config.service.CodConfigChangeListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Desc 配置实现
 *
 * @author sourcod
 * @version 1.0
 * @className CodConfigServiceImpl
 * @date 2019/5/28 10:34 PM
 */
// @Service
public class CodConfigServiceImpl implements CodConfigService {

    // @Autowired
    // CodConfigFacade codConfigFacade;


    private final List<CodConfigChangeListener> listeners = Lists.newCopyOnWriteArrayList();
    private final Map<CodConfigChangeListener, Set<String>> codInterestedKeys = Maps.newConcurrentMap();
    private final Map<CodConfigChangeListener, Set<String>> codInterestedKeyPrefixes = Maps.newConcurrentMap();


    /**
     * 缓存
     */
    private Map map = new HashMap();

    /**
     * 内容
     */
    private void s() {

    }

    @Override
    public CodConfigSourceType getType() {
        return null;
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
        map.put("", "");
        return map;
    }

    @Override
    public Set<String> list() {
        return null;
    }

    @Override
    public String getProperty(String key, String defaultValue) {
        System.out.println(key);
        // return codConfigFacade.getProperty(key, defaultValue);
        Random random = new Random();
        return key + random.nextDouble() * 1000;
    }

    @Override
    public void addChangeListener(CodConfigChangeListener listener) {
        addChangeListener(listener, null);
    }

    @Override
    public void addChangeListener(CodConfigChangeListener listener, Set<String> interestedKeys) {
        addChangeListener(listener, interestedKeys, null);
    }

    @Override
    public void addChangeListener(CodConfigChangeListener listener, Set<String> interestedKeys, Set<String> interestedKeyPrefixes) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
            if (interestedKeys != null && !interestedKeys.isEmpty()) {
                codInterestedKeys.put(listener, Sets.newHashSet(interestedKeys));
            }
            if (interestedKeyPrefixes != null && !interestedKeyPrefixes.isEmpty()) {
                codInterestedKeyPrefixes.put(listener, Sets.newHashSet(interestedKeyPrefixes));
            }
        }
    }

    @Override
    public boolean removeChangeListener(CodConfigChangeListener listener) {
        codInterestedKeys.remove(listener);
        codInterestedKeyPrefixes.remove(listener);
        return listeners.remove(listener);
    }

    @Override
    public <T> T getProperty(String key, java.util.function.Function<String, T> function, T defaultValue) {
        return null;
    }

    @Override
    public Set<String> getPropertyNames() {
        System.out.println("asd");

        return null;
    }

    @Override
    public CodConfigService getSourceType() {
        return null;
    }
}
