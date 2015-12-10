package com.foxinmy.umeng4j.util;

import java.io.File;
import java.util.ResourceBundle;

/**
 * 友盟配置
 * 
 * @className Umeng4jConfigUtil
 * @author jy
 * @date 2015年1月28日
 * @since JDK 1.7
 * @see
 */
public class Umeng4jConfigUtil {
	private final static String CLASSPATH_PREFIX = "classpath:";
	private final static String CLASSPATH_VALUE;
	private final static ResourceBundle umengBundle;
	static {
		umengBundle = ResourceBundle.getBundle("umeng4j");
		File file = null;
		CLASSPATH_VALUE = Thread.currentThread().getContextClassLoader()
				.getResource("").getPath();
		for (String key : umengBundle.keySet()) {
			if (!key.endsWith(".path")) {
				continue;
			}
			file = new File(getValue(key).replaceFirst(CLASSPATH_PREFIX,
					CLASSPATH_VALUE));
			if (!file.exists() && !file.mkdirs()) {
				System.err.append(String.format("%s create fail.%n",
						file.getAbsolutePath()));
			}
		}
	}
	private final static String UMENG4J_PREFIX = "umeng4j";

	private static String wrapKeyName(String key) {
		if (!key.startsWith(UMENG4J_PREFIX)) {
			return String.format("%s.%s", UMENG4J_PREFIX, key);
		}
		return key;
	}

	/**
	 * 获取umeng4j.properties文件中的key值
	 * 
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		String wrapKey = wrapKeyName(key);
		return System.getProperty(wrapKey, umengBundle.getString(wrapKey));
	}
}
