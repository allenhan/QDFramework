package org.qd4j.qdframework.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


import org.qd4j.qdframework.util.ReflectionUtil;

public final class BeanHelper {
  
	private static final Map<Class<?>, Object> BEAN_MAP=new HashMap<Class<?>, Object>();
    
    static{
    	Set<Class<?>> beanClass=ClassHelper.getBeanClassSet();
    	for (Class<?> cls : beanClass) {
		     Object object=ReflectionUtil.newInstatnce(cls);
		     BEAN_MAP.put(cls, object);
		}
    }
    
    //get bean map
    public static Map<Class<?>, Object> getBeanMap(){
    	return BEAN_MAP;
    }
 
    @SuppressWarnings("unchecked")
	public static <T> T getBean(Class<?> cls){
    	if(!BEAN_MAP.containsKey(cls)){
    		throw new RuntimeException("can not find bean by class");
    	}
    	return (T)BEAN_MAP.get(cls);
    }
}
