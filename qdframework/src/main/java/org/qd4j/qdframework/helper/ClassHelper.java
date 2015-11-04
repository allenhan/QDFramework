package org.qd4j.qdframework.helper;

import java.util.HashSet;
import java.util.Set;

import org.qd4j.qdframework.annotation.Controller;
import org.qd4j.qdframework.util.ClassUtil;

public final class ClassHelper {

	private static final Set<Class<?>> CLASS_SET;

	static {

		String basePackageString = "";

		CLASS_SET = ClassUtil.getClassSet(basePackageString);
	}

    //获取包下所有类
	public static Set<Class<?>> getConrollerClassSet(){
		Set<Class<?>> classSet=new HashSet<Class<?>>();
		for(Class<?> cls: CLASS_SET){
			if(cls.isAnnotationPresent(Controller.class))
			{
				classSet.add(cls);
			}
		}
		return classSet;
	}
	
	public static Set<Class<?>> getBeanClassSet() {
		Set<Class<?>> classSet=new HashSet<Class<?>>();
		classSet.addAll(getConrollerClassSet());
		return classSet;
	}
}
