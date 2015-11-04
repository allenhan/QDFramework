package org.qd4j.qdframework.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamUtil {

	public static String getString(InputStream is){
		StringBuilder sBuilder=new StringBuilder();
		try {
			BufferedReader bReader=new BufferedReader(new InputStreamReader(is));
			String line;
			while ((line=bReader.readLine())!=null) {
				sBuilder.append(line);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	    return sBuilder.toString();
	}
}
