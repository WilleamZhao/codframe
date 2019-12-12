package com.tlkj.cod.config.spring.config;

import com.tlkj.cod.config.model.enums.CodConfigSourceType;
import com.tlkj.cod.config.service.CodConfigService;
import com.tlkj.cod.config.service.CodConfigChangeListener;
import org.springframework.core.env.EnumerablePropertySource;

import java.util.Set;

/**
 * 配置 配置源
 *
 * @author sourcod
 */
public class ConfigPropertySource extends EnumerablePropertySource<CodConfigService> {
    private static final String[] EMPTY_ARRAY = new String[0];

    public ConfigPropertySource(CodConfigSourceType type, CodConfigService source) {
        super(type.name(), source);
    }

    @Override
    public String[] getPropertyNames() {
        Set<String> propertyNames = this.source.getPropertyNames();
        if (propertyNames.isEmpty()) {
            return EMPTY_ARRAY;
        }
        return propertyNames.toArray(new String[propertyNames.size()]);
    }

    @Override
    public Object getProperty(String name) {
        String key = name.split(":")[0];
        String defaultValue = name.indexOf(":") > 0 ? name.substring(key.length() + 1) : "";
        return this.source.getProperty(key, defaultValue);
    }



    public void addChangeListener(CodConfigChangeListener listener) {
        this.source.addChangeListener(listener);
    }
}
