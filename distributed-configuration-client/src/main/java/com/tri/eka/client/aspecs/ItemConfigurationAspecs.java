package com.tri.eka.client.aspecs;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tri.eka.client.annotations.FileConfiguration;
import com.tri.eka.client.annotations.ItemConfiguration;
import com.tri.eka.client.store.ConfigurationStoreCenter;

@Aspect
public class ItemConfigurationAspecs {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemConfigurationAspecs.class);

	@Pointcut(value = "execution(public * *(..))")
	public void anyPublicMethod() {}

	@Around(value = "anyPublicMethod() && @annotation(itemConfig)")
	public Object itemAnnotationHandler(ProceedingJoinPoint joinPoint, ItemConfiguration itemConfig) throws Throwable {

		LOGGER.info("<<<<======= ITEM ASPECT LOGGER ========>>>>");
		
		/**
		 * Get Assosiated Method
		 */
		MethodSignature ms = (MethodSignature) joinPoint.getSignature();
		Method method = ms.getMethod();
		
		Class<?> cls = method.getClass();
		FileConfiguration fileConfig = cls.getDeclaredAnnotation(FileConfiguration.class);
		
		Field field = getFieldFromMethod(method, cls.getDeclaredFields());
		if (field != null) {
			Object val = ConfigurationStoreCenter.getItem(fileConfig.fileName(), itemConfig.name());
			if (val != null) {
				return val;
			}
		}
		
		Object rtnOb;

        try {
            rtnOb = joinPoint.proceed();
        } catch (Throwable t) {
            LOGGER.info(t.getMessage());
            throw t;
        }
        return rtnOb;

	}

	private Field getFieldFromMethod(Method method, Field[] declaredFields) {
		// TODO Auto-generated method stub
		return null;
	}

}
