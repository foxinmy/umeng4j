package com.foxinmy.umeng4j.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtil {

	/**
	 * MD5
	 * 
	 * @param content
	 * @return
	 */
	public static String getMD5(String content) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			;
		}
		byte[] bytes = content.getBytes();
		byte[] results = messageDigest.digest(bytes);
		StringBuilder stringBuilder = new StringBuilder();
		for (byte result : results) {
			stringBuilder.append(String.format("%02x", result));
		}
		return stringBuilder.toString();
	}

	public static boolean isBlank(String content) {
		return content == null || content.trim().isEmpty();
	}
}
