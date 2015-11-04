package org.qd4j.qdframework.bean;

public class JsonResult {
    private Object model;
    public JsonResult(Object model){
    	this.model=model;
    }
    
    public Object getModel(){
    	return this.model;
    }
}
