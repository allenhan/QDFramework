package org.qd4j.qdframework.bean;

import java.lang.reflect.Method;

public class Handle {
      
	private Class<?> controllerClass;
	
	private Method  method;
	
	public Handle(Class<?> class1,Method method1){
		this.controllerClass=class1;
		this.method=method1;
	}

	public Class<?> getControllerClass() {
		return controllerClass;
	}

	public void setControllerClass(Class<?> controllerClass) {
		this.controllerClass = controllerClass;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}
	
	
}
