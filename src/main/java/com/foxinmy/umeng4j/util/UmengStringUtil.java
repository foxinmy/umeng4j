package com.foxinmy.umeng4j.util;

/**
 * 字符串工具类
 * 
 * @className UmengStringUtil
 * @author jy
 * @date 2015年12月10日
 * @since JDK 1.7
 * @see
 */
public final class UmengStringUtil {
	public static String concatComma(String... params) {
		StringBuilder sb = new StringBuilder();
		sb.append(params[0]);
		for (int i = 1; i < params.length; i++) {
			sb.append(",").append(params[i]);
		}
		return sb.toString();
	}
}
