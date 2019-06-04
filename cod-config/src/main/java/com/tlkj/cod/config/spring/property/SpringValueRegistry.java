package com.tlkj.cod.config.spring.property;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import org.springframework.beans.factory.BeanFactory;

import java.util.Collection;
import java.util.Map;

/**
 * @author sourcod
 */
public class SpringValueRegistry {
    private final Map<BeanFactory, Multimap<String, SpringValue>> registry = Maps.newConcurrentMap();
    private final Object LOCK = new Object();

    private static volatile SpringValueRegistry singleton;

    private SpringValueRegistry() {

    }

    public static SpringValueRegistry getInstance() {
        if (singleton == null) {
            synchronized (SpringValueRegistry.class) {
                if (singleton == null) {
                    singleton = new SpringValueRegistry();
                }
            }
        }
        return singleton;
    }

    public void register(BeanFactory beanFactory, String key, SpringValue springValue) {
        if (!registry.containsKey(beanFactory)) {
            synchronized (LOCK) {
                if (!registry.containsKey(beanFactory)) {
                    registry.put(beanFactory, Multimaps.synchronizedListMultimap(LinkedListMultimap.create()));
                }
            }
        }

        registry.get(beanFactory).put(key, springValue);
    }

    public Map getRegistry(){
        return registry;
    }

    public Collection<SpringValue> get(BeanFactory beanFactory, String key) {
        Multimap<String, SpringValue> beanFactorySpringValues = registry.get(beanFactory);
        if (beanFactorySpringValues == null) {
            return null;
        }
        return beanFactorySpringValues.get(key);
    }

}
