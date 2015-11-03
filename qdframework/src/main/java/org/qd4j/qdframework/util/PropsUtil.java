package org.qd4j.qdframework.util;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropsUtil {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(PropsUtil.class);

	public static Properties loadProperties(String filename) {
		Properties props = null;
		InputStream stream = null;
		try {
			stream = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(filename);
			if (stream == null) {
				throw new FileNotFoundException(filename + "is not found");
			}
			props = new Properties();
			props.load(stream);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (stream != null)
				try {
					stream.close();
				} catch (Exception e2) {
					LOGGER.error("file close error", e2);
				}
		}
		return props;
	}

	/*
	 * get string value
	 */
	public static String getstrString(Properties properties, String key) {
		String valueString = "";
		if (properties.containsKey(key)) {
			valueString = properties.getProperty(key);
		}
		return valueString;
	}

	public static int getInt(Properties properties, String key) {
		return getInt(properties, key, 0);
	}
    
	public static int getInt(Properties properties, String key,
			int defaultValue) {
		int value = defaultValue;
		if (properties.containsKey(key)) {
			value =CastUtil.CastToInt(properties.getProperty(key)); 
		}
		return value;
	}
}
