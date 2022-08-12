package com.tri.eka.client.aspecs;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tri.eka.client.annotations.ItemConfiguration;

@Component
@Aspect
public class ItemConfigurationAspecs {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemConfigurationAspecs.class);

	@Pointcut(value = "execution(public * *(..))")
	public void anyPublicMethod() {}

	@Around(value = "anyPublicMethod() && @annotation(itemConfig)")
	public Object itemAnnotationHandler(ProceedingJoinPoint joinPoint, ItemConfiguration itemConfig) throws Throwable {

		LOGGER.info("<<<<ITEM ASPECT LOGGER ========>>>>");

		return itemConfig.name();

	}

}
