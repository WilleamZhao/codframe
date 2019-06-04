package com.tlkj.cod.config.spring.config;

import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.tlkj.cod.config.service.CodConfigService;
import com.tlkj.cod.config.spring.property.AutoUpdateConfigChangeListener;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author Jason Song(song_s@ctrip.com)
 */
public class PropertySourcesProcessor implements BeanFactoryPostProcessor, EnvironmentAware, PriorityOrdered {
    private static final Multimap<Integer, CodConfigService> COD_CONFIG_DATASOURCE = LinkedHashMultimap.create();
    private static final Set<BeanFactory> AUTO_UPDATE_INITIALIZED_BEAN_FACTORIES = Sets.newConcurrentHashSet();

    private final ConfigPropertySourceFactory configPropertySourceFactory = ConfigPropertySourceFactory.getInstance();
    private ConfigurableEnvironment environment;

    private String prevDataSource = "";

    /**
     * 添加数据源
     * @param dataSource 数据源
     * @param order      排序
     * @return
     */
    public static boolean addCodConfigDataSource(Collection<CodConfigService> dataSource, int order) {
        return COD_CONFIG_DATASOURCE.putAll(order, dataSource);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        initializePropertySources();
        initializeAutoUpdatePropertiesFeature(beanFactory);
    }

    /**
     * 初始化数据源
     */
    private void initializePropertySources() {
        CompositePropertySource composite;
        //sort by order asc
        ImmutableSortedSet<Integer> orders = ImmutableSortedSet.copyOf(COD_CONFIG_DATASOURCE.keySet());

        for (Integer order : orders) {
            for (CodConfigService config : COD_CONFIG_DATASOURCE.get(order)) {
                if (environment.getPropertySources().contains(config.getType().name())){
                    continue;
                }
                composite = new CompositePropertySource(config.getType().name());
                composite.addPropertySource(configPropertySourceFactory.getConfigPropertySource(config.getType(), config));
                if (StringUtils.isEmpty(prevDataSource)){
                    environment.getPropertySources().addFirst(composite);
                    prevDataSource = config.getType().name();
                } else {
                    environment.getPropertySources().addAfter(prevDataSource, composite);
                }
            }
        }

        // clean up
        COD_CONFIG_DATASOURCE.clear();
    }

    /**
     * 初始化自动更新
     */
    private void initializeAutoUpdatePropertiesFeature(ConfigurableListableBeanFactory beanFactory) {
        if (!AUTO_UPDATE_INITIALIZED_BEAN_FACTORIES.add(beanFactory)) {
            return;
        }
        AutoUpdateConfigChangeListener autoUpdateConfigChangeListener = new AutoUpdateConfigChangeListener(environment, beanFactory);

        List<ConfigPropertySource> configPropertySources = configPropertySourceFactory.getAllConfigPropertySources();
        for (ConfigPropertySource configPropertySource : configPropertySources) {
            configPropertySource.addChangeListener(autoUpdateConfigChangeListener);
        }
    }

    @Override
    public void setEnvironment(Environment environment) {
        //it is safe enough to cast as all known environment is derived from ConfigurableEnvironment
        this.environment = (ConfigurableEnvironment) environment;
    }

    @Override
    public int getOrder() {
        //make it as early as possible
        return Ordered.HIGHEST_PRECEDENCE;
    }

    // for test only
    static void reset() {
        COD_CONFIG_DATASOURCE.clear();
        AUTO_UPDATE_INITIALIZED_BEAN_FACTORIES.clear();
    }
}
