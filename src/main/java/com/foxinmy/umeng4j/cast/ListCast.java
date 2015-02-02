package com.foxinmy.umeng4j.cast;

import com.alibaba.fastjson.annotation.JSONField;
import com.foxinmy.umeng4j.payload.Payload;
import com.foxinmy.umeng4j.type.CastType;
import com.foxinmy.umeng4j.util.MapUtil;

/**
 * 列播
 * 
 * @className ListCast
 * @author jy
 * @date 2015年1月30日
 * @since JDK 1.7
 * @see
 */
public class ListCast extends UmengCast {

	@JSONField(name = "device_tokens")
	private String deviceTokens;

	public ListCast(Payload payload, String... deviceTokens) {
		super(CastType.LISTCAST, payload);
		this.deviceTokens = MapUtil.concatComma(deviceTokens);
	}

	public String getDeviceTokens() {
		return deviceTokens;
	}

	@Override
	public String toString() {
		return "ListCast [deviceTokens=" + deviceTokens + ", "
				+ super.toString() + "]";
	}
}
