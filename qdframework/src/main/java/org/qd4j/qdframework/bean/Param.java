package org.qd4j.qdframework.bean;

import java.util.List;
import java.util.Map;

public class Param {
    
	private Map<String,Object> m_paramMap;
    
	private List<FormParam>  formParams;
	
     public Param(Map<String,Object> paramMap){
         this.m_paramMap=paramMap;    	 
     }
     
     public Param(List<FormParam> formParam){
    	 this.formParams=formParam;
     }
     
}
