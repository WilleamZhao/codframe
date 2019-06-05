package com.tlkj.cod.config.spring.annotation;

import com.tlkj.cod.config.annotation.CodValue;
import com.tlkj.cod.config.model.enums.CodConfigSourceType;
import com.tlkj.cod.config.spring.property.PlaceholderHelper;
import com.tlkj.cod.config.spring.property.SpringValue;
import com.tlkj.cod.config.spring.property.SpringValueRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author sourcod
 */
public class SpringValueProcessor extends AbstractCodConfigProcessor implements BeanFactoryPostProcessor, BeanFactoryAware {

    private static final Logger logger = LoggerFactory.getLogger(SpringValueProcessor.class);

    private final PlaceholderHelper placeholderHelper = PlaceholderHelper.getInstance();
    private final SpringValueRegistry springValueRegistry = SpringValueRegistry.getInstance();

    private BeanFactory beanFactory;

    public SpringValueProcessor() {
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        /*WxUserDo wxUserDo = new WxUserDo();
        try {
            beanFactory.getTypeConverter().convertIfNecessary("spring.liveBeansView.mbeanDomain", String.class, wxUserDo.getClass().getField("id"));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        // PlaceholderHelper.getInstance().resolvePropertyValue(beanFactory, "wxUserDo", "${spring.profiles.default}");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }


    @Override
    protected void processField(Object bean, String beanName, Field field) {
        // register @Value on field
        Value value = field.getAnnotation(Value.class);
        CodValue codValue = field.getAnnotation(CodValue.class);
        if (value == null) {
            return;
        }
        CodConfigSourceType type = null;
        if (codValue != null){
            type = codValue.type();
        }
        System.out.println(value.value());
        Set<String> keys = placeholderHelper.extractPlaceholderKeys(value.value());

        if (keys.isEmpty()) {
            return;
        }

        for (String key : keys) {
            SpringValue springValue = new SpringValue(key, value.value(), bean, beanName, field, false);
            springValueRegistry.register(beanFactory, key, springValue);
            logger.debug("Monitoring {}", springValue);
        }
    }

    @Override
    protected void processMethod(Object bean, String beanName, Method method) {
        //register @Value on method
        Value value = method.getAnnotation(Value.class);
        if (value == null) {
            return;
        }
        System.out.println(value.value());
        //skip Configuration bean methods
        if (method.getAnnotation(Bean.class) != null) {
            return;
        }
        if (method.getParameterTypes().length != 1) {
            logger.error("Ignore @Value setter {}.{}, expecting 1 parameter, actual {} parameters",
                    bean.getClass().getName(), method.getName(), method.getParameterTypes().length);
            return;
        }

        Set<String> keys = placeholderHelper.extractPlaceholderKeys(value.value());

        if (keys.isEmpty()) {
            return;
        }

        for (String key : keys) {
            SpringValue springValue = new SpringValue(key, value.value(), bean, beanName, method, false);
            springValueRegistry.register(beanFactory, key, springValue);
            logger.info("Monitoring {}", springValue);
        }
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
