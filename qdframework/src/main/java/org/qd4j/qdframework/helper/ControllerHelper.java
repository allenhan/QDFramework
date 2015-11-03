package org.qd4j.qdframework.helper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


import org.qd4j.qdframework.annotation.Action;
import org.qd4j.qdframework.bean.Handle;
import org.qd4j.qdframework.bean.Request;

public final class ControllerHelper {

	private static final Map<Request, Handle> Actionm_Map=new HashMap<Request, Handle>();
	
	static{
	    Set<Class<?>> controllercClasses=ClassHelper.getConrollerClassSet();
	    if(!controllercClasses.isEmpty()){
	    	for (Class<?> class1 : controllercClasses) {
				Method[] methods=class1.getDeclaredMethods();
				for (Method method : methods) {
					if(method.isAnnotationPresent(Action.class)){
						Action action=method.getAnnotation(Action.class);
						String mappingString=action.value();
						if(mappingString.matches("\\w:/\\w*")){
							String[] arrayStrings=mappingString.split(":");
							if(arrayStrings.length==2){
								String requreMehotd=arrayStrings[0];
								String requrePath=arrayStrings[1];
							    Request request=new Request();
							    request.setRequestMethod(requreMehotd);
							    request.setRequestPath(requrePath);
							    Handle handle=new Handle(class1, method);
							    Actionm_Map.put(request, handle);
							}
						}
					}
				}
			}
	    }
	}
	
	public static Handle getHandle(String requestMehod,String requestPath){
		Request request=new Request();
		request.setRequestMethod(requestMehod);
		request.setRequestPath(requestPath);
		return Actionm_Map.get(request);
	}
}
