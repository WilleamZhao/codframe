package com.tlkj.cod.config.spring.config;

import com.tlkj.cod.config.model.enums.CodConfigSourceType;
import com.tlkj.cod.config.service.CodConfigService;
import com.tlkj.cod.config.service.ConfigChangeListener;
import org.springframework.core.env.EnumerablePropertySource;

import java.util.Set;

/**
 * Property source wrapper for Config
 *
 * @author Jason Song(song_s@ctrip.com)
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
        return this.source.getProperty(name, null);
    }



    public void addChangeListener(ConfigChangeListener listener) {
        this.source.addChangeListener(listener);
    }
}
