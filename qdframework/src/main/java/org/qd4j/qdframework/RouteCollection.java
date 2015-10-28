package org.qd4j.qdframework;

import java.util.HashMap;

public final class RouteCollection {

	private static HashMap<String, RouteData> routeHashMap=new HashMap<String, RouteData>();
	
	public static void RouteMap(String name,RouteData routeData){
		if(!routeHashMap.containsKey(name)){
			routeHashMap.put(name, routeData);
		}
	}
	
	
}
