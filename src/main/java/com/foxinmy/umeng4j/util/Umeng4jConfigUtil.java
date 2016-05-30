package com.foxinmy.umeng4j.util;

import java.util.ResourceBundle;

/**
 * 友盟配置
 * 
 * @className Umeng4jConfigUtil
 * @author jinyu(foxinmy@gmail.com)
 * @date 2015年1月28日
 * @since JDK 1.7
 * @see
 */
public class Umeng4jConfigUtil {
	private final static ResourceBundle umengBundle;
	static {
		umengBundle = ResourceBundle.getBundle("umeng4j");
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
