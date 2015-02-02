package com.foxinmy.umeng4j.cast;

import com.foxinmy.umeng4j.payload.Payload;
import com.foxinmy.umeng4j.type.CastType;

/**
 * 组播
 * @className GroupCast
 * @author jy
 * @date 2015年1月30日
 * @since JDK 1.7
 * @see
 */
public class GroupCast extends UmengCast {
	public GroupCast(Payload payload) {
		super(CastType.GROUPCAST, payload);
	}
}
