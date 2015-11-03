package org.qd4j.qdframework.helper;

import java.lang.reflect.Field;
import java.util.Map;

import org.qd4j.qdframework.annotation.Inject;
import org.qd4j.qdframework.util.ReflectionUtil;

/*
 * 依赖注入
 * */
public final class IOCHelper {

	static {
		Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();

		if (!beanMap.isEmpty()) {
			for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
				// 获取对象里要注入的成员变量，并给予对象实例
				Class<?> cls = beanEntry.getKey();
				Object beanInstance = beanEntry.getValue();
				Field[] beanFields = cls.getDeclaredFields();
				if (beanFields.length > 0) {
					for (Field field : beanFields) {
						if (field.isAnnotationPresent(Inject.class)) {
							Class<?> beanFieldClass = field.getType();
							Object beanFieldInstance = beanMap
									.get(beanFieldClass);
							if (beanFieldInstance != null) {
                               ReflectionUtil.setFieldValue(beanInstance, field, beanFieldInstance);
							}
						}
					}
				}
			}
		}
	}
}
