package com.foxinmy.umeng4j.cast;

import com.foxinmy.umeng4j.payload.Payload;
import com.foxinmy.umeng4j.type.CastType;

/**
 * 广播
 * @className BroadCast
 * @author jinyu(foxinmy@gmail.com)
 * @date 2015年1月30日
 * @since JDK 1.7
 * @see
 */
public class BroadCast extends UmengCast {
	public BroadCast(Payload payload) {
		super(CastType.BROADCAST, payload);
	}
}
