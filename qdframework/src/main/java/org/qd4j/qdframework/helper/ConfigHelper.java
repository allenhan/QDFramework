package org.qd4j.qdframework.helper;

import java.util.Properties;

import org.qd4j.qdframework.ConfigConst;
import org.qd4j.qdframework.util.PropsUtil;

public final class ConfigHelper {
      
	private static final Properties CONFIG_PROPS=PropsUtil.loadProperties(ConfigConst.CONFIG_FILE);
      
    public static String getJdbcDriver()
    {
    	return PropsUtil.getstrString(CONFIG_PROPS, ConfigConst.JDBC_DRIVER);
    }
    
    public static String getJdbcURL()
    {
    	return PropsUtil.getstrString(CONFIG_PROPS, ConfigConst.JDBC_URL);
    }
    
    public static String getAppJSPPath() {
    	return PropsUtil.getstrString(CONFIG_PROPS, ConfigConst.APP_JSP_PATH); 
	}
    
    //get jdbc username
    public static String getJdbcUserName()
    {
    	return PropsUtil.getstrString(CONFIG_PROPS, ConfigConst.JDBC_USERNAME);
    }
    
    //get jdbc pwd
    public static String getJdbcPwd()
    {
    	return PropsUtil.getstrString(CONFIG_PROPS, ConfigConst.JDBC_PWD);
    }
    
    //get 
    public static String getASSetPath()
    {
    	return PropsUtil.getstrString(CONFIG_PROPS, ConfigConst.APP_ASSET_PATH);
    }
}
