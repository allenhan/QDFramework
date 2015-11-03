package org.qd4j.qdframework.bean;

import java.util.HashMap;
import java.util.Map;


public class ViewResult {
    //view path 
	private String path;
	
	private Map<String, Object> model;
	
	public ViewResult(String path){
		this.path=path;
		model=new HashMap<String, Object>();
	}
	
	public ViewResult addMode(String key,Object value){
	     model.put(key, value);
	     return this;
	}
	
	public String getPath(){
		return this.path;
	}
	
	public Map<String, Object> getModel(){
		return model;
	}
}
