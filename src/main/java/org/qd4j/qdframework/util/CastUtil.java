package org.qd4j.qdframework.util;

public final class CastUtil {
     
	public static String CastToString(Object obj){
	   return CastToString(obj,"");
	}
	
    public static String CastToString(Object obj,String defaultvalue){
		String valueString=defaultvalue;
        if(obj!=null){
        	valueString=obj.toString();
        }
		return valueString;
	}

	public static int CastToInt(Object obj){
		return CastToInt(obj,0);
	}
	
    public static int CastToInt(Object obj,int defaultValue){
		int value=defaultValue;
		if(obj!=null)
		{
			value=Integer.parseInt(CastToString(obj));
		}
		return value;
	}
    
    public static long CastToLong(Object obj){
		return CastToLong(obj,0l);
	}
	
    public static long CastToLong(Object obj,Long defaultValue){
		long value=defaultValue;
		if(obj!=null)
		{
			value=Long.parseLong(CastToString(obj));
		}
		return value;
	}
}
