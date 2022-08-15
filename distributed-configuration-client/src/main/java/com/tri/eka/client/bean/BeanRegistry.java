package com.tri.eka.client.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import com.tri.eka.client.aspecs.ItemConfigurationAspecs;

@Component
public class BeanRegistry implements BeanDefinitionRegistryPostProcessor, PriorityOrdered{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BeanRegistry.class);

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
	
		LOGGER.info("========= BEAN DEFENITION REGISTRY START ========");

		registerAspect(registry);		
		
		LOGGER.info("========= BEAN DEFENITION REGISTRY END ========");
	}
	
	private void registerAspect(BeanDefinitionRegistry registry) {
		GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
		beanDefinition.setBeanClass(ItemConfigurationAspecs.class);
		beanDefinition.setLazyInit(false);
		beanDefinition.setAbstract(false);
		beanDefinition.setAutowireCandidate(true);
//		beanDefinition.setScope("singelton");
		
		registry.registerBeanDefinition("ItemConfigurationAspecs", beanDefinition);
		
	}

	@Override
	public int getOrder() {
		return Ordered.HIGHEST_PRECEDENCE + 2;
	}

}
