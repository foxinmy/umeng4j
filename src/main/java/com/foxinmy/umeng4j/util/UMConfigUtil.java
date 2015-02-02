package com.foxinmy.umeng4j.util;

import java.io.File;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * 环信配置
 * 
 * @className EMConfigUtil
 * @author jy
 * @date 2015年1月28日
 * @since JDK 1.7
 * @see
 */
public class UMConfigUtil {
	private final static String CLASSPATH_PREFIX = "classpath:";
	private final static String CLASSPATH_VALUE;
	private final static ResourceBundle umengBundle;
	static {
		umengBundle = ResourceBundle.getBundle("umeng");
		Set<String> keySet = umengBundle.keySet();
		File file = null;
		CLASSPATH_VALUE = Thread.currentThread().getContextClassLoader()
				.getResource("").getPath();
		for (String key : keySet) {
			if (!key.endsWith("_path")) {
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

	/**
	 * 获取umeng.properties文件中的key值
	 * 
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		return umengBundle.getString(key);
	}
}
