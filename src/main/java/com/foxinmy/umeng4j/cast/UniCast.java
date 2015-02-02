package com.foxinmy.umeng4j.cast;

import com.alibaba.fastjson.annotation.JSONField;
import com.foxinmy.umeng4j.payload.Payload;
import com.foxinmy.umeng4j.type.CastType;

/**
 * 单播
 * 
 * @className UniCast
 * @author jy
 * @date 2015年1月30日
 * @since JDK 1.7
 * @see
 */
public class UniCast extends UmengCast {

	@JSONField(name = "device_tokens")
	private String deviceToken;

	public UniCast(String deviceToken, Payload payload) {
		super(CastType.UNICAST, payload);
		this.deviceToken = deviceToken;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	@Override
	public String toString() {
		return "UniCast [deviceToken=" + deviceToken + ", " + super.toString()
				+ "]";
	}
}
