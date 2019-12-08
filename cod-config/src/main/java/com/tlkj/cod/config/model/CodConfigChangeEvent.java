package com.tlkj.cod.config.model;

import com.tlkj.cod.config.model.enums.CodConfigSourceType;

import java.util.Map;
import java.util.Set;

/**
 * 修改 config
 * @author sourcod
 */
public class CodConfigChangeEvent {
    private final CodConfigSourceType type;
    private final Map<String, CodConfigModel> modelMap;

    public CodConfigChangeEvent(CodConfigSourceType type, Map<String, CodConfigModel> modelMap) {
        this.type = type;
        this.modelMap = modelMap;
    }

    public Set<String> changedKeys() {
        return modelMap.keySet();
    }

    public CodConfigModel getChange(String key) {
        return modelMap.get(key);
    }

    public boolean isChanged(String key) {
        return modelMap.containsKey(key);
    }

    public CodConfigSourceType getNamespace() {
        return type;
    }
}
