package org.qd4j.qdframework.util;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class CodeUtil {
     
	/*
	 * url 编码
	 * */
	public static String encodeURL(String source){
        String target = "";
        try {
			target=URLEncoder.encode(source,"UTF-8");
		} catch (Exception e) {
			// TODO: handle exception
		}
        return target;
    }
	
	/*
	 * url 解码
	 * */
	public static String decodeURL(String source){
        String target = "";
        try {
			target=URLDecoder.decode(source,"UTF-8");
		} catch (Exception e) {
			// TODO: handle exception
		}
        return target;
    }
}
