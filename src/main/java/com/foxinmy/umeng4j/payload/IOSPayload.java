package com.foxinmy.umeng4j.payload;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * 苹果消息提醒
 * 
 * @className IOSPayload
 * @author jy
 * @date 2015年1月30日
 * @since JDK 1.7
 * @see
 */
public class IOSPayload implements Payload {

	private JSONObject payloadContent;
	private JSONObject payloadAps;

	public IOSPayload(String alert) {
		payloadContent = new JSONObject();
		payloadAps = new JSONObject();
		payloadAps.put("alert", alert);
	}

	public IOSPayload badge(String badge) {
		payloadAps.put("badge", badge);
		return this;
	}

	public IOSPayload sound(String sound) {
		payloadAps.put("sound", sound);
		return this;
	}

	public IOSPayload contentAvailable(String contentAvailable) {
		payloadAps.put("content-available", contentAvailable);
		return this;
	}

	/**
	 * 注意: ios8才支持该字段
	 * 
	 * @param category
	 * @return
	 */
	public IOSPayload category(String category) {
		payloadAps.put("category", category);
		return this;
	}

	public IOSPayload keyValue(String key, String value) {
		payloadContent.put(key, value);
		return this;
	}

	public IOSPayload keyValue(Map<String, Object> maps) {
		payloadContent.putAll(maps);
		return this;
	}

	@Override
	public JSONObject asContent() {
		payloadContent.put("aps", payloadAps);
		return payloadContent;
	}
}
