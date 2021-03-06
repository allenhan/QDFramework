package org.qd4j.qdframework.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class ReflectionUtil {

	public static Object newInstatnce(Class<?> cls)
	{
		Object object=null;
		try {
			object= cls.newInstance();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return object;
	}
	
	public static Object createInsance(String className){
	   Class<?> cls=ClassUtil.loadClass(className, false);
	   return newInstatnce(cls);
	}
	
	
	public static Object InvokeMethod(Object obj,Method method,Object...args){
		Object result=null;
		try {
			method.setAccessible(true);
			result=method.invoke(obj, args);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	public static void setFieldValue(Object obj,Field field,Object value) {
		try {
			field.setAccessible(true);
			field.set(obj, value);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
